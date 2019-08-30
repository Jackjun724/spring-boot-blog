package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.Reply;

import java.util.List;
import java.util.Map;

public interface ReplyMapper {
    int insert(Reply record);

    int insertSelective(Reply record);

    List<Reply> getTop6Reply();

    List<Map<String, Object>> getWeekDayReplyCount();

    Integer getReplyCountByDay();
}