package com.lx.authoritymanagement.service.impl;

import com.lx.authoritymanagement.dao.UserDao;
import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/26 0:27
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 引入接口
     */
    @Autowired
    private UserDao userDao;

    /**
     * 查询所有用户信息
     * @return 用户列表
     */
    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }
}
