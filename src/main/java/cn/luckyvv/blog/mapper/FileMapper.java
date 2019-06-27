package cn.luckyvv.blog.mapper;

import cn.luckyvv.blog.entity.File;

public interface FileMapper {
    int insert(File record);

    int insertSelective(File record);
}