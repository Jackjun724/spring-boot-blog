package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.File;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jackjun
 */
public interface FileMapper {
    int insert(File record);

    int insertSelective(File record);

    List<File> getFileList();

    List<File> getFileListByType(@Param("type") Integer type);
}