package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.Reply;

public interface ReplyMapper {
    int insert(Reply record);

    int insertSelective(Reply record);
}