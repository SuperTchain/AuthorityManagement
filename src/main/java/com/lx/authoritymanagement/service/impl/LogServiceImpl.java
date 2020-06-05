package com.lx.authoritymanagement.service.impl;

import com.lx.authoritymanagement.controller.ProductController;
import com.lx.authoritymanagement.dao.LogDao;
import com.lx.authoritymanagement.pojo.Log;
import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.service.LogService;
import com.lx.authoritymanagement.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

/**
 * @ClassName LogServiceImpl
 * @Description TODO
 * @Author ASUS
 * @Date 2020/6/5 13:09
 * @Version 1.0
 */
@Service
public class LogServiceImpl implements LogService {
    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(LogServiceImpl.class);

    /**
     * 引入dao
     */
    @Autowired
    private LogDao logDao;

    /**
     * 插入记录
     * @param username 用户名
     * @param methodOperation 操作
     * @param urlOperation 操作地址
     * @param operationTime 操作时间
     */
    @Override
    public Integer insertLog(String username, String methodOperation, String urlOperation, Date operationTime) {
        Integer integer= logDao.insertLog(username,methodOperation,urlOperation,operationTime);
        return integer==1?1:0;
    }

    /**
     * 查询所有日志信息
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @Override
    public Result findAllLogByPage(Integer page, Integer limit) {
        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        Result result = new Result();
        //分页查询所有用户集合
        List<Log> logs = logDao.findAllLogByPage(start, limit);
        result.setItem(logs);
        //查询用户的总个数
        Integer count = logDao.selectCount();
        result.setTotal(count);
        return result;
    }

    /**
     * 批量删除日志
     * @param ids 日志数组
     * @return 封装结果
     */
    @Override
    public Result batchDeleteByLogId(String[] ids) {
        Result result = new Result();
        try{
            logDao.batchDeleteByProductId(ids);
            result.setStatus(200);
            result.setItem("删除成功");
        }catch (Exception e){
            logger.error("错误",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);
            result.setItem("删除失败");
        }
        return result;
    }
}
