package com.quincy.java.base.abstractconstructor;

/**
 * author:quincy
 * Date:2019-03-21
 */
public abstract class Person {
    private String id;
    private String username;


    public Person(){
        System.out.println("Person");
    }

    public Person(String id,String username){
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
