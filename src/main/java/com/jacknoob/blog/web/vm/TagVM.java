package com.jacknoob.blog.web.vm;

/**
 * @author JackJun
 * 2019/7/30 16:42
 * Life is just about survival.
 */
public class TagVM {
    private Integer id;
    private String value;

    public TagVM() {
    }

    public TagVM(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
