package com.lx.authoritymanagement.service;

import com.lx.authoritymanagement.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户
     * @return 用户列表信息
     */
    List<User> findAllUser();
}
