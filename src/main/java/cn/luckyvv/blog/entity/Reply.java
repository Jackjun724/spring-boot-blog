package cn.luckyvv.blog.entity;

import java.util.Date;

public class Reply {
    private Integer id;

    private Integer noteId;

    private String reply;

    private String name;

    private Integer replyId;

    private String ip;

    private Integer status;

    private Date date;

    public Reply(Integer id, Integer noteId, String reply, String name, Integer replyId, String ip, Integer status, Date date) {
        this.id = id;
        this.noteId = noteId;
        this.reply = reply;
        this.name = name;
        this.replyId = replyId;
        this.ip = ip;
        this.status = status;
        this.date = date;
    }

    public Reply() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}