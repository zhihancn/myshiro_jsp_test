package com.xueyh.newshiro_jsp.service;

import com.xueyh.newshiro_jsp.entity.Perms;
import com.xueyh.newshiro_jsp.entity.User;

import java.util.List;

public interface UserService {
    //注册用户服务层
    void register(User user);

    //根据用户名查找业务
    User findByUserName(String userName);

    //根据用户名查询权限[列表]
    User findRolesByUserName(String userName);


    List<Perms> findPermsByRoleId(String id);

}
