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

    /**
     * 根据搜索条件查询用户
     * @param userid 用户id
     * @param username 用户姓名
     * @param account 用户账号
     * @param start 开始页数
     * @param limit 每页页数
     * @return
     */
    List<User> search(@Param("userid") Integer userid,@Param("username") String username, @Param("account")String account, @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 根据搜索条件查询数量
     * @param userid 用户id
     * @param username 用户姓名
     * @param account 用户账号
     * @return
     */
    Integer searchCountLike(@Param("userid") Integer userid,@Param("username") String username,@Param("account")String account);

    /**
     * 添加用户
     * @param user 用户信息
     * @return
     */
    Integer addUser(User user);

    /**
     * 根据用户id批量删除用户
     * @param ids 用户id数组
     * @return
     */
    Integer batchDeleteByUserId(@Param("ids") String[] ids);
}
