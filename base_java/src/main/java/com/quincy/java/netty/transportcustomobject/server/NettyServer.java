package com.quincy.java.netty.transportcustomobject.server;

import com.quincy.java.netty.transportcustomobject.handler.AccountMessageDecoder;
import com.quincy.java.netty.transportcustomobject.handler.AccountMessageEncoder;
import com.quincy.java.netty.transportcustomobject.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * author:quincy
 * Date:2019-05-20
 */
public class NettyServer {

    private int port;

    public NettyServer(int port){
        this.port = port;
    }


    public void run() throws  Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGrop = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup,workGrop)
                .channel(NioServerSocketChannel.class)//
                .childHandler(new ChannelInitializer<SocketChannel>() {//
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {//

                        socketChannel.pipeline()
                                .addLast(new AccountMessageDecoder())
                                .addLast(new AccountMessageEncoder())
                                .addLast(new ServerHandler())
                        .addLast(new LoggingHandler(LogLevel.INFO));
                    }
                })
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture f = server.bind(port).sync();

        f.channel().closeFuture().sync();

        bossGroup.shutdownGracefully();
        workGrop.shutdownGracefully();


    }


    public static void main(String[] args) throws  Exception{
        NettyServer nettyServer = new NettyServer(9999);
        nettyServer.run();
    }
}
