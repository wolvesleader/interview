package com.quincy.java.rpc.v1.client;

import com.quincy.java.rpc.v1.pojo.User;
import com.quincy.java.rpc.v1.server.UserService;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Created by quincy on 2018/7/9.
 */

/**
 * try {
 UserService userService = new UserServiceImpl();
 ServerSocket serverSocket = new ServerSocket(8088);
 System.out.println("服务启动");
 Socket socket = serverSocket.accept();
 InputStream inputStream = socket.getInputStream();
 ObjectInputStream inputObject = new ObjectInputStream(inputStream);
 //读方法名
 String methodName = inputObject.readUTF();
 //读方法的参数类型
 Class[] params = (Class[]) inputObject.readObject();
 //获取到需要执行的目标方法
 Method method = userService.getClass().getMethod(methodName, params);

 Object result = method.invoke(userService, params);

 //需要把方法的执行结果响应给客户端
 OutputStream outputStream = socket.getOutputStream();
 ObjectOutputStream outputObject = new ObjectOutputStream(outputStream);
 outputObject.writeObject(result);
 } catch (Exception e) {
 e.printStackTrace();
 }finally {
 //关闭流的操作
 }
 netty
 */
public class Ref {
    public static void main(String[] args) {


        //UserService.class.getClassLoader()在spring的xml文件中提供

        UserService service =(UserService)Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class<?>[]{UserService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {
                    Socket socket = new Socket("localhost",8088);
                    OutputStream outputStream = socket.getOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                    System.out.println(UserService.class.getName());

                    //写接口的完全路径名
                    objectOutputStream.writeUTF(UserService.class.getName());
                    //写方法名
                    objectOutputStream.writeUTF(method.getName());
                    //写方法的参数类型
                    objectOutputStream.writeObject(method.getParameterTypes());
                    //写方法的参数
                    objectOutputStream.writeObject(args);

                    //接受响应回来的对象
                    InputStream inputStream = socket.getInputStream();
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                    return objectInputStream.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //关闭流的操作
                }
                return null;
            }
        });

        User nihaoa = service.save("nihaoa","123456");
        System.out.println(nihaoa);


    }
}
