package com.cm.dao;

import com.cm.pojo.Student;
import com.cm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class StudentMapperTest {
    @Test
    public void testGetStudents() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> studentList = studentMapper.getStudents();
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    @Test
    public void testGetStudents2() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> studentList = studentMapper.getStudent2();
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }
}
