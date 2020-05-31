package com.lx.authoritymanagement.service;

import com.lx.authoritymanagement.pojo.Product;
import com.lx.authoritymanagement.utils.Result;

public interface ProductService {

    /**
     * 查询所有产品信息
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    Result findAllProduct(Integer page, Integer limit);

    /**
     * 根据传入的条件进行模糊查询
     * @param productId 产品id
     * @param productName 产品名称
     * @param timerange 时间
     * @param page 页数
     * @param limit 每页页数
     * @return 封装结果
     */
    Result search(Integer productId, String productName, String timerange, Integer page, Integer limit);

    /**
     * 添加产品
     * @param product 产品实体类
     * @return 结果
     */
    Result addProduct(Product product);
}
