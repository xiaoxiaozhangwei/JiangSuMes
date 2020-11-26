package com.hrms.mapper;


import com.hrms.bean.Permission;

import java.util.List;

public interface PermissionMapper {
    List<Permission> queryPermissionsByUserName(String username);
}
