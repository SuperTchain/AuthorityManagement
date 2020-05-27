package com.lx.authoritymanagement.service;

import com.lx.authoritymanagement.utils.Result;


public interface UserService {
    /**
     * 分页查询所有用户
     * @return 用户列表信息
     */
    Result findAllUserByPage(Integer page, Integer limit);

    /**
     * 查询用户总数
     * @return 总数
     */
    Integer selectCount();
}
