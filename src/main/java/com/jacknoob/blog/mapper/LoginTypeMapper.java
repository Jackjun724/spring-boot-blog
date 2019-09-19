package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.LoginType;

public interface LoginTypeMapper {
    int insert(LoginType record);

    int insertSelective(LoginType record);
}