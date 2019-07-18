package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.Note;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoteMapper {
    int insert(Note record);

    int insertSelective(Note record);

    /**
     * @return 启用的文章总数
     */
    int enableCount();

    /**
     * @return 置顶文章总数
     */
    int enableTopCount();

    /**
     * @return 前台可以看到的文章列表
     */
    List<Map<String, Object>> enableList();

    /**
     * @return 前台可以看到的置顶文章列表
     */
    List<Map<String, Object>> enableTopList();

    Note getNoteById(@Param("id") Integer id);

    List<Map<String, Object>> loadMore();

    List<Note> getNoteByTagId(Integer id);
}