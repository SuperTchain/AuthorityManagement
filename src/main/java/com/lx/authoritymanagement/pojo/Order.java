package com.lx.authoritymanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Order
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/27 11:29
 * @Version 1.0
 */
@TableName("order")
@ApiModel(value="Order对象", description="订单表")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id，主键")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "下单时间")
    @TableField("orderTime")
    private Date orderTime;

    @ApiModelProperty(value = "订单描述")
    @TableField("orderDesc")
    private String orderDesc;

    @ApiModelProperty(value = "订单数量")
    @TableField("orderNumber")
    private Integer orderNumber;

    @ApiModelProperty(value = "订单状态 1 已完成， 0 未完成")
    @TableField("orderStatus")
    private Integer orderStatus;

    @ApiModelProperty(value = "产品id")
    @TableField("productId")
    private Integer productId;

    @ApiModelProperty(value = "下单用户id")
    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "支付状态 1 已支付，0 未支付")
    @TableField("payType")
    private Integer payType;

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderTime=" + orderTime +
                ", orderDesc='" + orderDesc + '\'' +
                ", orderNumber=" + orderNumber +
                ", orderStatus=" + orderStatus +
                ", productId=" + productId +
                ", userId=" + userId +
                ", payType=" + payType +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
