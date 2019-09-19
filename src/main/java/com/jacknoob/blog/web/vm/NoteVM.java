package com.jacknoob.blog.web.vm;

import com.jacknoob.blog.entity.Note;

/**
 * @author JackJun
 * 2019/7/30 14:57
 * Life is just about survival.
 */
public class NoteVM {
    private Integer click;
    private Note note;
    private Integer len;

    public NoteVM() {
    }

    public NoteVM(Integer click, Note note, Integer len) {
        this.click = click;
        this.note = note;
        this.len = len;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }
}
