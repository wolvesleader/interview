package com.quincy.java.netty.transportcustomobject.handler;

import com.quincy.java.netty.transportcustomobject.domain.Account;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * author:quincy
 * Date:2019-05-24
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("来自客户端 " + msg + "======");
        Account account = new Account();
        account.setAccountId("10001");
        account.setAccountPwd("123456");
        ctx.writeAndFlush(account);

    }

}
