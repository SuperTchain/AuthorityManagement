package com.lx.authoritymanagement.dao;

import com.lx.authoritymanagement.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /**
     * 查询所有用户
     * @return 用户列表信息
     */
    List<User> findAllUser();
}
