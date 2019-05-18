package com.quincy.java.designpatterns.builder;

/**
 * author:quincy
 * Date:2019-05-18
 */
public class User {

    private String id;
    private String username;
    private String age;
    private String sex;
    private String address;
    private String school;


    public User(Builder builder){
        this.id = builder.id;
        this.username = builder.username;
        this.age = builder.age;
        this.sex = builder.sex;
        this.address = builder.address;
        this.school = builder.school;
    }


    public static final class Builder {
        private String id;
        private  String username;
        private  String age;
        private  String sex;
        private  String address;
        private  String school;

        public User.Builder addId(String id){
            this.id = id;
            return this;
        }
        public User.Builder addUsername(String username){
            this.username = username;
            return this;
        }
        public User.Builder addAge(String age){
            this.age = age;
            return this;
        }
        public User.Builder addSex(String sex){
            this.sex = sex;
            return this;
        }
        public User.Builder addAddress(String address){
            this.address = address;
            return this;
        }
        public User.Builder addSchool(String school){
            this.school = school;
            return this;
        }

        public User build(){
            return new User(this);
        }

    }


    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public String getSchool() {
        return school;
    }
}
