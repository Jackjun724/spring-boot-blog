package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.FileMapping;
import org.apache.ibatis.annotations.Param;

/**
 * @author JackJun
 * 2019/9/14 23:54
 * Life is not just about survival.
 */
public interface FileMappingMapper {

    void insert(FileMapping fileMapping);

    String getPathByKey(@Param("key") String key);
}
