package com.quincy.java.base.threadreadtxt;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: TestDriver
 *
 * @author: quincy
 * Date: 2020/6/5 上午8:55
 * History:
 */

public class TestDriver {


    @Test
    public void data(){

        File file = new File("/Volumes/quincy/study/interview/base_java/src/main/java/com/quincy/java/base/threadreadtxt/test.txt");
        ThreadReadTxt threadReadTxt = new ThreadReadTxt(file);
        try {
            threadReadTxt.writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testDriver(){

        File file = new File("/Volumes/quincy/study/interview/base_java/src/main/java/com/quincy/java/base/threadreadtxt/test.txt");
        ThreadReadTxt threadReadTxt = new ThreadReadTxt(file);

        /*threadReadTxt.start(new Handle() {
            @Override
            public void callback(byte[] bytes) {
                try {
                    String line = new String(bytes,"gbk");
                    System.out.println(line);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });*/

        threadReadTxt.start(null);

    }

}
