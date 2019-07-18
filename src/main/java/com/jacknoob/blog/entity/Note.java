package com.jacknoob.blog.entity;

import java.util.Date;
import java.util.List;

public class Note {
    private Integer id;

    private String title;

    private Date publishTime;

    private Date lastUpdateTime;

    private String content;

    private Integer displayType;

    private String displayContent;

    private List<Tag> tags;

    public Note(Integer id, String title, Date publishTime, Date lastUpdateTime, String content, Integer displayType) {
        this.id = id;
        this.title = title;
        this.publishTime = publishTime;
        this.lastUpdateTime = lastUpdateTime;
        this.content = content;
        this.displayType = displayType;
    }

    public Note(Integer id, String title, Date publishTime, Date lastUpdateTime, String content, Integer displayType, String displayContent) {
        this.id = id;
        this.title = title;
        this.publishTime = publishTime;
        this.lastUpdateTime = lastUpdateTime;
        this.content = content;
        this.displayType = displayType;
        this.displayContent = displayContent;
    }

    public Note() {
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getDisplayType() {
        return displayType;
    }

    public void setDisplayType(Integer displayType) {
        this.displayType = displayType;
    }

    public String getDisplayContent() {
        return displayContent;
    }

    public void setDisplayContent(String displayContent) {
        this.displayContent = displayContent == null ? null : displayContent.trim();
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}