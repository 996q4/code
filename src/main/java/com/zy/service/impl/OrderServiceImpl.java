package com.zy.service.impl;

import com.zy.mapper.OrderMapper;
import com.zy.pojo.Order;
import com.zy.pojo.PageBean;
import com.zy.service.OrderService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OrderServiceImpl implements OrderService {
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
    public void addOrder(Order order) {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            mapper.addOrder(order);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Order findOrderById(String oid) {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            return mapper.findOrderById(oid);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateOrderAddress(String oid, int addressid) {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            mapper.updateOrderAddress(oid, addressid);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateOrderIsPay(String oid, String s) {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            mapper.updateOrderIsPay(oid, s);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Order> findOrderByUserId(int user_id) {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            return mapper.findOrderByUserId(user_id);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Order> findAllOrder() {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            return mapper.findAllOrder();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public PageBean<Order> findOrderByPage(String _currentPage, String _rows) {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

            int currentPage = Integer.parseInt(_currentPage);
            int rows = Integer.parseInt(_rows);

            PageBean<Order> pb = new PageBean<>();
            pb.setCurrentPage(currentPage);
            pb.setRows(rows);

            int totalCount = mapper.findTotalCount();
            pb.setTotalCount(totalCount);

            int start = (currentPage - 1) * rows;
            List<Order> list = mapper.findByPage(start, rows);
            pb.setList(list);

            int totalPage = (totalCount % rows) == 0 ? totalCount/rows : (totalCount/rows) + 1;
            pb.setTotalPage(totalPage);

            return pb;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Order> findOrderListByKey(String keywords) {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            return mapper.findOrderListByKey(keywords);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Order> findOrderByUserIdAndIsReceipt(int user_id) {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            return mapper.findOrderByUserIdAndIsReceipt(user_id);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateOrderIsShip(String oid) {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            mapper.updateOrderIsShip(oid);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateOrderIsReceipt(String oid) {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            mapper.updateOrderIsReceipt(oid);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void delOrderById(String oid) {
        SqlSession sqlSession = factory.openSession();
        try {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            mapper.delOrderById(oid);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}