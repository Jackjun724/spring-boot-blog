package com.jacknoob.blog.entity;

import java.util.Date;

public class Pv {
    private Integer id;

    private Date date;

    private Integer num;

    public Pv(Integer id, Date date, Integer num) {
        this.id = id;
        this.date = date;
        this.num = num;
    }

    public Pv() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}