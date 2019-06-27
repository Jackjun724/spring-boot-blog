package cn.luckyvv.blog.mapper;

import cn.luckyvv.blog.entity.Tag;

public interface TagMapper {
    int insert(Tag record);

    int insertSelective(Tag record);
}