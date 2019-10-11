package com.quincy.java.netty.protocolcustom.converter;

import com.quincy.java.netty.protocolcustom.protocol.DubboProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * author:quincy
 * Date:2019-05-26
 */
public class DubboEncoder extends MessageToByteEncoder {

    private DubboProtocol protocol;

    public DubboEncoder(DubboProtocol protocol){
        this.protocol = protocol;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {

        protocol.encode(channelHandlerContext.channel(),byteBuf,o);

    }
}
