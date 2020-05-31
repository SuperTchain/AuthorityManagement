package com.lx.authoritymanagement.dao;

import com.lx.authoritymanagement.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    /**
     * 查询所有产品信息
     *
     * @param start 页数
     * @param limit 每页限制
     * @return 产品列表
     */
    List<Product> findAllProductByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 查询产品数量
     *
     * @return 数量
     */
    Integer selectCount();

    /**
     * 根据传入条件模糊查询
     *
     * @param productId   产品ID
     * @param productName 产品名称
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param start       开始页面
     * @param limit       每页条数
     * @return 封装结果
     */
    List<Product> search(@Param("productId") Integer productId, @Param("productName") String productName, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 根据传入条件模糊查询数量
     *
     * @param productId   产品ID
     * @param productName 产品名称
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @return 数量
     */
    Integer searchCountLike(@Param("productId") Integer productId, @Param("productName") String productName, @Param("startTime") String startTime, @Param("endTime") String endTime);
}
