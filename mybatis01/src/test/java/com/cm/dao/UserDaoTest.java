package com.cm.dao;

import com.cm.pojo.User;
import com.cm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    @Test
    public void test() {
        // 获取sqlSession
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            // 方法1
//            List<User> users = sqlSession.selectList("com.cm.dao.UserDao.getUserList");

            // 方法2 获取Mapper/DAO 实现类, 推荐使用
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            // 执行方法
            List<User> users = userDao.getUserList();

            for (User user :
                    users) {
                System.out.println(user);
            }
        }
    }

    @Test
    public void testGetUserById() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            System.out.println(userDao.getUserById(1));
        }
    }

    @Test
    public void testGetUserLike() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            List<User> users = userDao.getUserLike("z");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    @Test
    public void testAddUser() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            try {
                int res = userDao.addUser(new User(3, "ww", "123456"));
                System.out.println(res);
                sqlSession.commit();  // 提交事务
            } catch (Exception e) {
                e.printStackTrace();
                sqlSession.rollback();
            }

        }
    }

    @Test
    public void testAddUser2() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            try {
                Map<String, Object> map = new HashMap<>();
                map.put("code", 4);
                map.put("username", "zl");
                map.put("password", "123123");

                int res = userDao.addUser2(map);
                System.out.println(res);
                sqlSession.commit();  // 提交事务
            } catch (Exception e) {
                e.printStackTrace();
                sqlSession.rollback();
            }

        }
    }

    @Test
    public void testUpdateUser() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            try {
                int res = userDao.updateUser(new User(3, "ww", "654321"));
                System.out.println(res);
                sqlSession.commit();
            } catch (Exception e) {
                e.printStackTrace();
                sqlSession.rollback();
            }
        }
    }

    @Test
    public void testDeleteUser() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            try {
                int res = userDao.deleteUser(3);
                System.out.println(res);
                sqlSession.commit();
            } catch (Exception e) {
                e.printStackTrace();
                sqlSession.rollback();
            }
        }
    }
}
