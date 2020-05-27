package com.lx.authoritymanagement.dao;

import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.utils.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /**
     * 分页查询所有用户
     * @return 用户列表信息
     */
    List<User> findAllUserByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 查询用户总数
     * @return 总数
     */
    Integer selectCount();
}
