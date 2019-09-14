package com.jacknoob.blog.entity;

/**
 * @author JackJun
 * 2019/9/14 23:51
 * Life is not just about survival.
 */
public class FileMapping {
    private Integer id;
    private String path;
    private String key;

    public FileMapping() {
    }

    public FileMapping(String path, String key) {
        this.path = path;
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
