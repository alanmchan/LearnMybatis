package com.cm.dao;

import com.cm.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    public User getUserById(@Param("id") int id);
}

