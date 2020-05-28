package com.lx.authoritymanagement.service.impl;

import com.lx.authoritymanagement.dao.UserDao;
import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.service.UserService;
import com.lx.authoritymanagement.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
     *
     * @return 用户列表
     */
    @Override
    public Result findAllUserByPage(Integer page, Integer limit) {

        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        Result result = new Result();
        //分页查询所有用户集合
        List<User> user = userDao.findAllUserByPage(start, limit);
        result.setItem(user);
        //查询用户的总个数
        Integer count = userDao.selectCount();
        result.setTotal(count);
        return result;
    }

    /**
     * 查询用户总数
     *
     * @return 总数
     */
    @Override
    public Integer selectCount() {
        return userDao.selectCount();
    }

    /**
     * 根据搜索条件进行查询
     *
     * @param userid   用户id
     * @param username 用户名
     * @param account  用户账号
     * @param page     页数
     * @param limit    分页页数
     * @return result结果
     */
    @Override
    public Result search(Integer userid, String username, String account, Integer page, Integer limit) {
        Result result = new Result();
        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        //分页查询所有用户集合
        List<User> user = userDao.search(userid, username, account, start, limit);
        result.setItem(user);
        //查询用户的总个数
        Integer count = userDao.searchCountLike(userid, username, account);
        result.setTotal(count);
        return result;
    }

    /**
     * 根据搜索条件查询数量
     *
     * @param userid   用户id
     * @param username 用户姓名
     * @param account  用户账号
     * @return result结果
     */
    @Override
    public Integer searchCountLike(Integer userid, String username, String account) {
        return userDao.searchCountLike(userid, username, account);
    }

    /**
     * 添加用户
     * @param user 用户信息
     * @return result结果
     */
    @Override
    public Result addUser(User user) {
        Result result = new Result();
        try {
            userDao.addUser(user);
            result.setStatus(200);
            result.setItem("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
            result.setItem("添加失败");
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    /**
     * 批量删除用户
     * @param ids 用户id数组
     * @return result结果
     */
    @Override
    public Result batchDeleteByUserId(String[] ids) {
        Result result = new Result();
        try{
            userDao.batchDeleteByUserId(ids);
            result.setStatus(200);
            result.setItem("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);
            result.setItem("删除失败");
        }
        return result;
    }
}
