package com.quincy.java.netty.customizedmessage.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * author:quincy
 * Date:2019-05-24
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       /* String msg = "Hello world\n";
        ByteBuf encoded = ctx.alloc().buffer(msg.length());
        encoded.writeBytes(msg.getBytes());
        ctx.write(encoded);
        ctx.flush();*/


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buffer = ctx.alloc().buffer(2048);

        ByteBuf in = (ByteBuf) msg;
        try {
            if (in.isReadable()) { // (1)
                String str = in.toString(CharsetUtil.US_ASCII);
                //Log.logInfo("server receive message:" + str);
                System.out.println(str + "=======");
                buffer.writeBytes("ddddd".getBytes());
                ctx.write(buffer);
                ctx.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }

    }

}
