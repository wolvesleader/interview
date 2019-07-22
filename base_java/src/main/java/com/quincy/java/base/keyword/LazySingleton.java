package com.quincy.java.base.keyword;


/**
 * Created by quincy on 2018/12/20.
 * java -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*LazySingleton.getInstance LazySingleton
 *
 * java -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=print,*LazySingleton.getInstance LazySingleton
 */
public class LazySingleton {

    private static volatile LazySingleton instance = null;

    public static LazySingleton getInstanceQuincy() {
        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }

    public static void main(String[] args) {
        LazySingleton.getInstanceQuincy();
    }

}