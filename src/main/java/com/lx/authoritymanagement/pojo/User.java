package com.lx.authoritymanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName User
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/26 0:20
 * @Version 1.0
 */
@TableName("user")
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

   private static final long serialVersionUID = 1L;

   @ApiModelProperty(value = "用户编号，主键")
   @TableId(value = "id", type = IdType.AUTO)
   private Integer id;

   @ApiModelProperty(value = "用户真实姓名")
   @TableField("userName")
   private String userName;

   @ApiModelProperty(value = "用户昵称")
   @TableField("nickName")
   private String nickName;

   @ApiModelProperty(value = "用户账号")
   @TableField("account")
   private String account;

   @ApiModelProperty(value = "用户密码")
   @TableField("password")
   private String password;

   @ApiModelProperty(value = "用户性别")
   @TableField("gender")
   private Integer gender;

   @ApiModelProperty(value = "用户年龄")
   @TableField("age")
   private Integer age;

   @ApiModelProperty(value = "用户邮箱地址")
   @TableField("email")
   private String email;

   @ApiModelProperty(value = "角色id")
   @TableField("roleId")
   private Integer roleId;

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", userName='" + userName + '\'' +
              ", nickName='" + nickName + '\'' +
              ", account='" + account + '\'' +
              ", password='" + password + '\'' +
              ", gender=" + gender +
              ", age=" + age +
              ", email='" + email + '\'' +
              ", roleId=" + roleId +
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

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getNickName() {
      return nickName;
   }

   public void setNickName(String nickName) {
      this.nickName = nickName;
   }

   public String getAccount() {
      return account;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Integer getGender() {
      return gender;
   }

   public void setGender(Integer gender) {
      this.gender = gender;
   }

   public Integer getAge() {
      return age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Integer getRoleId() {
      return roleId;
   }

   public void setRoleId(Integer roleId) {
      this.roleId = roleId;
   }
}