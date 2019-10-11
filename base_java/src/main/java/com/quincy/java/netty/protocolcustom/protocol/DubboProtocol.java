package com.quincy.java.netty.protocolcustom.protocol;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoPool;
import com.quincy.java.netty.protocolcustom.Bytes;
import com.quincy.java.netty.protocolcustom.converter.ChannelBufferInputStream;
import com.quincy.java.netty.protocolcustom.converter.ChannelBufferOutputStream;
import com.quincy.java.netty.protocolcustom.message.Request;
import com.quincy.java.netty.protocolcustom.message.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.IOException;

/**
 * author:quincy
 * Date:2019-05-26
 */
public class DubboProtocol {

    // header length.
    protected static final int HEADER_LENGTH = 16;
    // magic header.
    protected static final short MAGIC = (short) 0xdabb;
    protected static final byte MAGIC_HIGH = short2bytes(MAGIC)[0];
    protected static final byte MAGIC_LOW = short2bytes(MAGIC)[1];
    // message flag.
    protected static final byte FLAG_REQUEST = (byte) 0x80;
    protected static final byte FLAG_TWOWAY = (byte) 0x40;
    protected static final byte FLAG_EVENT = (byte) 0x20;
    protected static final int SERIALIZATION_MASK = 0x1f;

    public KryoPool newKryoPool() {
        return new KryoPool.Builder(() -> {
            final Kryo kryo = new Kryo();
            kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(
                    new StdInstantiatorStrategy()));
            return kryo;
        }).softReferences().build();
    }

    private Kryo kryo ;

    public DubboProtocol(){
        kryo = newKryoPool().borrow();
        //kryo.register(Request.class,new JavaSerializer());
        //kryo.register(Response.class,new JavaSerializer());
        //kryo.setRegistrationRequired(true);
        //kryo.register(Response.class,new DefaultSerializers.KryoSerializableSerializer(),11);
        //kryo.register(Request.class,new DefaultSerializers.KryoSerializableSerializer(),10);
    }


    public void encode(Channel channel, ByteBuf buffer, Object msg) throws IOException {
        if (msg instanceof Request) {
            encodeRequest(channel, buffer, (Request) msg);
        } else if (msg instanceof Response) {
            encodeResponse(channel, buffer, (Response) msg);
        } else {
            //super.encode(channel, buffer, msg);
        }
    }

    public Object decode(Channel channel, ByteBuf buffer) throws IOException {
        int readable = buffer.readableBytes();
        byte[] header = new byte[Math.min(readable, HEADER_LENGTH)];
        buffer.readBytes(header);
        return decode(channel, buffer, readable, header);
    }

    private int decodeStartIndex = 0;
    private int decodeEndIndex = 0;
    protected Object decode(Channel channel, ByteBuf buffer, int readable, byte[] header) throws IOException {
        // check magic number.
        if (readable > 0 && header[0] != MAGIC_HIGH
                || readable > 1 && header[1] != MAGIC_LOW) {
            int length = header.length;
            if (header.length < readable) {
                header = Bytes.copyOf(header, readable);
                buffer.readBytes(header, length, readable - length);
            }
            for (int i = 1; i < header.length - 1; i++) {
                if (header[i] == MAGIC_HIGH && header[i + 1] == MAGIC_LOW) {
                    buffer.readerIndex(buffer.readerIndex() - header.length + i);
                    header = Bytes.copyOf(header, i);
                    break;
                }
            }
            //return super.decode(channel, buffer, readable, header);
        }
        // check length.
        if (readable < HEADER_LENGTH) {
            //return DecodeResult.NEED_MORE_INPUT;
        }

        // get data length.
        int len = Bytes.bytes2int(header, 12);
        //checkPayload(channel, len);

        int tt = len + HEADER_LENGTH;
        if (readable < tt) {
            //return DecodeResult.NEED_MORE_INPUT;
        }

        // limit input stream.
        ChannelBufferInputStream is = new ChannelBufferInputStream(buffer, len);
        //decodeStartIndex = buffer.readerIndex();
       // decodeEndIndex = decodeStartIndex + len;
        //buffer.markReaderIndex();
       // byte[] bytes = new byte[tt];
        //InputStream bais = new ByteArrayInputStream(bytes);
        Input input = new Input(is,8 * 1024 * 1024);

        try {
            return decodeBody(channel, input, header);
        } finally {
           int available =  decodeEndIndex - buffer.readerIndex();
            if (available > 0) {

            }
        }
    }
    protected Object decodeBody(Channel channel, Input input, byte[] header) throws IOException {

        byte flag = header[2], proto = (byte) (flag & SERIALIZATION_MASK);
        //Serialization s = CodecSupport.getSerialization(channel.getUrl(), proto);
        //ObjectInput in = s.deserialize(channel.getUrl(), is);
        // get request id.
        long id = Bytes.bytes2long(header, 4);
        if ((flag & FLAG_REQUEST) == 0) {
            // decode response.
            Response res = new Response(id);
            if ((flag & FLAG_EVENT) != 0) {
                res.setEvent(Response.HEARTBEAT_EVENT);
            }
            // get status.
            byte status = header[3];
            res.setStatus(status);
            if (status == Response.OK) {
                try {
                    Object data;
                    if (res.isHeartbeat()) {
                        data = decodeHeartbeatData(channel, input,false);
                    } else if (res.isEvent()) {
                        data = decodeEventData(channel, input,false);
                    } else {
                        data = decodeResponseData(channel, input);
                    }
                    res.setResult(data);
                } catch (Throwable t) {
                    res.setStatus(Response.CLIENT_ERROR);
                    //res.setErrorMessage(StringUtils.toString(t));
                }
            } else {
                //res.setErrorMessage(in.readUTF());
            }
            return res;
        } else {
            // decode request.
            Request req = new Request(id);
            req.setVersion("2.0.0");
            req.setTwoWay((flag & FLAG_TWOWAY) != 0);
            if ((flag & FLAG_EVENT) != 0) {
                req.setEvent(Request.HEARTBEAT_EVENT);
            }
            try {
                Object data;
                if (req.isHeartbeat()) {
                    data = decodeHeartbeatData(channel, input,true);
                } else if (req.isEvent()) {
                    data = decodeEventData(channel, input,true);
                } else {
                    data = decodeRequestData(channel, input);
                }
                req.setData(data);
            } catch (Throwable t) {
                t.printStackTrace();
                // bad request
                req.setBroken(true);
                req.setData(t);
            }
            return req;
        }
    }

    private Object decodeRequestData(Channel channel, Input input) {
        //return  kryo.readObject(input, Request.class);
        return kryo.readClassAndObject(input);
    }

    private Object decodeResponseData(Channel channel, Input input) {
        //return  kryo.readObject(input, Response.class);
        return kryo.readClassAndObject(input);
        //return null;
    }

    private Object decodeEventData(Channel channel, Input input, boolean isRequest) {
        /*if ( isRequest ){
            return kryo.readObject(input, Request.class);
        }else{
            return  kryo.readObject(input, Response.class);
        }*/
        return null;

    }

    private Object decodeHeartbeatData(Channel channel, Input input,boolean isRequest) {
       /*if ( isRequest ){
           return kryo.readObject(input, Request.class);
       }else{
           return  kryo.readObject(input, Response.class);
       }*/
       return null;
    }


    private int startDecodeIndex = 0;
    private void encodeResponse(Channel channel, ByteBuf buffer, Response response) {
        try {
            //Serialization serialization = getSerialization(channel);
            // header.
            byte[] header = new byte[HEADER_LENGTH];
            // set magic number.
            Bytes.short2bytes(MAGIC, header);
            // set request and serialization flag.
            //header[2] = serialization.getContentTypeId();
            header[2] = Bytes.int2bytes(1)[0];
            
            if (response.isHeartbeat()) header[2] |= FLAG_EVENT;
            // set response status.
            byte status = response.getStatus();
            header[3] = status;
            // set request id.
            Bytes.long2bytes(response.getId(), header, 4);

            int savedWriteIndex = buffer.writerIndex();
            buffer.writerIndex(savedWriteIndex + HEADER_LENGTH);
            ChannelBufferOutputStream bos = new ChannelBufferOutputStream(buffer);
            //ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //ObjectOutput out = serialization.serialize(channel.getUrl(), bos);
            //startDecodeIndex = buffer.writerIndex();
            Output out = new Output(4096,8 * 1024 * 1024);
            out.setOutputStream(bos);
            // encode response data or error message.
            if (status == Response.OK) {
                if (response.isHeartbeat()) {
                    encodeHeartbeatData(channel, out, response.getResult());
                } else {
                    encodeResponseData(channel, out, response.getResult());
                }
            } //else out.writeUTF(response.getErrorMessage());
            out.flush();
            bos.flush();
            bos.close();
            int len = bos.writtenBytes();
            //int len = buffer.writerIndex() -startDecodeIndex;
            //checkPayload(channel, len);
            Bytes.int2bytes(len, header, 12);
            // write
            buffer.writerIndex(savedWriteIndex);
            buffer.writeBytes(header); // write header.
            buffer.writerIndex(savedWriteIndex + HEADER_LENGTH + len);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void encodeResponseData(Channel channel, Output out, Object result) {
        //kryo.writeObject(out,result);
        kryo.writeClassAndObject(out,result);
    }

    private void encodeHeartbeatData(Channel channel, Output out, Object result) {
        //kryo.writeObject(out,result);
        kryo.writeClassAndObject(out,result);
    }

    private  int startEncodeIndex = 0;
    private void encodeRequest(Channel channel, ByteBuf buffer, Request request) {

        // header.
        //设置请求头长度为16
        byte[] header = new byte[HEADER_LENGTH];
        // set magic number.
        //1-2位置为模数
        Bytes.short2bytes(MAGIC, header);

        // set request and serialization flag.
        header[2] = (byte) (FLAG_REQUEST);

        if (request.isTwoWay()) header[2] |= FLAG_TWOWAY;
        if (request.isEvent()) header[2] |= FLAG_EVENT;

        // set request id.
        Bytes.long2bytes(request.getId(), header, 4);

        // encode request data.
        int savedWriteIndex = buffer.writerIndex();
        buffer.writerIndex(savedWriteIndex + HEADER_LENGTH);

        ChannelBufferOutputStream bos = new ChannelBufferOutputStream(buffer);
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //startEncodeIndex = buffer.writerIndex();
        Output out = new Output(bos);
       // ObjectOutput out = serialization.serialize(channel.getUrl(), bos);
        if (request.isEvent()) {
            encodeEventData(channel, out, request.getData());
        } else {
            encodeRequestData(channel, out, request.getData());
        }
        try {
            out.flush();
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //检测传输的数据大小，不能超过8M负责会报错误
        int len = bos.writtenBytes();
        //int len = buffer.writerIndex() - startEncodeIndex;
        //checkPayload(channel, len);
        Bytes.int2bytes(len, header, 12);

        // write
        buffer.writerIndex(savedWriteIndex);
        buffer.writeBytes(header); // write header.
        buffer.writerIndex(savedWriteIndex + HEADER_LENGTH + len);
    }

    private void encodeRequestData(Channel channel, Output out, Object data) {
        //kryo.writeObject(out,data);
        kryo.writeClassAndObject(out,data);
    }

    private void encodeEventData(Channel channel, Output out, Object data) {
        //kryo.writeObject(out,data);
       kryo.writeClassAndObject(out,data);
    }


    public static void short2bytes(short v, byte[] b) {
        short2bytes(v, b, 0);
    }

    public static byte[] short2bytes(short v) {
        byte[] ret = {0, 0};
        short2bytes(v, ret);
        return ret;
    }

    public static void short2bytes(short v, byte[] b, int off) {
        b[off + 1] = (byte) v;
        b[off + 0] = (byte) (v >>> 8);
    }

}
