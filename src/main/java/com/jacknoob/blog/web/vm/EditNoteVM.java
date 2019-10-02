package com.jacknoob.blog.web.vm;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author JackJun
 * 2019/7/30 17:21
 * Life is just about survival.
 */
public class EditNoteVM {

    private Integer id;

    @NotNull(message = "标题不能为空!")
    @Size(max = 50, message = "标题长度不能超过50个字符!")
    private String title;

    private Date publishTime;

    private Date lastUpdateTime;

    @NotNull(message = "内容不能为空!")
    private String content;

    @NotNull(message = "类型不能为空!")
    @Max(value = 3, message = "类型错误!")
    @Min(value = 1, message = "类型错误!")
    private Integer displayType;

    @NotNull(message = "摘要内容不能为空!")
    private String displayContent;

    private String html;

    private List<String> tags;

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
        this.title = title;
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
        this.content = content;
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
        this.displayContent = displayContent;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
