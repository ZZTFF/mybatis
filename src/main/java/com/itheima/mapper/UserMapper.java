package com.itheima.mapper;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface UserMapper {
    List<User> selectAll();

//    使用注解开发
    @Select("select * from tb_user where id = #{id}")
    User selectById(int id);
}
