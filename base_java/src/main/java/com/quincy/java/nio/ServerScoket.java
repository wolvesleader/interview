package com.quincy.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by quincy on 2018/6/20.
 * 没有提供客户端程序
 * 测试可以使用 telnet localhost 9999 命令开启多个窗口
 *NIO+Reactor(反应堆)+单线程
 */
public class ServerScoket {

    private  Selector selector;

    public  void serverScoket() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            //注册连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reactor(单线程模式)反应堆
     */
    public void listen(){

        while (true){
            try {
                //如果没有任何事件进入会阻塞在这里
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    handlerKey(selectionKey);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 分发器==dispatch
     * @param selectionKey
     */
    public void handlerKey(SelectionKey selectionKey){

       if(selectionKey.isAcceptable()){
           try {
               handlerAccept(selectionKey);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }else if(selectionKey.isReadable()){
           try {
               handelerRead(selectionKey);
           } catch (IOException e) {
               e.printStackTrace();
           }

       }else if(selectionKey.isWritable()){
           handlerWrite(selectionKey);
       }


    }

    public void handlerWrite(SelectionKey key){
        try {
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            SocketChannel channel = (SocketChannel) key.channel();
            channel.write(buffer);
            channel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 处理连接请求
     *
     * @param key
     * @throws IOException
     */
    public void handlerAccept(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        // 获得和客户端连接的通道
        SocketChannel channel = server.accept();
        // 设置成非阻塞
        channel.configureBlocking(false);
        // 在这里可以给客户端发送信息哦
        System.out.println("新的客户端连接");
        // 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
        channel.register(this.selector, SelectionKey.OP_READ);
    }


    /**
     * 处理读的事件
     *
     * @param key
     * @throws IOException
     */
    public void handelerRead(SelectionKey key) throws IOException {

        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //可以打断点调试，如果客户端有连接进入，没有写入任何数据
        //线程也不会阻塞在这里
        //设置NIO模式的表现
        int read = channel.read(buffer);
        if(read > 0){

            byte[] data = buffer.array();
            String msg = new String(data).trim();
            System.out.println("服务端收到信息：" + msg);
            //回写数据
            //直接通过channel写回去也可以但是不好
            //可以注册写事件

            ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
            sendBuffer.clear();
            sendBuffer.put("好的".getBytes());
            sendBuffer.flip();
            channel.register(selector,SelectionKey.OP_WRITE,sendBuffer);
            //channel.write(outBuffer);// 将消息回送给客户端
        }else{
            System.out.println("客户端关闭");
            key.cancel();
        }
    }



    public static void main(String[] args) {
        ServerScoket clientScoket = new ServerScoket();
        clientScoket.serverScoket();
        clientScoket.listen();

    }
}
