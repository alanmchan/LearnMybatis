# Mybatis笔记

## 第一个Mybatis程序

- pom中导入依赖

```xml
<!-- pom.xml -->
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.20</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.6</version>
    </dependency>
</dependencies>
```

如有必要，需要解决Maven静态资源过来问题

```xml
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```

- 编写Mybatis核心配置文件 `mybatis-config.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">


<configuration>

    <!-- 引入外部配置 -->
    <properties resource="db.properties"/>

    <settings>
        <!-- 配置日志 -->
        <setting name="logImpl" value="LOG4J"/>

        <!-- 蛇形命名转驼峰命名 -->
        <setting name="mapUnderscoreToCamelCase" value="true"/>

        <!-- 显示启用二级缓存 -->
        <setting name="cacheEnabled" value="true"/>
    </settings>

    <!-- 给实体类起别名 -->
    <typeAliases>
        <!-- 方法一 -->
        <!-- <typeAlias type="com.cm.pojo.Blog" alias="Blog"/> -->

        <!-- 方法二, 整个包都会取别名, 别名为类名小写，也可以通过注解指定别名 -->
        <package name="com.cm.pojo"/>
    </typeAliases>

    <!-- 配置数据库链接 -->
    <environments default="test">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/test?useSSL=true&amp;useUnicode=true&amp;charsetEncoding=utf8&amp;serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>

        <environment id="test">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>

    <!-- 注册Mapper -->
    <mappers>
        <mapper class="com.cm.dao.UserMapper"/>
        <!--        <mapper resource="com/cm/dao/BlogMapper.xml"/>-->
    </mappers>

</configuration>
```

- 编写Mybatis工具类

```java
package com.cm.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Mybatis Utils get sqlSessionFactory
 * sqlSessionFactor get sqlSession
 */
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get SqlSession
     * set auto commit
     * @return SqlSession
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }
}
```

说明：

1. 利用SqlSessionFactorBuilder构建SqlSessionFactory
2. 从SqlSession中获取SqlSession, 再进行数据库操作
3. SqlSessionFactor相当于是连接池，需要使用单例模式
4. SqlSession是连接，用完放回连接池

- 创建实体类

```java
package com.cm.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String pwd;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public User() {
    }

    public User(int id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
```

- 编写Mapper接口

类似于DAO

```java
package com.cm.dao;

import com.cm.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    public User getUserById(@Param("id") int id);
}
```

- 编写Mapper.xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- namespace 对应要实现的接口 -->
<mapper namespace="com.cm.dao.UserMapper">
    
    <!-- id 对应方法， resultType 对应返回数据类型，如果没有配置别名，需要写全类名 -->
    <select id="getUserById" resultType="com.cm.pojo.User">
        select * from user where id = #{id}
    </select>

</mapper>
```

- 在Mybatis核心配置文件中注册Mapper

见`mybatis-config.xml`的 `<mapper>` 标签

- 测试

```java
package com.cm.dao;

import com.cm.pojo.User;
import com.cm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class UserMapperTest {
    
    @Test
    public void testGetUserById() {

        // 获取SqlSession， 建议写在try-resource中
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        
        // 获取Mapper
        // 方法一
        // User user = sqlSession.selectOne("com.cm.dao.UserMapper.getUserById", 1);
        
        // 方法二
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(1);
        
        System.out.println(user);
        sqlSession.close();
    }
}
```

## CRUD

## Mybatis核心配置说明

## 复杂sql， 一对多，多对一问题

## 动态sql

## 缓存
