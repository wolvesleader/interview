package com.quincy.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by quincy on 2018/6/20.
 */
public class ClientScoket {

    private  Selector selector;

    public  void serverScoket() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));
            serverSocketChannel.configureBlocking(false);


            selector = Selector.open();

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen(){

        while (true){
            try {
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
        int read = channel.read(buffer);
        if(read > 0){
            byte[] data = buffer.array();
            String msg = new String(data).trim();
            System.out.println("服务端收到信息：" + msg);

            //回写数据
            ByteBuffer outBuffer = ByteBuffer.wrap("好的".getBytes());
            channel.write(outBuffer);// 将消息回送给客户端
        }else{
            System.out.println("客户端关闭");
            key.cancel();
        }
    }



    public static void main(String[] args) {
        ClientScoket clientScoket = new ClientScoket();
        clientScoket.serverScoket();
        clientScoket.listen();

    }
}
