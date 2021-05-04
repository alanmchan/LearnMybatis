package com.cm.dao;

import com.cm.pojo.User;
import com.cm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UserMapperTest {

    @Test
    public void testGetUserById() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            System.out.println(mapper.getUserById(1));
        }
    }

    @Test
    public void testAddUser() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            System.out.println(mapper.addUser(new User(5, "alan", "123123")));
        }
    }

    @Test
    public void testUpdateUser() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 5);
            map.put("password", "456456");
            System.out.println(mapper.updateUser(map));
        }
    }

    @Test
    public void testDeleteUser() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            System.out.println(mapper.deleteUser(5));
        }
    }
}
