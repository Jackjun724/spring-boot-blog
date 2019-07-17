package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.File;

public interface FileMapper {
    int insert(File record);

    int insertSelective(File record);
}