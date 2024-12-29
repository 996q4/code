package com.zy.service.impl;

import com.zy.mapper.ProductMapper;
import com.zy.pojo.PageBean;
import com.zy.pojo.Product;
import com.zy.service.ProductService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductServiceImpl implements ProductService {
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
    public List<Product> findAllProduct() {
        SqlSession sqlSession = factory.openSession();
        try {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            return mapper.findAllProduct();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Product findProductById(int pId) {
        SqlSession sqlSession = factory.openSession();
        try {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            return mapper.findProductById(pId);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateProductById(Product p) {
        SqlSession sqlSession = factory.openSession();
        try {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            mapper.updateProductById(p);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void delProductById(int productId) {
        SqlSession sqlSession = factory.openSession();
        try {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            mapper.delProductById(productId);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void addProduct(Product p) {
        SqlSession sqlSession = factory.openSession();
        try {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            mapper.addProduct(p);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Product> findProductByCategoryCid(int productCid) {
        SqlSession sqlSession = factory.openSession();
        try {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            return mapper.findProductByCategoryCid(productCid);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public PageBean<Product> findProductByPage(String _currentPage, String _rows) {
        SqlSession sqlSession = factory.openSession();
        try {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);

            int currentPage = Integer.parseInt(_currentPage);
            int rows = Integer.parseInt(_rows);

            PageBean<Product> pb = new PageBean<>();
            pb.setCurrentPage(currentPage);
            pb.setRows(rows);

            int totalCount = mapper.findTotalCount();
            pb.setTotalCount(totalCount);

            int start = (currentPage - 1) * rows;
            List<Product> list = mapper.findByPage(start, rows);
            pb.setList(list);

            int totalPage = (totalCount % rows) == 0 ? totalCount/rows : (totalCount/rows) + 1;
            pb.setTotalPage(totalPage);

            return pb;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Product> findProductTimeSort() {
        SqlSession sqlSession = factory.openSession();
        try {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            return mapper.findProductTimeSort();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Product> findProductSalesSort() {
        SqlSession sqlSession = factory.openSession();
        try {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            return mapper.findProductSalesSort();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Product> findProductByCategoryFid(int fid) {
        SqlSession sqlSession = factory.openSession();
        try {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            return mapper.findProductByCategoryFid(fid);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Product> findProductListByKey(String key) {
        SqlSession sqlSession = factory.openSession();
        try {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            return mapper.findProductListByKey(key);
        } finally {
            sqlSession.close();
        }
    }
}