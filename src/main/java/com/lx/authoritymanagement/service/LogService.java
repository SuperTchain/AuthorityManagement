package com.lx.authoritymanagement.service;

import com.lx.authoritymanagement.utils.Result;

import java.util.Date;

/**
 * 记录·操作业务
 */
public interface LogService {
    /**
     * 插入操作记录
     * @param username 用户名
     * @param methodOperation 操作
     * @param urlOperation 操作地址
     * @param operationTime 操作时间
     */
    Integer insertLog(String username, String methodOperation, String urlOperation, Date operationTime);

    /**
     * 查询所有日志信息
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    Result findAllLogByPage(Integer page, Integer limit);

    /**
     * 批量删除日志ID
     * @param ids 日志数组
     * @return 结果
     */
    Result batchDeleteByLogId(String[] ids);
}
