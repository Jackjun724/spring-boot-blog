package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagMapper {
    int insert(Tag record);

    int insertSelective(Tag record);

    List<Tag> getAllTags();

    String getTagNameById(int id);

    /**
     * 插入不存在的标签
     *
     * @param tagList 标签名称列表
     */
    void insertIgnore(@Param("tagList") List<String> tagList);

    /**
     * 根据名字列表查Id列表
     *
     * @param tags 标签名称列表
     * @return id List
     */
    List<Integer> selectiveSelect(@Param("tags") List<String> tags);

    /**
     * 根据名字列表查Id列表
     *
     * @param tags 标签名称列表
     * @return id List
     */
    Page<Integer> testMethod(@Param("tags") List<String> tags, Pageable test);
}