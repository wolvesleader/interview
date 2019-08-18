package com.quincy.java.base.iomodel.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * author:quincy
 * Date:2019-08-18
 */
public class Reactor implements Runnable{
    final Selector selector;
    final ServerSocketChannel serverSocket;

    Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(
                new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk =
                serverSocket.register(selector,
                        SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext())
                    dispatch((SelectionKey)(it.next()));
                selected.clear();
            }
        } catch (IOException ex) { /* ... */ }
    }

    void dispatch(SelectionKey k) {
        Runnable r = (Runnable)(k.attachment());
        if (r != null)
            r.run();
    }

    class Acceptor implements Runnable { // inner
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null)
                    new Handler(selector, c);
            }catch(IOException ex) { /* ... */ }
        }
    }
}

final class Handler implements Runnable {
    final SocketChannel socket;
    final SelectionKey sk;
    static final int MAXIN = 256*1024;
    static final int MAXOUT = 256*1024;
    ByteBuffer input = ByteBuffer.allocate(MAXIN);
    ByteBuffer output = ByteBuffer.allocate(MAXOUT);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    Handler(Selector sel, SocketChannel c)
            throws IOException {
        socket = c;
        c.configureBlocking(false);
        // Optionally try first read now
        sk = socket.register(sel, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }

    boolean inputIsComplete() {
        return input.hasRemaining();
    }

    boolean outputIsComplete() {
       return output.hasRemaining();
    }

    void process(int readCount) {

        StringBuilder sb = new StringBuilder();
        input.flip();   //from writing mode to reading mode
        byte[] subStringBytes = new byte[readCount];
        byte[] array = input.array();
        System.arraycopy(array, 0, subStringBytes, 0, readCount);
        sb.append(new String(subStringBytes));
        input.clear();
        System.out.println("服务端收到信息：" + sb.toString());
    }


    public void run() {
        try {
            if (state == READING) read();
            else if (state == SENDING) send();
        } catch (IOException ex) { /* ... */ }
    }
    void read() throws IOException {
        int read = socket.read(input);
        if (read > 0){
            //if (inputIsComplete()) {
            process(read);
            state = SENDING;
            // Normally also do first write now
            sk.interestOps(SelectionKey.OP_WRITE);
            //}
        }
    }
    void send() throws IOException {
        output.clear();
        output.put("好的".getBytes());
        output.flip();
        socket.write(output);
        sk.interestOps(SelectionKey.OP_READ);
        state = READING;
        //if (outputIsComplete()) sk.cancel();
    }


}
