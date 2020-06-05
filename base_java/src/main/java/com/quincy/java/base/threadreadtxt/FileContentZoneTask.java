package com.quincy.java.base.threadreadtxt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
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
    CountDownLatch latch;
    public FileContentZoneTask(FileContentZone fileContentZone, RandomAccessFile randomAccessFile,Handle handle,CountDownLatch latch){
        this.fileContentZone = fileContentZone;
        this.randomAccessFile = randomAccessFile;
        this.zoneSize = fileContentZone.getEnd() - fileContentZone.getStart() + 1;
        this.readBuff = new byte[bufferSize];
        this.handle = handle;
        this.latch = latch;
    }

    @Override
    public void run() {

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
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
          throw  new RuntimeException("文件不可读",e);
        }


    }
}
