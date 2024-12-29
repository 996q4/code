package com.zy.service.impl;

import com.zy.mapper.CategoryMapper;
import com.zy.pojo.Category;
import com.zy.service.CategoryService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private static SqlSessionFactory factory;

    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("MyBatis配置文件加载失败", e);
        }
    }

    @Override
    public List<Category> findCategoryListByName(String flag) {
        SqlSession sqlSession = factory.openSession();
        try {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            if (flag != null && flag.equals("father")) {
                return mapper.findCategoryParentList();
            } else {
                return mapper.findCategoryChildList();
            }
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Category> findAllCategory() {
        SqlSession sqlSession = factory.openSession();
        try {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            return mapper.findAllCategory();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Category findCategoryByCid(int category_id) {
        SqlSession sqlSession = factory.openSession();
        try {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            return mapper.findCategoryByCid(category_id);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void addCategory(Category category) {
        SqlSession sqlSession = factory.openSession();
        try {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            mapper.addCategory(category);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void delCategoryById(int category_id) {
        SqlSession sqlSession = factory.openSession();
        try {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            mapper.delCategoryById(category_id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateCategoryName(String categoryName, int categoryId) {
        SqlSession sqlSession = factory.openSession();
        try {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            mapper.updateCategoryName(categoryName, categoryId);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}