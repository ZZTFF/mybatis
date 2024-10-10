package com.itheima.mapper;


import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/*mybatis接口方法参数的封装处理方式
单个参数
1.POJO(实体类)类型:直接使用,属性名与占位符名称一致
2.Map集合:直接使用,键名与占位符名称一致
3.Collection:封装为Map集合,用@Param注解,替换Map集合默认键名
map.put("arg0", collection集合)
map.put("collection", collection集合)
4.List,用@Param注解,替换Map集合默认键名
map.put("arg0", list集合)
map.put("collection", list集合)
map.put("list", list集合)
5.Array:封装为Map集合,用@Param注解,替换Map集合默认键名
map.put("arg0", 数组)
map.put("array", 数组)
6.其他类型  直接使用

多个参数:封装为Map集合,使用@Param注解,替换Map集合默认键名,比如将传进来的String username的键名更新为username
map.put("arg0", 参数值1)   ---->map.put("username", 参数值1)
map.put("param1", 参数值1)     map.put("param1", 参数值1)
map.put("param2", 参数值2)     map.put("param2", 参数值2)
map.put("arg1", 参数值2)       map.put("arg1", 参数值2)

方式一:
User select(@Param("username") String username, @Param("password") String password);
同时sql语句中对应为#{username}, #{password}
方式二:
User select(String username,  String password);
同时sql语句中对应为#{arg0}, #{arg1}

执行方法 String username = "jack";String password = "123";
User user = userMapper.select(username, password);


*/

public interface BrandMapper {
    //    查询所有
    List<Brand> selectAll();

    //    查看详情
    Brand selectById(int id);

//多条件查询
//    1.需要" "里与sql映射里面{ }变量名保持一致
//    List<Brand> selectByCondition(@Param("status") int a, @Param("companyName") String b, @Param("brandName") String c);

//    2.创建Brand对象
//    List<Brand> selectByCondition(Brand brand);


//    3.map对象
    List<Brand> selectByCondition(Map map);

//单条件动态查询
    List<Brand> selectByConditionSingle(Brand brand);

//    添加数据
    void add(Brand brand);

//    修改随机数据
    int update(Brand brand);


//    删除单行
    void deleteById(int id);

//    删除多行
    void deleteByIds(@Param("ids") int[] ids);


}



