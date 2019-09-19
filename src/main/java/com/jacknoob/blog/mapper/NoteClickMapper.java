package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.NoteClick;

public interface NoteClickMapper {
    int insert(NoteClick record);

    int insertSelective(NoteClick record);

    void insertClick(Integer id);

    int clickNum(Integer id);
}