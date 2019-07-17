package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.Tag;

public interface TagMapper {
    int insert(Tag record);

    int insertSelective(Tag record);
}