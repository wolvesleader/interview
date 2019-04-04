package com.quincy.java.netty.client;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by quincy on 18/5/23.
 */
public class NettyClient {
    public static void main(String[] args) {

//        ClientBootstrap client = new ClientBootstrap();
//        ExecutorService boss = Executors.newCachedThreadPool();
//        ExecutorService worker = Executors.newCachedThreadPool();
//
//        client.setFactory(new NioClientSocketChannelFactory(boss,worker));
//
//        client.setPipelineFactory(new ChannelPipelineFactory() {
//            @Override
//            public ChannelPipeline getPipeline() throws Exception {
//                ChannelPipeline pipeline = Channels.pipeline();
//                pipeline.addLast("decoder",new StringDecoder());
//                pipeline.addLast("encoder",new StringEncoder());
//                pipeline.addLast("hiHandler",new HiHandler());
//                return pipeline;
//            }
//        });
//
//
//        ChannelFuture connect = client.connect(new InetSocketAddress("127.0.0.1", 9999));
//        Channel channel = connect.getChannel();
//
//        System.out.println("client start");
//
//        Scanner scanner = new Scanner(System.in);
//        while(true){
//            System.out.println("请输入");
//            channel.write(scanner.next());
//        }


    }
}
