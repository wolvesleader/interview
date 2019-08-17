package com.quincy.java.base.iomodel.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * author:quincy
 * Date:2019-08-16
 */
public class BioServer {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",9999);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("大家好".getBytes());
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
