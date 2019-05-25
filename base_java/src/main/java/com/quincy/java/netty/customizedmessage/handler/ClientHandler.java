package com.quincy.java.netty.customizedmessage.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * author:quincy
 * Date:2019-05-24
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(">>>>> I'm client.");

        //String msg = "Are you ok?";
        //ByteBuf encoded = ctx.alloc().buffer(msg.length());
        //encoded.writeBytes(msg.getBytes());
        //ctx.write(encoded);
        //ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) { // (1)
                System.out.println("client收到服务器的消息:" + msg);
                //System.out.println(new String(in.getBytes(2048)));
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }

    }

}
