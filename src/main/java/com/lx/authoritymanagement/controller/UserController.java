package com.lx.authoritymanagement.controller;

import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private static Logger logger= Logger.getLogger(UserController.class);
    /**
     * 引入服务
     */
    @Autowired
    private UserService userService;

    @GetMapping("/findAllUser")
    public String findAllUser(Model model){
        List<User> allUser = userService.findAllUser();
        model.addAttribute("userList",allUser);
        logger.info("查询成功");
        return "test";
    }
}
