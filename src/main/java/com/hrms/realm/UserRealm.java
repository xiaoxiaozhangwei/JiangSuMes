package com.hrms.realm;


import com.hrms.bean.User;
import com.hrms.service.PermissionService;
import com.hrms.service.RoleService;
import com.hrms.service.UserService;
import com.hrms.util.ActiveUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class UserRealm  extends AuthorizingRealm{


    public String getName()
    {
        return this.getClass().getName();
    }

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;


    //认证

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // TODO Auto-generated method stub
        String alias=(String) token.getPrincipal();
        Object credtntialsObject= token.getCredentials();
       // System.out.println(Arrays.toString((char[])credtntialsObject));

        //根据用户名查询是否存在
        User user=this.userService.queryUserByUserName(alias);

        //若用户存在
        if (user!=null) {

           List<String> roles=roleService.queryRolesByUserName(user.getName());
           List<String> permissions=this.permissionService.queryPermissionsByUserName(user.getName());

            ActiveUser activeUser=new ActiveUser(user,roles,permissions);
          //  System.out.println(activeUser);

          //  SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, user.getPasswordString(), this.getName());


            ByteSource credentialsSalt= ByteSource.Util.bytes(user.getName());//设置盐
            SimpleAuthenticationInfo info2= new SimpleAuthenticationInfo(activeUser, user.getPwd(), credentialsSalt, this.getName());
            return info2;

        }



        return null;
    }


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        //System.out.println("doGetAuthorizationInfo");

        SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();
        ActiveUser activeUser =(ActiveUser) principals.getPrimaryPrincipal();

          // System.out.println("验证1");
            List<String> roles=activeUser.getRoles();
            //System.out.println(roles);
            if (roles!=null&&roles.size()>0)
            {
                //添加角色
                info.addRoles(roles);
            }

            List<String> permissions=activeUser.getPermissions();//不需要频繁读取数据库.，认证只需一次（所以将相关的权限和角色 在认证是放入），授权需要多次
       // System.out.println(permissions);
            if (permissions!=null&&permissions.size()>0)
            {
                //添加权限
                info.addStringPermissions(permissions);
            }
           // System.out.println("验证2");

        return info;

    }

}
