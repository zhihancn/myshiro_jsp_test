package com.xueyh.newshiro_jsp.dao;

import com.xueyh.newshiro_jsp.entity.Perms;
import com.xueyh.newshiro_jsp.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {
    void save(User user);

    User findByUserName(String userName);

    //根据用户名查询角色
    User findRolesByUserName(String userName);

    //根据角色名查询具体权限
    List<Perms> findPermsByRoleId(String id);
}
