package com.lx.authoritymanagement.controller;

import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/25 23:30
 * @Version 1.0
 */
@Controller
public class HelloController {

    /**
     * 测试springboot整合thymeleaf
     * @return 返回到success界面
     */
    @RequestMapping(value = "/sayHello")
    public String sayHello(){
        return "success";
    }


    /**
     * 跳转到欢迎界面
     * @return 欢迎界面
     */
    @RequestMapping("/welcome")
    public String toWelcome(){
        return "welcome";
    }
}
