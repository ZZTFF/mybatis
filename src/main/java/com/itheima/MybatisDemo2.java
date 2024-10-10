package com.itheima;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//mapper代理开发

//1.定义与sql映射文件同名的Mapper接口，并且将他们两个放到同一个目录下
//2.设置sql映射文件的namespace属性为Mapper
//3.在Mapper接口中定义方法，方法名就是sql映射文件中sql语句的id。
// 保持参数类型和返回值类型一致
public class MybatisDemo2 {
    public static void main(String[] args) throws IOException {
        //1. 加载mybaits配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.返回list集合
        //List<User> users = sqlSession.selectList("test.selectAll");

        //3.1 获取UserMapper接口的代理对象，可以不再需要在UserMapper里多次查找sql语句的id
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();

        System.out.println(users);

        sqlSession.close();
    }
}
