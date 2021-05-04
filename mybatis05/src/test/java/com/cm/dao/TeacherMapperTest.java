package com.cm.dao;

import com.cm.pojo.Teacher;
import com.cm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TeacherMapperTest {

    @Test
    public void testGetTeacherById() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
            Teacher teacher = teacherMapper.getTeacherById(1);
            System.out.println(teacher);
        }
    }
}
