package com.zy.service.impl;

import com.zy.mapper.OrderListMapper;
import com.zy.pojo.OrderList;
import com.zy.service.OrderListService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class OrderListServiceImpl implements OrderListService {
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
    public void addOrderList(OrderList orderList) {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderListMapper mapper = sqlSession.getMapper(OrderListMapper.class);
            mapper.addOrderList(orderList);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}