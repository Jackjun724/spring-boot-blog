package cn.luckyvv.blog.mapper;

import cn.luckyvv.blog.entity.NoteHasTags;

public interface NoteHasTagsMapper {
    int insert(NoteHasTags record);

    int insertSelective(NoteHasTags record);
}