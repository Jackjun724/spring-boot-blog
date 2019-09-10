package com.jacknoob.blog.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * @author JackJun
 * 09/09/2019 18:58
 * Life is not just about survival.
 */
public class FileMapperTest {
    private static FileMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(FileMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/FileMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(FileMapper.class, builder.openSession(true));
    }

    @Test
    public void testInsertSelective() throws FileNotFoundException {
//        mapper.insertSelective();
    }
}
