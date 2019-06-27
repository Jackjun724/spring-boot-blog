package cn.luckyvv.blog.mapper;

import cn.luckyvv.blog.entity.LoginType;

public interface LoginTypeMapper {
    int insert(LoginType record);

    int insertSelective(LoginType record);
}