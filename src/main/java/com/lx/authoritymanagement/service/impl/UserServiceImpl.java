package com.lx.authoritymanagement.service.impl;

import com.lx.authoritymanagement.controller.LoginController;
import com.lx.authoritymanagement.dao.UserDao;
import com.lx.authoritymanagement.exception.MyUsernameNotFoundException;
import com.lx.authoritymanagement.pojo.Role;
import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.service.UserService;
import com.lx.authoritymanagement.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    /**
     * 密码加密
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
     *
     * @param user 用户信息
     * @return result结果
     */
    @Override
    public Result addUser(User user) {
        //对密码进行加密处理
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        logger.info(user.getPassword());
        Result result = new Result();
        try {
            userDao.addUser(user);
            result.setStatus(200);
            result.setItem("添加成功");
        } catch (Exception e) {
            logger.error("错误", e);
            result.setStatus(500);
            result.setItem("添加失败");
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    /**
     * 批量删除用户
     *
     * @param ids 用户id数组
     * @return result结果
     */
    @Override
    public Result batchDeleteByUserId(String[] ids) {
        Result result = new Result();
        try {
            userDao.batchDeleteByUserId(ids);
            result.setStatus(200);
            result.setItem("删除成功");
        } catch (Exception e) {
            logger.error("错误", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);
            result.setItem("删除失败");
        }
        return result;
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 结果
     */
    @Override
    public Result deleteById(Integer id) {
        Result result = new Result();
        try {
            userDao.deleteById(id);
            result.setStatus(200);
            result.setItem("删除成功");
        } catch (Exception e) {
            logger.error("错误", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);
            result.setItem("删除失败");
        }
        return result;
    }

    /**
     * 根湖用户Id查询用户信息
     *
     * @param id 用户Id
     * @return 封装结果
     */
    @Override
    public Result findUserById(Integer id) {
        Result result = new Result();
        User user = userDao.findUserById(id);
        result.setStatus(200);
        result.setItem(user);
        return result;
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 封装结果
     */
    @Override
    public Result updateUser(User user) {
        //对密码进行加密处理
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.updateUser(user);
        Result result = new Result();
        result.setStatus(200);
        result.setItem("更新成功");
        return result;
    }

    /**
     * 根据用户账户查询信息
     *
     * @param username  账户名
     * @param password 密码
     * @param code     验证码
     * @param request  请求
     * @return 结果
     */
    @Override
    public Result findUserByName(String username, String password, String code, HttpServletRequest request) {
        logger.info(username + password + code);
        Result result = new Result();
        HttpSession session = request.getSession();
        //将验证码从session中获取出来
        String codeValue = (String) session.getAttribute("code");
        //先比较验证码(使用忽略大小写比较)
        if (!code.equalsIgnoreCase(codeValue)) {
            //给出验证码输入错误的提示
            result.setStatus(500);
            result.setItem("验证码错误，请重新输入");
        } else {
            //再比较账户和密码
            //根据账号查询用户
            User user = userDao.findUserByName(username);
            logger.info(user);
            //根据账户查不到   或者  查到的用户的密码跟输入密码加密之后的值不一致
            if (user == null || !user.getPassword().equals(password)) {
                //账户或者密码输入错误
                result.setItem("账户或者密码输入错误");
                result.setStatus(500);
            } else {
                //将用户信息存放到session中
                session.setAttribute("user", user);
                session.setAttribute("userName", user.getUserName());
                result.setStatus(200);
                result.setItem("success");
                logger.info("登录成功");
            }
        }
        return result;
    }

    /**
     * security实现
     *
     * @param s 用户名
     * @return 封装结果
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userByName = userDao.findUserByName(s);
        if (userByName!=null){
            //将用户信息存放到session中
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            request.getSession().setAttribute("user", userByName);
            request.getSession().setAttribute("userName", userByName.getUserName());
        }else if (userByName==null){
            throw new MyUsernameNotFoundException("输入的账号有误");
        }
        return userByName;
    }
}