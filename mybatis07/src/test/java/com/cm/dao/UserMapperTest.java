package com.cm.dao;

import com.cm.pojo.User;
import com.cm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class UserMapperTest {
    /* 测试一级缓存 日志中只有一条sql语句，如果进行了清理，会有两条   一级缓存是默认开启的 */
    @Test
    public void testGetUserById() {
        // 测试一级缓存
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user1 = userMapper.getUserById(1);
        System.out.println(user1);

        System.out.println("==========================================");
//        sqlSession.clearCache();

        User user2 = userMapper.getUserById(1);
        System.out.println(user2);
        System.out.println(user1 == user2);

        sqlSession.close();
    }

    /* 测试二级缓存, 日志中只有一条sql语句， 如果二级缓存关闭，日志中会有两条sql语句 */
    @Test
    public void testGetUserById2() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user1 = userMapper.getUserById(2);
        System.out.println(user1);
        sqlSession.close();


        SqlSession sqlSession2 = MybatisUtils.getSqlSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.getUserById(2);
        System.out.println(user2);
        sqlSession2.close();

        System.out.println(user1 == user2);
    }
}
