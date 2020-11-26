package com.hrms.service;

import com.hrms.bean.Role;
import com.hrms.mapper.RoleMapper;
import com.hrms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;
    //根据用户名查询用户拥有的角色
  public  List<String> queryRolesByUserName(String username)
    {
         List<Role> roleList=roleMapper.queryRolesByUserName(username);
        List<String>  roles=new ArrayList<String>();
        for (Role role:roleList)
        {
            roles.add(role.getRolename());
        }
        return  roles;
    }

}
