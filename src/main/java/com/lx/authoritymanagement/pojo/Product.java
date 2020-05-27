package com.lx.authoritymanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Produt
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/27 11:34
 * @Version 1.0
 */
@TableName("product")
@ApiModel(value="Product对象", description="产品表")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "产品名称")
    @TableField("productName")
    private String productName;

    @ApiModelProperty(value = "产品价格")
    @TableField("productPrice")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "产品生产地址")
    @TableField("productionAddress")
    private String productionAddress;

    @ApiModelProperty(value = "产品生产日期")
    @TableField("productDate")
    private Date productDate;

    @ApiModelProperty(value = "产品数量")
    @TableField("productNumber")
    private Integer productNumber;

    @ApiModelProperty(value = "产品描述")
    @TableField("productDesc")
    private String productDesc;

    @ApiModelProperty(value = "产品状态")
    @TableField("productStaus")
    private Integer productStaus;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productionAddress='" + productionAddress + '\'' +
                ", productDate=" + productDate +
                ", productNumber=" + productNumber +
                ", productDesc='" + productDesc + '\'' +
                ", productStaus=" + productStaus +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductionAddress() {
        return productionAddress;
    }

    public void setProductionAddress(String productionAddress) {
        this.productionAddress = productionAddress;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStaus() {
        return productStaus;
    }

    public void setProductStaus(Integer productStaus) {
        this.productStaus = productStaus;
    }
}
