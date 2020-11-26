package com.hrms.service;

import com.hrms.bean.Permission;
import com.hrms.bean.Role;
import com.hrms.mapper.PermissionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {
       @Autowired
       PermissionMapper permissionMapper;

    //根据用户名查询用户拥有的权限
  public List<String> queryPermissionsByUserName(String username)
    {
        List<Permission> permissionList=permissionMapper.queryPermissionsByUserName(username);
        List<String> permissions=new ArrayList<>();
        for (Permission permission:permissionList)
        {
            permissions.add(permission.getPercode());
        }

        return  permissions;
    }





}
