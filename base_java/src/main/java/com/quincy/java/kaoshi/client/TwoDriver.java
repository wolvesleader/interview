package com.quincy.java.kaoshi.client;

import com.quincy.java.kaoshi.pojo.TwoUser;
import com.quincy.java.kaoshi.service.UserService;
import com.quincy.java.kaoshi.service.impl.UserServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * author:quincy
 * Date:2019-08-12
 *
 * 1.	大众易书是一个外包公司，客户小强有一些用户的手机号码，存放在txt文件中数据，一行只存放一个手机号码，
 * 客户小强委托大众易书做一个系统，对电话号码做一些处理，系统需求如下所示
 * 1）	读取txt文件中的手机号码，做长度校验，如果长度为11为，是正确的号码，不是在控制台输出提示信息
 * 2）	如果手机号码校验正确，把用户信息存入到数据库，用户信息包含用户id、用户名、用户手机号（表需要自己创建）
 * 3）	根据手机号码和用户id查找用户，在控制台输出
 * 4）	根据用户名（用户名可以重复）查询出多个用户，存放在集合中，循环遍历集合中的用户元素
 */
public class TwoDriver {




    public static void main(String[] args) throws Exception{

        UserService userService = new UserServiceImpl();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Volumes/quincy/jobinterview/basic_java/base_java/src/main/java/com/quincy/java/kaoshi/userdata.txt")));
        String line = null;
        while ((line = bufferedReader.readLine()) != null){
            String[] split = line.split(":");
            if (split[1].length() != 11){
                System.out.println("输入格式不正确");
            }else{
                TwoUser twoUser = new TwoUser();
                twoUser.setUsername(split[0]);
                twoUser.setPhone(split[1]);
                userService.save(twoUser);
            }
        }
    }
}
