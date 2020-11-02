package com.quincy.java.algorithm;



import java.util.*;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: Demo
 *
 * @author: quincy
 * Date: 2020/10/30 下午2:24
 * History:
 */

public class Demo {
    public static void main(String[] args) {
        /**
         * 年级, 姓名, 学号, 语文成绩，数据成绩，英文成绩]
         * [
         * [2, "zhangsan", 200023, 88, 95, 91]
         * [5, "lisi", 500008, 92, 90, 89]
         * [2, "wangwu", 200014, 95, 87, 90]
         * [5, "zhaoliu", 500009, 98, 97, 100]
         * ]
         */
        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setGrade(2);
        student.setName("zhangsan");
        student.setNo(200023);
        student.setChinese(88);
        student.setMath(95);

        Student student1 = new Student();
        student1.setGrade(5);
        student1.setName("lisi");
        student1.setNo(500008);
        student1.setChinese(92);
        student1.setMath(90);

        Student student2 = new Student();
        student2.setGrade(2);
        student2.setName("wangwu");
        student2.setNo(200014);
        student2.setChinese(95);
        student2.setMath(87);

        Student student3 = new Student();
        student3.setGrade(5);
        student3.setName("zhaoliu");
        student3.setNo(500009);
        student3.setChinese(98);
        student3.setMath(97);

        list.add(student);
        list.add(student1);
        list.add(student2);
        list.add(student3);


        list.stream().forEach(item -> System.out.println(item.toString()));

        Collections.sort(list);

        System.out.println("=================");
        list.stream().forEach(item -> System.out.println(item.toString()));


    }
}

class Student implements Comparable<Student>{

    private String name;
    private int grade;
    private int no;
    private int chinese;
    private int math;
    private int english;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return grade == student.grade &&
                no == student.no &&
                Double.compare(student.chinese, chinese) == 0 &&
                Double.compare(student.math, math) == 0 &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, grade, no, chinese, math);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public double getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public double getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getSum(){
        return  this.chinese + this.math + this.english;
    }

    public double getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    @Override
    public int compareTo(Student student) {
        //Student student = (Student) o;
        int result = 0;
        result = student.grade - this.grade;
        if (result == 0){
            result = student.getSum() - this.getSum();
        }
        if (result == 0){
            result = student.getNo() - this.getNo();
        }
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", no=" + no +
                ", chinese=" + chinese +
                ", math=" + math +
                '}';
    }
}
