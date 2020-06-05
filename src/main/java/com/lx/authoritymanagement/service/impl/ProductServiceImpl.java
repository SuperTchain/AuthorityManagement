package com.lx.authoritymanagement.service.impl;

import com.lx.authoritymanagement.controller.LoginController;
import com.lx.authoritymanagement.dao.ProductDao;
import com.lx.authoritymanagement.pojo.Product;
import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.service.ProductService;
import com.lx.authoritymanagement.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @ClassName ProductServiceImpl
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/31 16:03
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);


    /**
     * 引入dao
     */
    @Autowired
    private ProductDao productDao;

    /**
     * 查询所有产品信息
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @Override
    public Result findAllProduct(Integer page, Integer limit) {
        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        Result result = new Result();
        //分页查询所有产品集合
        List<Product> products = productDao.findAllProductByPage(start, limit);
        result.setItem(products);
        //查询产品的总个数
        Integer count = productDao.selectCount();
        result.setTotal(count);
        return result;
    }

    /**
     * 根据传入数据进行模糊查询
     * @param productId 产品id
     * @param productName 产品名称
     * @param timerange 时间
     * @param page 页数
     * @param limit 每页页数
     * @return 封装结果
     */
    @Override
    public Result search(Integer productId, String productName, String timerange, Integer page, Integer limit) {
        //开始时间
        String startTime=null;
        //结束时间
        String endTime=null;
        String isNull="";
        //判断时间是否为空字符串
        if(!timerange.equals(isNull)){
            //根据开始时间和结束时间查询
            //使用split切割，返回数组 数组第一个元素：起始时间 数组第二个元素：结束时间
            String [] timeArray = timerange.split("~");
            //为其赋值,trim():去除前后的空格，
            startTime= timeArray[0].trim();
            endTime= timeArray[1].trim();
        }
        Result result = new Result();
        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        //分页查询所有产品集合
        List<Product> products = productDao.search(productId, productName, startTime,endTime, start, limit);
        result.setItem(products);
        //查询产品的总个数
        Integer count = productDao.searchCountLike(productId, productName, startTime,endTime);
        result.setTotal(count);
        return result;
    }

    /**
     * 添加产品
     * @param product 产品实体类
     * @return 结果
     */
    @Override
    public Result addProduct(Product product) {
        Result result = new Result();
        try {
            productDao.addProduct(product);
            result.setStatus(200);
            result.setItem("添加成功");
        } catch (Exception e) {
            logger.error("错误",e);
            result.setStatus(500);
            result.setItem("添加失败");
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    /**
     * 根据产品ID查询产品
     * @param id 产品ID
     * @return 结果
     */
    @Override
    public Result findProductById(Integer id) {
        Result result = new Result();
        Product product = productDao.findProductById(id);
        result.setStatus(200);
        result.setItem(product);
        return result;
    }

    /**
     * 根据ID删除产品
     * @param id 产品ID
     * @return 封装结果
     */
    @Override
    public Result deleteById(Integer id) {
        Result result = new Result();
        try{
            productDao.deleteById(id);
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

    /**
     * 批量删除产品
     * @param ids 产品ID数组
     * @return 封装结果
     */
    @Override
    public Result batchDeleteByProductId(String[] ids) {
        Result result = new Result();
        try{
            productDao.batchDeleteByProductId(ids);
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

    /**
     * 更新产品信息
     * @param product 产品实体类
     * @return 封装结果
     */
    @Override
    public Result updateProduct(Product product) {
        productDao.updateProduct(product);
        Result result = new Result();
        result.setStatus(200);
        result.setItem("更新成功");
        return result;
    }
}
