package com.quincy.java.netty.transportcustomobject.handler;

import com.quincy.java.netty.transportcustomobject.domain.Account;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * author:quincy
 * Date:2019-05-24
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Account account = new Account();
        account.setAccountId("10000");
        account.setAccountPwd("123456");
        ctx.writeAndFlush(account);
        Account account2 = new Account();
        account2.setAccountId("9999");
        account2.setAccountPwd("111");
        ctx.writeAndFlush(account2);
        //System.out.println("client send message:"+account);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("来自服务端 " + msg + "=========");


    }

}
