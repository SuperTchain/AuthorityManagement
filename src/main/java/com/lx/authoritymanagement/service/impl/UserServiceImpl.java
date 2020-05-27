package com.lx.authoritymanagement.service.impl;

import com.lx.authoritymanagement.dao.UserDao;
import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.service.UserService;
import com.lx.authoritymanagement.utils.Result;
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
    public Result findAllUserByPage(Integer page, Integer limit) {

        //计算查询的起始位置
        Integer start = (page-1)*limit;
        Result result = new Result();
        //分页查询所有视频集合
        List<User> user = userDao.findAllUserByPage(start,limit);
        result.setItem(user);
        //查询视频的总个数
        Integer count = userDao.selectCount();
        result.setTotal(count);
        return result;
    }

    /**
     * 查询用户总数
     * @return 总数
     */
    @Override
    public Integer selectCount() {
        return userDao.selectCount();
    }
}
