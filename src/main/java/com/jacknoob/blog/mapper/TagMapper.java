package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.Tag;
import org.apache.ibatis.annotations.Param;

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
     * 根据name删除标签
     * @param tagName 标签name
     * @return 受影响行数
     */
    int deleteTagByName(String tagName);

    int validTagIsUsing(@Param("name") String tagName);
}