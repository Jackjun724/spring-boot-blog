package com.jacknoob.blog.web.rest.vm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author JackJun
 * 2019/7/1 19:46
 * Life is not just about survival.
 */
public class UserVM {

    @NotNull(message = "帐号不能为空")
    @Size(max = 16,message = "帐号长度过长")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(max = 16,message = "密码长度过长")
    private String password;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
