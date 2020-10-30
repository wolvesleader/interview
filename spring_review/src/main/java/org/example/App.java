package org.example;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import sun.security.provider.MD2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        try {
            String str = "E";
            MessageDigest md = MessageDigest.getInstance("MD2");
            //MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            String encode = HexBin.encode(digest);
            System.out.println(encode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
