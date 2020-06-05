package com.quincy.java.base.threadreadtxt;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: FileContentZoneTask
 *
 * @author: quincy
 * Date: 2020/6/5 上午9:11
 * History:
 */

public class FileContentZoneTask implements Runnable{


    FileContentZone fileContentZone;
    RandomAccessFile randomAccessFile;
    long zoneSize;
    byte[] readBuff;
    int bufferSize = 1024*1024;
    private Handle handle;
    CyclicBarrier cyclicBarrier;

    public FileContentZoneTask(FileContentZone fileContentZone,
                               RandomAccessFile randomAccessFile,
                               Handle handle,CyclicBarrier cyclicBarrier){
        this.fileContentZone = fileContentZone;
        this.randomAccessFile = randomAccessFile;
        this.zoneSize = fileContentZone.getEnd() - fileContentZone.getStart() + 1;
        this.readBuff = new byte[bufferSize];
        this.handle = handle;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        if (handle != null){
            destToMemory();
        }else{
            destToDiscFile();
        }
    }

    /**
     * 为了保证多线程分片之后的文件内容能按照原来的顺序写入其他文件
     *
     */
    private void destToDiscFile(){
        try {
            File file = new File("/Volumes/quincy/study/interview/base_java/src/main/java/com/quincy/java/base/threadreadtxt/",fileContentZone.getPart()+".txt");
            if (!file.exists()){
                file.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(file);

            randomAccessFile.seek(fileContentZone.getStart());
            for(int offset=0;offset<zoneSize;offset+=bufferSize) {
                int readLength;
                if (offset + bufferSize <= zoneSize) {
                    readLength = bufferSize;
                } else {
                    readLength = (int) (zoneSize - offset);
                }
                randomAccessFile.read(readBuff,0,readLength);
                outputStream.write(readBuff,0,readLength);
                outputStream.flush();
            }

            outputStream.close();
            cyclicBarrier.await();

        } catch (Exception e) {
            throw  new RuntimeException("文件不可读",e);
        }

    }

    private void destToMemory(){
        try {
            MappedByteBuffer mapBuffer = randomAccessFile.getChannel().
                    map(FileChannel.MapMode.READ_ONLY, fileContentZone.getStart(),zoneSize);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            for(int offset=0;offset<zoneSize;offset+=bufferSize){
                int readLength;
                if(offset+bufferSize<=zoneSize){
                    readLength = bufferSize;
                }else{
                    readLength = (int) (zoneSize-offset);
                }
                mapBuffer.get(readBuff, 0, readLength);
                for(int i=0;i<readLength;i++){
                    byte tmp = readBuff[i];
                    if(tmp=='\n' || tmp=='\r'){

                        handle.callback(bos.toByteArray());
                        bos.reset();
                    }else{
                        bos.write(tmp);

                    }
                }
            }
            if(bos.size()>0){
                handle.callback(bos.toByteArray());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("文件不可读",e);
        }

    }
}
