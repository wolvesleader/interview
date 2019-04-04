package com.quincy.java.rpc.v1.server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by quincy on 2018/7/9.
 */
public class Export {

    public static void main(String[] args) {


        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());


        try {
            UserService userService = new UserServiceImpl();
            //netty
            ServerSocket serverSocket = new ServerSocket(8088);
            System.out.println("服务启动");
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream inputObject = new ObjectInputStream(inputStream);
            //读接口完全路径名
            String serviceName = inputObject.readUTF();
            //获取接口的Class文件
            Class<?> serviceClass = Class.forName(serviceName);
            //读方法名
            String methodName = inputObject.readUTF();
            //读方法的参数类型
            Class[] paramsType = (Class[]) inputObject.readObject();
            //获取到需要执行的目标方法
            Method method = serviceClass.getMethod(methodName, paramsType);
            //需要获取到参数
            Object[] paramsValue = (Object[]) inputObject.readObject();
            Object result = method.invoke(userService, paramsValue);

            //需要把方法的执行结果响应给客户端
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream outputObject = new ObjectOutputStream(outputStream);
            outputObject.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭流的操作
        }
    }
}
