package com.hrms.service;

import com.hrms.bean.User;
import com.hrms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;

    /**
     public UserTest logincheck(String name, String pwd) {
     UserTest u= userMapper.logincheck(name);
     if(u != null &&u.getPwd().equals(pwd)){
     return u;
     }
     return null;
     }
     */
    public User checkUser(String name, String password) {
        return userMapper.checkUser(name);
    }


    public List<User> selectAllUser()
    {
        return  userMapper.selectAllUser();
    }

    public   int  deleteUser(String name){
        return  userMapper.deleteUser(name);

    }
    public  int insertUser(User user){
        return  userMapper.insertUser(user);
    }
    public int updateUser(String job_number,User user)
    {

        return userMapper.updateUser(job_number,user);
    }
    public User selectUser(String job_number)
    {
        return userMapper.selectUser(job_number);
    }


    public  String selectName(String job_number)
    {
        return userMapper.selectName(job_number);
    }


    public User queryUserByUserName(String alias)
    {

        return   userMapper.queryUserByUserName(alias);

    }
    public  int lockUser(String alias)
    {
        return  userMapper.lockUser(alias);
    }


}