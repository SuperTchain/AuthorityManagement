package com.lx.authoritymanagement.controller;

import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.service.UserService;
import com.lx.authoritymanagement.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/27 10:37
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(UserController.class);
    /**
     * 引入服务
     */
    @Autowired
    private UserService userService;


    @GetMapping("/findAllUser")
    @ResponseBody
    public Result findAllUser(Integer page,Integer limit) {
        Result result = userService.findAllUserByPage(page, limit);
        logger.info("查询成功");
        return result;
    }

    /**
     * 跳转到用户列表界面
     * @return 用户列表界面
     */
    @GetMapping("/toUserList")
    public  String tuUserList(){
        return "user/userList";
    }

    @GetMapping("/search")
    public String serachUser(String timerange,String keyword,Integer page,Integer limit){
        return "main";
    }
}
