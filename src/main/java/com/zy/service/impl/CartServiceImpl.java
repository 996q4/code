package com.zy.service.impl;

import com.zy.mapper.CartMapper;
import com.zy.pojo.Cart;
import com.zy.service.CartService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CartServiceImpl implements CartService {
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
    public int findCartCountByUserId(int user_id) {
        SqlSession sqlSession = factory.openSession();
        try {
            CartMapper mapper = sqlSession.getMapper(CartMapper.class);
            return mapper.findCartCountByUserId(user_id);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Cart> findCartListByUserId(int user_id) {
        SqlSession sqlSession = factory.openSession();
        try {
            CartMapper mapper = sqlSession.getMapper(CartMapper.class);
            return mapper.findCartListByUserId(user_id);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void addCart(Cart cart) {
        SqlSession sqlSession = factory.openSession();
        try {
            CartMapper mapper = sqlSession.getMapper(CartMapper.class);
            mapper.addCart(cart);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Cart findCartById(int cart_id) {
        SqlSession sqlSession = factory.openSession();
        try {
            CartMapper mapper = sqlSession.getMapper(CartMapper.class);
            return mapper.findCartById(cart_id);
        } finally {
            sqlSession.close();
        }
    }
}