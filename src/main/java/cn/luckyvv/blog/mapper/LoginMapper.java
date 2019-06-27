package cn.luckyvv.blog.mapper;

import cn.luckyvv.blog.entity.Login;

public interface LoginMapper {
    int insert(Login record);

    int insertSelective(Login record);
}