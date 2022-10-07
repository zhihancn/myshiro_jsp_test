package com.xueyh.newshiro_jsp.service;

import com.xueyh.newshiro_jsp.dao.UserDAO;
import com.xueyh.newshiro_jsp.entity.Perms;
import com.xueyh.newshiro_jsp.entity.User;
import com.xueyh.newshiro_jsp.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void register(User user) {
        //1.创建随机盐
        String salt = SaltUtils.getSalt(8);
        //2.保存随机盐至user类
        user.setSalt(salt);
        //3.加密密码
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        //4.保存至数据库中
        userDAO.save(user);

    }

    @Override
    public User findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }

    @Override
    public User findRolesByUserName(String userName) {
        return userDAO.findRolesByUserName(userName);
    }

    @Override
    public List<Perms> findPermsByRoleId(String id) {
        return userDAO.findPermsByRoleId(id);
    }
}
