package com.quincy.java.base.iomodel.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * author:quincy
 * Date:2019-08-16
 * 测试可以使用 telnet localhost 9999 命令开启多个窗口
 */
public class BioServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);

            while (true){
                //没有连接进来会阻塞在这里
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                //BIO阻塞模式的体现，可以打断点调试
                //如果客户端没有数据写入，永远会阻塞在这里
                int read = inputStream.read();
                System.out.println(read);
                System.out.println(456);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
