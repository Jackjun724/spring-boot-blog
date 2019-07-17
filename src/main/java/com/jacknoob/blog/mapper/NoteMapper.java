package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.Note;

public interface NoteMapper {
    int insert(Note record);

    int insertSelective(Note record);
}