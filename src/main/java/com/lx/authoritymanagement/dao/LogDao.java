package com.lx.authoritymanagement.dao;

import com.lx.authoritymanagement.pojo.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author lx
 */
@Repository
public interface LogDao {

    /**
     * 插入记录
     * @param username 用户名
     * @param methodOperation 操作
     * @param urlOperation 操作路径
     * @param operationTime 操作时间
     * @return 结果
     */
    Integer insertLog(@Param("username") String username,@Param("methodOperation") String methodOperation,@Param("urlOperation") String urlOperation,@Param("operationTime") Date operationTime);

    /**
     * 查询所有日志信息
     * @param start 开始
     * @param limit  每页条数
     * @return 结果
     */
    List<Log> findAllLogByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 查询日志条数
     * @return 数量
     */
    Integer selectCount();

    /**
     * 批量删除日志
     * @param ids 数组
     */
    void batchDeleteByProductId(String[] ids);
}
