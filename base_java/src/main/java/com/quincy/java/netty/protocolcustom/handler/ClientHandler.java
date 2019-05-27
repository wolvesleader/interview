package com.quincy.java.netty.protocolcustom.handler;

import com.quincy.java.netty.protocolcustom.message.Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * author:quincy
 * Date:2019-05-24
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //写一条消息出去
        Request request = new Request();
        //request.setEvent("");
        request.setHeartbeat(false);
        request.setData("来自客户端的请求数据");
        ctx.writeAndFlush(request);


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("ClientHandler " + msg + "=========");


    }

}
