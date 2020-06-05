package com.quincy.java.base.threadreadtxt;

import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
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
    /**
     * 读取文本有多少行
     * @return
     */
    @Test
    public void readLineNumber(){


        int threadCount = Runtime.getRuntime().availableProcessors() + 1;

        //CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);

        File file = new File("/Volumes/quincy/study/interview/base_java/src/main/java/com/quincy/java/base/threadreadtxt/test.txt");
        ThreadReadTxt threadReadTxt = new ThreadReadTxt(file);

        threadReadTxt.start(new Handle() {
            @Override
            public void callback(byte[] bytes) {
                try {
                    String line = new String(bytes,"gbk");
                    System.out.println(line);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //threadReadTxt.fileContentZoneCount(0);
        //threadReadTxt.getFileContentZoneList().stream().forEach(item -> System.out.println(item.getStart()+"--" + item.getEnd()));
    }

}
