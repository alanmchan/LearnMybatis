package com.cm.dao;

import com.cm.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    public int addBlog(Blog blog);

    public List<Blog> queryBlogIf(Map<String, Object> map);

    public List<Blog> queryBlogChoose(Map map);

    public int updateBlog(Map map);

    public List<Blog> queryBlogForeach(Map map);
}
