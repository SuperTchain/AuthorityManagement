package com.lx.authoritymanagement.controller;

import com.lx.authoritymanagement.annotations.RecordOperation;
import com.lx.authoritymanagement.pojo.Product;
import com.lx.authoritymanagement.service.ProductService;
import com.lx.authoritymanagement.service.UserService;
import com.lx.authoritymanagement.utils.Result;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/31 15:09
 * @Version 1.0
 */
@Controller
@RequestMapping("/product")
@Api(tags = "产品模块")
public class ProductController {
    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(ProductController.class);
    /**
     * 引入服务
     */
    @Autowired
    private ProductService productService;

    /**
     * 跳转到产品列表界面
     *
     * @return 列表界面
     */
    @GetMapping("/toProductList")
    @ApiOperation(value = "跳转到产品列表界面")
    public String toProductList() {
        return "product/productList";
    }

    /**
     * 跳转到列表界面
     *
     * @return 列表界面
     */
    @GetMapping("/toAddProduct")
    @ApiOperation(value = "跳转到添加产品界面")
    public String toAddProduct() {
        return "product/addProduct";
    }

    /**
     * 查询所有产品信息
     *
     * @param page  页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @GetMapping("/findAllProduct")
    @ResponseBody
    @ApiOperation(value = "查询所有用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数"),
            @ApiImplicitParam(name = "limit", value = "每页页数")
    })
    @RecordOperation(name = "查询所有产品信息")
    public Result findAllProduct(Integer page, Integer limit) {
        Result result = productService.findAllProduct(page, limit);
        logger.info("查询产品列表成功");
        return result;
    }

    /**
     * 根据传入条件查询产品信息
     *
     * @param productId   产品ID
     * @param productName 产品名称
     * @param timerange   时间范围
     * @param page        页数
     * @param limit       每页条数
     * @return 封装结果
     */
    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "根据条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "产品ID", dataType = "Integer"),
            @ApiImplicitParam(name = "productName", value = "产品名称", dataType = "String"),
            @ApiImplicitParam(name = "timerange", value = "时间范围", dataType = "String", example = "2020-05-30~2020-05-31"),
            @ApiImplicitParam(name = "page", value = "页数", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页页数", dataType = "Integer")}
    )
    @RecordOperation(name = "根据传入条件查询产品信息")
    public Result serachUser(Integer productId, String productName, String timerange, Integer page, Integer limit) {
        Result result = productService.search(productId, productName, timerange, page, limit);
        logger.info("产品条件搜索查询成功");
        return result;
    }


    /**
     * 添加产品
     * @param product 产品实体类
     * @return 结果
     */
    @PostMapping("/addProduct")
    @ResponseBody
    @ApiOperation(value = "添加产品")
    @RecordOperation(name = "添加产品")
    public Result addProduct(@ApiParam(name = "product", value = "产品实体类") Product product) {
        Result result = productService.addProduct(product);
        logger.info("成功添加产品");
        result.setStatus(200);
        return result;
    }
}
