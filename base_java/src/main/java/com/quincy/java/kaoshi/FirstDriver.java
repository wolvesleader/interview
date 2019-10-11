package com.quincy.java.kaoshi;

import com.quincy.java.kaoshi.pojo.User;

/**
 * author:quincy
 * Date:2019-08-12
 * 小红，小花，小黑，小明
 */
public class FirstDriver {

    public static void main(String[] args) {
        User user1 = new User(1,"小红");
        User user2 = new User(2,"小花");
        User user3 = new User(3,"小黑");
        User user4 = new User(4,"小明");
        user1.setNext(user2);
        user2.setNext(user3);
        user3.setNext(user4);
        user4.setNext(null);
        printPoint(user1);
    }

    /**
     * 遍历查看位置元素
     * @param user
     */
    public static void printPoint(User user){
        while (user != null){
            System.out.print(user.getUsername().concat(" "));
            user = user.getNext();
        }
    }
}
