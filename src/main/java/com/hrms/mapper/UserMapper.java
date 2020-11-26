package com.hrms.mapper;

import com.hrms.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    // public  UserTest logincheck(String name);

    //@Select({"select user_id,name,pwd from tb_user where name=#{name}"})
    User checkUser(String name);

    List<User> selectAllUser();
    int  deleteUser(String name);
    int insertUser(User user);
    int updateUser(@Param("job_number") String job_number, @Param("user") User user);
    User selectUser(String job_number);
    String selectName(String job_number);

    User queryUserByUserName(String alias);
    int lockUser(String alias);
}