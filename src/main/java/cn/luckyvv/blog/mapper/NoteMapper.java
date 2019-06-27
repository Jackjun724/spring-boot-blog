package cn.luckyvv.blog.mapper;

import cn.luckyvv.blog.entity.Note;

public interface NoteMapper {
    int insert(Note record);

    int insertSelective(Note record);
}