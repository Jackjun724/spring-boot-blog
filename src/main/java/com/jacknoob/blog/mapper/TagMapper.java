package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.Tag;

import java.util.List;

public interface TagMapper {
    int insert(Tag record);

    int insertSelective(Tag record);

    List<Tag> getAllTags();

    String getTagNameById(int id);
}