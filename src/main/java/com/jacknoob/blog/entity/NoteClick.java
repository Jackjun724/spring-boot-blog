package com.jacknoob.blog.entity;

import java.util.Date;

public class NoteClick {
    private Integer id;

    private Integer noteId;

    private Integer clickNum;

    private Date date;

    public NoteClick(Integer id, Integer noteId, Integer clickNum, Date date) {
        this.id = id;
        this.noteId = noteId;
        this.clickNum = clickNum;
        this.date = date;
    }

    public NoteClick() {
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

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}