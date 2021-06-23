package com.quincy.mybatis.pojo;

/**
 * @Copyright (C), 2015-2021, 大众易书天津科技有限公司
 * @FileName: Student
 * @author: quincy
 * @Date: 2021/6/23 下午2:24
 * @History:
 */

public class Student {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
