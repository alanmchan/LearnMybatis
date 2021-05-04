package com.cm.dao;

import com.cm.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    public List<Student> getStudents();

    public List<Student> getStudent2();

}
