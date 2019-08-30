package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.NoteHasTags;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoteHasTagsMapper {
    int insert(NoteHasTags record);

    int insertSelective(NoteHasTags record);

    int insertList(@Param("tagsId") List<Integer> tagsId, @Param("noteId") int noteId);

    int deleleSelective(Integer id);
}