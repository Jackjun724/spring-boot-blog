package cn.luckyvv.blog.mapper;

import cn.luckyvv.blog.entity.Login;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

public interface LoginMapper {
    int insert(Login record);

    int insertSelective(Login record);

    /**
     * 根据帐号查询密码
     * @param username 帐号
     * @return 加密后的密码
     */
    @Select("select password from login where user=#{username}")
    Optional<String> findPasswordByUsername(@Param("username") String username);
}