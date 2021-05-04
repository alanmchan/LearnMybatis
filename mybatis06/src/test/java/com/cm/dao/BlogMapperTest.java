package com.cm.dao;

import com.cm.pojo.Blog;
import com.cm.utils.IDUtils;
import com.cm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class BlogMapperTest {
    @Test
    public void testAddBlog() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

            Blog blog = new Blog();
            blog.setId(IDUtils.genId());
            blog.setTitle("Java 学习");
            blog.setAuthor("alan");
            blog.setCreateTime(new Date());
            blog.setViews(10);

            blogMapper.addBlog(blog);

            blog.setId(IDUtils.genId());
            blog.setTitle("Mybatis");
            blogMapper.addBlog(blog);

            blog.setId(IDUtils.genId());
            blog.setTitle("Spring");
            blogMapper.addBlog(blog);

            blog.setId(IDUtils.genId());
            blog.setTitle("微服务");
            blogMapper.addBlog(blog);
        }
    }

    @Test
    public void testQueryBlogIf() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Map<String, Object> map = new HashMap<>();
            List<Blog> blogs = mapper.queryBlogIf(map);
            System.out.println(blogs.size());
            for (Blog blog : blogs) {
                System.out.println(blog);
            }

            map.put("title", "mybatis");
            blogs = mapper.queryBlogIf(map);
            System.out.println(blogs.size());
            for (Blog blog : blogs) {
                System.out.println(blog);
            }

            map.put("author", "cm");
            blogs = mapper.queryBlogIf(map);
            System.out.println(blogs.size());
            for (Blog blog : blogs) {
                System.out.println(blog);
            }
        }
    }

    @Test
    public void testQueryBlogChoose() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Map<String, Object> map = new HashMap<>();
            List<Blog> blogs = mapper.queryBlogChoose(map);
            System.out.println(blogs.size());
            for (Blog blog : blogs) {
                System.out.println(blog);
            }

            map.put("title", "mybatis");
            blogs = mapper.queryBlogChoose(map);
            System.out.println(blogs.size());
            for (Blog blog : blogs) {
                System.out.println(blog);
            }

            map.put("author", "cm");
            blogs = mapper.queryBlogChoose(map);
            System.out.println(blogs.size());
            for (Blog blog : blogs) {
                System.out.println(blog);
            }
        }
    }

    @Test
    public void testUpdateBlog() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Map map = new HashMap();
//            map.put("title", "learn mybatis");
            map.put("author", "cm");
//            map.put("id", "2a52cbd30d3d474bba4db2db636835cd");
            mapper.updateBlog(map);
        }
    }

    @Test
    public void testQueryBlogForeach() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Map map = new HashMap();

            List<Integer> ids = new ArrayList<Integer>();
            ids.add(1);

            map.put("ids", ids);
            List<Blog> blogs = mapper.queryBlogForeach(map);
            for (Blog blog : blogs) {
                System.out.println(blog);
            }
        }
    }
}
