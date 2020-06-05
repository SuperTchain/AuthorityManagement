package com.lx.authoritymanagement.service;

import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.utils.Result;

import javax.servlet.http.HttpServletRequest;


public interface UserService {
    /**
     * 分页查询所有用户
     *
     * @return 用户列表信息
     */
    Result findAllUserByPage(Integer page, Integer limit);

    /**
     * 查询用户总数
     *
     * @return 总数
     */
    Integer selectCount();

    /**
     * 根据条件查询用户信息
     *
     * @param userid   用户id
     * @param username 用户名
     * @param account  用户账号
     * @param page     页数
     * @param limit    分页页数
     * @return 结果
     */
    Result search(Integer userid, String username, String account, Integer page, Integer limit);

    /**
     * 根据搜索条件查询数量
     *
     * @param userid   用户id
     * @param username 用户姓名
     * @param account  用户账号
     * @return 用户数量
     */
    Integer searchCountLike(Integer userid, String username, String account);

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 结果
     */
    Result addUser(User user);

    /**
     * 根据用户id批量删除用户
     *
     * @param ids 用户id数组
     * @return 结果
     */
    Result batchDeleteByUserId(String[] ids);

    /**
     * 根据用户id删除用户
     *
     * @param id 用户id
     * @return 结果
     */
    Result deleteById(Integer id);

    /**
     * 根据用户id显示用户信息
     *
     * @param id 用户Id
     * @return 封装结果
     */
    Result findUserById(Integer id);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 封装结果
     */
    Result updateUser(User user);

    /**
     *根据账户名查询信息
     * @param account 账户名
     * @param password 密码
     * @param code 验证码
     * @param request 请求
     * @return 查询结果
     */
    Result findUserByName(String account, String password, String code, HttpServletRequest request);
}
