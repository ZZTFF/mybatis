package com.itheima.test;

import com.itheima.mapper.BrandMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MybatisTest {

    //总结mybatis完成操作步骤
    //1.对于一个待查询的表，编写对应类
    //2.编写接口方法，返回类型，名称，参数
    //3.编写sql映射语句，更改名称空间，写sql语句
    //4.执行方法

    @Test
    public void testSelectAll() throws IOException {
        //1. 加载mybaits配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.1 获取UserMapper接口的代理对象，可以不再需要在UserMapper里多次查找sql语句的id
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAll();

        System.out.println(brands);

        sqlSession.close();
    }

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
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = brandMapper.selectById(id);

        System.out.println(brand);

        sqlSession.close();
    }

    //多条件查询
    @Test
    public void testSelectByCondition() throws IOException {
//       接受参数
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

//        多条件查询第二种，创建对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompany_name(companyName);
        brand.setBrand_name(brandName);

//        多条件第三种 map
        Map map = new HashMap();
        map.put("status", status);
        map.put("company_name", companyName);
        map.put("brand_name", brandName);

        //1. 加载mybaits配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();


        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

//      多条件查询第一种
//        List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
//
//        多条件查询第二种，
//        List<Brand> brands = brandMapper.selectByCondition(brand);

//        多条件第三种
        List<Brand> brands = brandMapper.selectByCondition(map);

        System.out.println(brands);

        sqlSession.close();
    }

    @Test
    public void testSelectByConditionSingle() throws IOException {
//       接受参数
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

//        多条件查询第二种，创建对象
        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setCompany_name(companyName);
//        brand.setBrand_name(brandName);

        //1. 加载mybaits配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        List<Brand> brands = brandMapper.selectByConditionSingle(brand);

        System.out.println(brands);

        sqlSession.close();

    }

//    插入数据
    @Test
    public void testAdd() throws IOException {
//       接受参数
        int status = 1;
        String companyName = "奥特曼";
        String brandName = "奥特曼";
        String description = "奥特曼无敌";
        int ordered = 400;


        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompany_name(companyName);
        brand.setBrand_name(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);

        //1. 加载mybaits配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        brandMapper.add(brand);

        System.out.println(brand.getId());
//        需要提交事务
        sqlSession.commit();

        sqlSession.close();

    }
//修改数据,不固定字段
    @Test
    public void testupdate() throws IOException {
//       接受参数
        int status = 1;
        String companyName = "迪迦奥特曼1111";
        String brandName = "迪迦奥特曼111";
        String description = "迪迦奥特曼无敌";
        int ordered = 200;
        int id = 5;

        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompany_name(companyName);
        brand.setBrand_name(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);
        brand.setId(id);

        //1. 加载mybaits配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int count = brandMapper.update(brand);
//        需要提交事务
        sqlSession.commit();

        sqlSession.close();

    }

//    单行删除
    @Test
    public void testdeletById() throws IOException {
//       接受参数
        int id = 7;

        //1. 加载mybaits配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

         brandMapper.deleteById(id);
//        需要提交事务
        sqlSession.commit();

        sqlSession.close();

    }

//    多行删除
    @Test
    public void testdeletByIds() throws IOException {
//       接受参数
        int ids[] = {10, 11};

        //1. 加载mybaits配置文件，获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        brandMapper.deleteByIds(ids);
//        需要提交事务
        sqlSession.commit();

        sqlSession.close();

    }



}
