package com.quincy.java.netty.protocolcustom.client;


import com.quincy.java.netty.protocolcustom.handler.ClientHandler;
import com.quincy.java.netty.protocolcustom.handler.NettyAdapter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * author:quincy
 * Date:2019-05-20
 */
public class NettyClient {


    public void connect(String host,int port) throws Exception{
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup client = new NioEventLoopGroup();

        bootstrap.group(client)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)//
                .handler(new ChannelInitializer<SocketChannel>() {//
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        NettyAdapter adapter = new NettyAdapter();
                        socketChannel.pipeline()
                                .addLast("decoder", adapter.getDubboDecoder())
                                .addLast("encoder", adapter.getDubboEncoder())
                                .addLast(new LoggingHandler(LogLevel.INFO))
                                .addLast(new ClientHandler());

                    }
                });

        ChannelFuture f = bootstrap.connect(host, port).sync();
        f.channel().closeFuture().sync();
        client.shutdownGracefully();

    }

    public static void main(String[] args) throws Exception {
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect("localhost",9999);
    }
}
