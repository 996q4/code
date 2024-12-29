// AddressMapper.java
package com.zy.mapper;

import com.zy.pojo.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    /**
     * 根据用户ID查询地址列表
     */
    @Select("select * from tb_address where user_id = #{user_id}")
    List<Address> findAddressByUserId(int user_id);

    /**
     * 根据地址ID查询地址
     */
    @Select("select * from tb_address where address_id = #{address_id}")
    Address findAddressByAddressId(int address_id);

    /**
     * 添加新地址
     */
    @Insert("insert into tb_address(user_id, user_name, user_phone, user_address) " +
            "values(#{user_id}, #{user_name}, #{user_phone}, #{user_address})")
    void addAddress(Address address);

    /**
     * 根据地址ID删除地址
     */
    @Delete("delete from tb_address where address_id = #{address_id}")
    void delAddressById(int address_id);
}