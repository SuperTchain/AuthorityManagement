package com.lx.authoritymanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

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

    @ApiModelProperty(value = "产品id",example = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "产品名称",example = "华为荣耀")
    @TableField("productName")
    private String productName;

    @ApiModelProperty(value = "产品价格",example = "2000")
    @TableField("productPrice")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "产品生产地址",example = "成都")
    @TableField("productionAddress")
    private String productionAddress;

    @ApiModelProperty(value = "产品生产日期",example = "2020-05-31")
    @TableField("productDate")
    private Date productDate;

    @ApiModelProperty(value = "产品数量",example = "20")
    @TableField("productNumber")
    private Integer productNumber;

    @ApiModelProperty(value = "产品描述",example = "真棒啊")
    @TableField("productDesc")
    private String productDesc;

    @ApiModelProperty(value = "产品状态",example = "1")
    @TableField("productStatus")
    private Integer productStatus;


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
                ", productStatus=" + productStatus +
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

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }
}
