package com.zy.mapper;

import com.zy.pojo.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from tb_user where user_name = #{name} and user_password = #{password}")
    User loginByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Select("select * from tb_user")
    List<User> findAllUser();

    @Insert("insert into tb_user(user_name,user_nickname,user_password,user_sex,user_vip," +
            "user_viptime,user_status,user_photo) values(#{user_name},#{user_nickname}," +
            "#{user_password},#{user_sex},#{user_vip},#{user_viptime},#{user_status},#{user_photo})")
    void addUser(User user);

    @Select("select count(*) from tb_user")
    int findTotalCount();

    @Select("select * from tb_user limit #{start}, #{rows}")
    List<User> findByPage(@Param("start") int start, @Param("rows") int rows);

    @Select("select * from tb_user where user_id = #{user_id}")
    User findUserByUserId(int user_id);

    @Select("select * from tb_user where user_name = #{userName}")
    User findUserByUserName(String userName);

    @Select("select * from tb_user where user_name like concat('%',#{keywords},'%') " +
            "or user_nickname like concat('%',#{keywords},'%')")
    List<User> findUserListByKey(String keywords);

    @Delete("delete from tb_user where user_id=#{uid}")
    void delUserById(int uid);

    @Update("update tb_user set user_password = #{password} where user_id = #{user_id}")
    void updatePasswordById(@Param("user_id") int user_id, @Param("password") String password);

    @Update("update tb_user set user_photo = #{fileName} where user_id = #{userId}")
    void updatePhotoById(@Param("userId") int userId, @Param("fileName") String fileName);

    @Update("update tb_user set user_sex = #{sex}, user_nickname = #{nickname} where user_id = #{userId}")
    void updateInfoById(@Param("userId") int userId, @Param("sex") String sex, @Param("nickname") String nickname);

    @Update("update tb_user set user_name=#{user_name}, user_nickname=#{user_nickname}, " +
            "user_password=#{user_password}, user_sex=#{user_sex}, user_vip=#{user_vip}, " +
            "user_viptime=#{user_viptime}, user_photo=#{user_photo} where user_id=#{user_id}")
    void updateUserById(User user);
}