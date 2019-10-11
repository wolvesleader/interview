package com.quincy.java.netty.protocolcustom.converter;

import com.quincy.java.netty.protocolcustom.protocol.DubboProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * author:quincy
 * Date:2019-05-26
 */
public class DubboDecoder extends ByteToMessageDecoder {


    private DubboProtocol protocol;

    public DubboDecoder(DubboProtocol protocol){
        this.protocol = protocol;
    }


    //private DubboProtocol protocol = new DubboProtocol();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        Object decode = protocol.decode(channelHandlerContext.channel(), byteBuf);
        list.add(decode);

    }
}
