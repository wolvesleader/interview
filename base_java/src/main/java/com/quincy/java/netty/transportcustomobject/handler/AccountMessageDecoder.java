package com.quincy.java.netty.transportcustomobject.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * author:quincy
 * Date:2019-05-26
 * account
 * 对象解码器
 */
public class AccountMessageDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        byteBuf.markReaderIndex();
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];

        byteBuf.readBytes(bytes);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = new ObjectInputStream(byteArrayInputStream);
        Object o = oi.readObject();
        list.add(o);
    }
}
