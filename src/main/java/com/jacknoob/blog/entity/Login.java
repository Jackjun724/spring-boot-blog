package com.jacknoob.blog.entity;

public class Login {
    private Integer id;

    private String user;

    private String password;

    public Login(Integer id, String user, String password, Integer typeId) {
        this.id = id;
        this.user = user;
        this.password = password;
    }

    public Login() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}