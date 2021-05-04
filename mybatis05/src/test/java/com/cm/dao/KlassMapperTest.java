package com.cm.dao;

import com.cm.pojo.Klass;
import com.cm.pojo.Student;
import com.cm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class KlassMapperTest {
    @Test
    public void testGetKlass() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            KlassMapper klassMapper = sqlSession.getMapper(KlassMapper.class);
            List<Klass> klassList = klassMapper.getKlass();
            for (Klass klass : klassList) {
                System.out.println(klass);
            }
        }
    }

    @Test
    public void testGetKlassById() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            KlassMapper klassMapper = sqlSession.getMapper(KlassMapper.class);
            Klass klass = klassMapper.getKlassById(1);
            System.out.println(klass);
        }
    }

    @Test
    public void testGetKlassById2() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            KlassMapper klassMapper = sqlSession.getMapper(KlassMapper.class);
            Klass klass = klassMapper.getKlassById2(2);
            System.out.println(klass);
        }
    }
}
