package com.itheima.test;

import com.itheima.mapper.BrandMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserMapperTest {
    @Test
    public void testSelectById() throws IOException {
        int id = 1;
        //1. 加载mybaits配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);




        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.1 获取UserMapper接口的代理对象，可以不再需要在UserMapper里多次查找sql语句的id
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectById(id);
        System.out.println(user);
        sqlSession.close();
    }
}
