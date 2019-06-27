package cn.luckyvv.blog.mapper;

import cn.luckyvv.blog.entity.Reply;

public interface ReplyMapper {
    int insert(Reply record);

    int insertSelective(Reply record);
}