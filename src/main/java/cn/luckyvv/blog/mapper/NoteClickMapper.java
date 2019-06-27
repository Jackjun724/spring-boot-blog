package cn.luckyvv.blog.mapper;

import cn.luckyvv.blog.entity.NoteClick;

public interface NoteClickMapper {
    int insert(NoteClick record);

    int insertSelective(NoteClick record);
}