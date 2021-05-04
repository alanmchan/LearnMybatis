package com.cm.dao;

import com.cm.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * JDBC的写法会写实现类来访问数据库
 * <p>
 * 但是Mybatis中，是通过配置文件
 */
public interface UserMapper {

    // 方法有多个参数，所有参数前面必须加上Param注解, 引用对象不需要加注解，String除外
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") int id);

    @Insert("insert into test.user (id, name, pwd) values (#{id}, #{name}, #{password})")
    int addUser(User user);

    @Update("update user set pwd = #{password} where id = #{id}")
    int updateUser(Map<String, Object> map);

    @Delete("delete from user where id = #{id}")
    int deleteUser(@Param("id") int id);
}
