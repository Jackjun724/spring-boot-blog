package cn.luckyvv.blog.mapper;

import cn.luckyvv.blog.entity.Pv;

public interface PvMapper {
    int insert(Pv record);

    int insertSelective(Pv record);
}