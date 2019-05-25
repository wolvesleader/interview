package com.quincy.java.netty.transportcustomobject.handler;

import com.quincy.java.netty.transportcustomobject.domain.Account;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * author:quincy
 * Date:2019-05-26
 * 把一个对象转换为ByteBuf
 * 对象编码器
 */
public class AccountMessageEncoder extends MessageToByteEncoder<Account> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Account account, ByteBuf byteBuf) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(account);
        byte[] bytes = byteArrayOutputStream.toByteArray();


        //ByteBuf buffer = channelHandlerContext.alloc().buffer();

        int dataLength = bytes.length;
        byteBuf.writeInt(dataLength);
        byteBuf.writeBytes(bytes);
    }
}
