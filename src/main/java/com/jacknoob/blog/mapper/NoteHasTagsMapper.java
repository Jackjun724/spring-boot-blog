package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.NoteHasTags;

public interface NoteHasTagsMapper {
    int insert(NoteHasTags record);

    int insertSelective(NoteHasTags record);
}