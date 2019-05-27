package com.quincy.java.netty.protocolcustom.handler;

import com.quincy.java.netty.protocolcustom.message.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * author:quincy
 * Date:2019-05-24
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.toString());
        Response response = new Response();
        response.setHeartbeat(false);
        response.setResult("服务端响应数据");
        ctx.writeAndFlush(response);

    }

}
