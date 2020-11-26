package com.hrms.mapper;

import com.hrms.bean.Role;

import java.util.List;

public interface RoleMapper {
    List<Role> queryRolesByUserName(String username);
}
