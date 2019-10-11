package com.quincy.java.base.jvm.out_of_memory;

import java.util.ArrayList;

/**
 *  <dependency>
 *    <groupId>asm</groupId>
 *    <artifactId>asm</artifactId>
 *    <version>3.3.1</version>
 *  </dependency>
 *  必须要通过服务器访问，负责出现错误进程关闭，获取不到进程信息
 */
public class OutOfMemoryDriver {
    public static void main(String[] args) {
        OutOfMemoryDriver outOfMemoryDriver = new OutOfMemoryDriver();
        outOfMemoryDriver.heap();

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //URL resource = outOfMemoryDriver.getClass().getResource("/");
        //System.out.println(resource.getPath());
        //outOfMemoryDriver.noheap();
    }
    /**
     * 非堆溢出
     * -XX:MaxMetaspaceSize=16m -XX:MetaspaceSize=16m
     * 需要引入asm jar包
     */
    public void noheap(){
        ArrayList<Class> classList = new ArrayList<>();
        while (true) {
            classList.addAll(Metaspace.createClasses());
        }
    }
    /**
     * 堆溢出
     * -Xmx16M -Xms16M
     */
    public void heap(){
        ArrayList<String> strings = new ArrayList<>();
        while (true){
            strings.add(new String("1234567890"));
        }
    }
}
