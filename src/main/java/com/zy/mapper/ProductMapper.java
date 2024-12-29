package com.zy.mapper;

import com.zy.pojo.Product;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select * from tb_product")
    List<Product> findAllProduct();

    @Select("select * from tb_product where product_id = #{pId}")
    Product findProductById(int pId);

    @Update("update tb_product set product_name=#{product_name}, product_info=#{product_info}, " +
            "product_price=#{product_price}, product_stock=#{product_stock}, product_fid=#{product_fid}, " +
            "product_cid=#{product_cid}, product_photo=#{product_photo} where product_id=#{product_id}")
    void updateProductById(Product p);

    @Delete("delete from tb_product where product_id=#{productId}")
    void delProductById(int productId);

    @Insert("insert into tb_product(product_name,product_info,product_price,product_stock," +
            "product_fid,product_cid,product_photo,product_time) values(#{product_name}," +
            "#{product_info},#{product_price},#{product_stock},#{product_fid},#{product_cid}," +
            "#{product_photo},#{product_time})")
    void addProduct(Product p);

    @Select("select * from tb_product where product_cid = #{productCid}")
    List<Product> findProductByCategoryCid(int productCid);

    @Select("select * from tb_product order by product_time desc limit 6")
    List<Product> findProductTimeSort();

    @Select("select * from tb_product order by product_sales desc limit 9")
    List<Product> findProductSalesSort();

    @Select("select * from tb_product where product_fid = #{fid}")
    List<Product> findProductByCategoryFid(int fid);

    @Select("select * from tb_product order by product_time desc limit #{start}, #{rows}")
    List<Product> findByPage(@Param("start") int start, @Param("rows") int rows);

    @Select("select count(*) from tb_product")
    int findTotalCount();

    @Select("select * from tb_product where product_name like concat('%',#{key},'%')")
    List<Product> findProductListByKey(String key);
}