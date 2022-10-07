package com.xueyh.newshiro_jsp.shiro.realms;

import com.xueyh.newshiro_jsp.entity.Perms;
import com.xueyh.newshiro_jsp.entity.Role;
import com.xueyh.newshiro_jsp.entity.User;
import com.xueyh.newshiro_jsp.service.UserService;
import com.xueyh.newshiro_jsp.shiro.salt.MyByteSource;
import com.xueyh.newshiro_jsp.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证："+primaryPrincipal);
        //获取主身份信息 角色/权限
        UserService userServiceImpl = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User user = userServiceImpl.findRolesByUserName(primaryPrincipal);
        //从数据库中获取角色信息
        if (!CollectionUtils.isEmpty(user.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());

                //权限信息
                List<Perms> perms = userServiceImpl.findPermsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(perms)){
                    perms.forEach(perm -> {
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }
            });
            return  simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("=============");

        //从传过来的token获取到的用户名
        String principal = (String) token.getPrincipal();
        System.out.println("用户名"+principal);

        //假设是从数据库获得的 用户名，密码
//        String password_db="123";
//        String username_db="zhangsan";

        //在工厂中获取service对象
        UserService userServiceImpl = (UserService) ApplicationContextUtils.getBean("userServiceImpl");

        //查询数据库获取user类信息
        User user = userServiceImpl.findByUserName(principal);
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), new MyByteSource(user.getSalt()),this.getName());
        }


//        if (username_db.equals(principal)){
////            SimpleAuthenticationInfo simpleAuthenticationInfo =
//            return new SimpleAuthenticationInfo(principal,"123", this.getName());
//        }

        return null;
    }
}
