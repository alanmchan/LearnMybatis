package com.cm.dao;

import com.cm.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * JDBC的写法会写实现类来访问数据库
 *
 * 但是Mybatis中，是通过配置文件
 */
public interface UserMapper {
    // get all users
    List<User> getUserList();

    User getUserById(int id);

    List<User> getUserLike(String value);

    int addUser(User user);

    int addUser2(Map<String, Object> map);

    int updateUser(User user);

    int deleteUser(int id);
}
