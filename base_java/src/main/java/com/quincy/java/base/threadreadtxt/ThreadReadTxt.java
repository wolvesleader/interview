package com.quincy.java.base.threadreadtxt;


import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: FileContentZone
 * Date: 2020/6/4 下午7:45
 * History:
 * @author quincy
 */


public class ThreadReadTxt {

    private RandomAccessFile randomAccessFile;
    private long fileLength;
    private int threadCount =  Runtime.getRuntime().availableProcessors() + 1;
    private long zoneSize ;

    private Set<FileContentZone> fileContentZoneSet;

    private ExecutorService executorService;

    private AtomicInteger atomicInteger = new AtomicInteger(0);



    public ThreadReadTxt(File file){
        try {
            this.fileLength = file.length();
            System.out.println("文件大小："+this.fileLength);
            this.zoneSize = this.fileLength / threadCount;
            this.randomAccessFile = new RandomAccessFile(file,"rw");
            fileContentZoneSet = Collections.synchronizedSet(new HashSet<>());
            executorService = Executors.newFixedThreadPool(threadCount);
        } catch (Exception e) {
            throw new RuntimeException("创建ThreadReadTxt失败 ",e);
        }
    }




    protected void start(Handle handle){
        fileContentZoneCount(0);

        final long startTime = System.currentTimeMillis();
       CyclicBarrier cyclicBarrier = new CyclicBarrier(fileContentZoneSet.size(),new Runnable() {

            @Override
            public void run() {
                System.out.println("use time: "+(System.currentTimeMillis()-startTime));
            }
        });

       fileContentZoneSet.stream().forEach(item ->this.executorService.execute(new FileContentZoneTask(item,this.randomAccessFile,handle,cyclicBarrier)));

    }


     protected void fileContentZoneCount(long startPosition){
        try {
            if (startPosition > fileLength ){
                throw new RuntimeException("分片开始位置不能大于文件长度");
            }
            long endPosition = ( startPosition + zoneSize ) - 1;
            FileContentZone fileContentZone = new FileContentZone();
            fileContentZone.setStart(startPosition);
            fileContentZone.setPart(atomicInteger.incrementAndGet());
            if (endPosition > fileLength - 1){
                fileContentZone.setEnd(fileLength - 1);
                fileContentZoneSet.add(fileContentZone);
                return;
            }

            this.randomAccessFile.seek(endPosition);
            char read = (char) randomAccessFile.read();
            while (read != '\n' && read != '\r'){
                endPosition ++;
                randomAccessFile.seek(endPosition);
                read = (char) randomAccessFile.read();
            }
            fileContentZone.setEnd(endPosition);
            fileContentZoneSet.add(fileContentZone);
            fileContentZoneCount(endPosition + 1);
        } catch (IOException e) {
            throw new RuntimeException("seek or read exception",e);
        }


    }


    public  void writeData() throws  IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("/Volumes/quincy/study/interview/base_java/src/main/java/com/quincy/java/base/threadreadtxt/test.txt");
        Random random = new Random();
        for (int n = 0; n < 1000; n++) {
            //int count = random.nextInt(10) + 1;
            StringBuilder builder = new StringBuilder();
            builder.append(n).append("==");
            for (int i = 0; i < 3; i++) {
                builder.append(UUID.randomUUID().toString());
            }

            builder.append("\n");
            fileOutputStream.write(builder.toString().getBytes());
        }
        fileOutputStream.close();
        System.out.println("ok");
    }

}

