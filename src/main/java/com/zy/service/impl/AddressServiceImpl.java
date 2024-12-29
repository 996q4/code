package com.zy.service.impl;

import com.zy.mapper.AddressMapper;
import com.zy.pojo.Address;
import com.zy.service.AddressService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AddressServiceImpl implements AddressService {
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
    public List<Address> findAddressByUserId(int user_id) {
        SqlSession sqlSession = factory.openSession();
        try {
            AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);
            return mapper.findAddressByUserId(user_id);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Address findAddressByAddressId(int address_id) {
        SqlSession sqlSession = factory.openSession();
        try {
            AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);
            return mapper.findAddressByAddressId(address_id);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void addAddress(Address address) {
        SqlSession sqlSession = factory.openSession();
        try {
            AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);
            mapper.addAddress(address);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void delAddressById(int address_id) {
        SqlSession sqlSession = factory.openSession();
        try {
            AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);
            mapper.delAddressById(address_id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}