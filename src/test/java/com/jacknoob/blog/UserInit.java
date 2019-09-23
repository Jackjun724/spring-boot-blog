package com.jacknoob.blog;

import com.jacknoob.blog.common.Constants;
import com.jacknoob.blog.entity.Login;
import com.jacknoob.blog.mapper.LoginMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInit {

    private static LoginMapper mapper;
    @Inject
    private PasswordEncoder passwordEncoder;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        //设置active的profile
        System.setProperty(Constants.SPRING_PROFILE_KEY, "prod");
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(UserInit.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/LoginMapperTestConfiguration.xml"));
        mapper = builder.getConfiguration().getMapper(LoginMapper.class, builder.openSession(true));
    }

    @Test
    public void contextLoads() {
        // 初始化创建一个管理员账户
        Login user = new Login();
        user.setUser("root");
        user.setPassword(passwordEncoder.encode("123123"));
        mapper.insert(user);
    }

}
