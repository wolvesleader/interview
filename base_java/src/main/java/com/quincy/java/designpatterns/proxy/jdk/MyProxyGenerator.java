package com.quincy.java.designpatterns.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by quincy on 18/6/16.
 */
public class MyProxyGenerator {

    public static void main(String[] args) {

          new Vector<String>();

        IUserDao userDao = new UserDao();
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy11", UserDao.class.getInterfaces());
        FileOutputStream out = null;
        try {
            File file = new File("/Users/quincy/Desktop/test","$Proxy11.class");
            out = new FileOutputStream(file);
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
