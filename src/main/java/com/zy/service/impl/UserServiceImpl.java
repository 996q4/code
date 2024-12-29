package com.zy.service.impl;

import com.zy.mapper.UserMapper;
import com.zy.pojo.PageBean;
import com.zy.pojo.User;
import com.zy.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserServiceImpl implements UserService {
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
    public User loginByNameAndPassword(String name, String password) {
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.loginByNameAndPassword(name, password);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<User> findAllUser() {
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.findAllUser();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Boolean addUser(User u) {
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.addUser(u);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            int currentPage = Integer.parseInt(_currentPage);
            int rows = Integer.parseInt(_rows);

            PageBean<User> pb = new PageBean<>();
            pb.setCurrentPage(currentPage);
            pb.setRows(rows);

            int totalCount = mapper.findTotalCount();
            pb.setTotalCount(totalCount);

            int start = (currentPage - 1) * rows;
            List<User> list = mapper.findByPage(start, rows);
            pb.setList(list);

            int totalPage = (totalCount % rows) == 0 ? totalCount/rows : (totalCount/rows) + 1;
            pb.setTotalPage(totalPage);

            return pb;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public User findUserByUserId(int user_id) {
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.findUserByUserId(user_id);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public User findUserByUserName(String userName) {
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.findUserByUserName(userName);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<User> findUserListByKey(String keywords) {
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.findUserListByKey(keywords);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void delUserById(int uid) {
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.delUserById(uid);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updatePasswordById(int user_id, String p) {
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.updatePasswordById(user_id, p);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updatePhotoById(int userId, String fileName) {
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.updatePhotoById(userId, fileName);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateInfoById(int userId, String sex, String nickname) {
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.updateInfoById(userId, sex, nickname);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateUserById(User u) {
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.updateUserById(u);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}