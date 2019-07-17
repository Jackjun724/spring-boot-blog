package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.Pv;

public interface PvMapper {
    int insert(Pv record);

    int insertSelective(Pv record);
}