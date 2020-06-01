package com.lx.authoritymanagement.dao;

import com.lx.authoritymanagement.pojo.Product;
import com.lx.authoritymanagement.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lx
 */
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

    /**
     * 添加产品
     * @param product 产品实体类
     * @return 结果
     */
    Integer addProduct(Product product);

    /**
     * 根据产品ID查询产品信息
     * @param id 产品ID
     * @return 产品信息
     */
    Product findProductById(Integer id);

    /**
     * 根据产品ID删除产品
     * @param id 产品ID
     * @return 影响数量
     */
    Integer deleteById(Integer id);

    /**
     * 根据传入产品ID数组批量删除产品
     * @param ids 产品ID数组
     * @return 影响数量
     */
    Integer batchDeleteByProductId(@Param("ids")String[] ids);

    /**
     * 更新产品信息
     * @param product 产品实体类
     * @return 影响结果
     */
    Integer updateProduct(Product product);
}
