package cn.luckyvv.blog.entity;

import java.util.Date;

public class Tag {
    private Integer id;

    private String title;

    private Date publishTime;

    public Tag(Integer id, String title, Date publishTime) {
        this.id = id;
        this.title = title;
        this.publishTime = publishTime;
    }

    public Tag() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}