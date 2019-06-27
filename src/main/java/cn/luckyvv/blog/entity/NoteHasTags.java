package cn.luckyvv.blog.entity;

public class NoteHasTags {
    private Integer id;

    private Integer tagId;

    private Integer noteId;

    public NoteHasTags(Integer id, Integer tagId, Integer noteId) {
        this.id = id;
        this.tagId = tagId;
        this.noteId = noteId;
    }

    public NoteHasTags() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }
}