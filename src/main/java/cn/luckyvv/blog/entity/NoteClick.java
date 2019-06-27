package cn.luckyvv.blog.entity;

import java.util.Date;

public class NoteClick {
    private Integer id;

    private Integer noteid;

    private Integer clicknum;

    private Date date;

    public NoteClick(Integer id, Integer noteid, Integer clicknum, Date date) {
        this.id = id;
        this.noteid = noteid;
        this.clicknum = clicknum;
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

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public Integer getClicknum() {
        return clicknum;
    }

    public void setClicknum(Integer clicknum) {
        this.clicknum = clicknum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}