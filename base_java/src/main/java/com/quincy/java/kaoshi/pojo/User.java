package com.quincy.java.kaoshi.pojo;

/**
 * author:quincy
 * Date:2019-08-12
 */
public class User {

    private Integer id;
    private String username;
    private User next;

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getNext() {
        return next;
    }

    public void setNext(User next) {
        this.next = next;
    }
}
