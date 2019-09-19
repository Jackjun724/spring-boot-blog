package com.jacknoob.blog.entity;

public class LoginType {
    private Integer id;

    private String tName;

    public LoginType(Integer id, String tName) {
        this.id = id;
        this.tName = tName;
    }

    public LoginType() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName == null ? null : tName.trim();
    }
}