package com.quincy.java.base.jvm.out_of_memory;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


import java.util.ArrayList;
import java.util.List;

/**
 * author:quincy
 * Date:2019-01-27
 */
public class Metaspace extends ClassLoader {

    public static List<Class<?>> createClasses(){
        List<Class<?>> classes = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            ClassWriter cw = new ClassWriter(0);
            //定义一个Class类。访问域为public,父类为Object，没有实现接口
            cw.visit(Opcodes.V1_1,Opcodes.ACC_PUBLIC,"Class"+i,null,"java/lang/Object",null);
            //定义构造函数<init>
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC,"<init>","()V",null,null);
            //第一个指令加载this
            mv.visitIntInsn(Opcodes.ALOAD,0);
            //第二个指令为调用父类Object的构造函数
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL,"java/lang/Oject","<init>","()V");
            //第三条指令为return
            mv.visitInsn(Opcodes.RETURN);
            //局部变量表和操作数栈的大小，可以忽略
            mv.visitMaxs(1,1);
            //方法结束返回
            mv.visitEnd();
            Metaspace metaspace = new Metaspace();
            byte[] bytes = cw.toByteArray();
            Class<?> clazz = metaspace.defineClass("Class"+i,bytes,0,bytes.length);
            classes.add(clazz);
        }

        return classes;
    }
}
