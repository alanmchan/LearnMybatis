package com.cm.dao;

import com.cm.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {

    @Select("select * from teacher where id = #{tid}")
    public Teacher getTeacherById(@Param("tid") int tid);
}
