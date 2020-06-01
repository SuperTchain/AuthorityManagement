package com.lx.authoritymanagement.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @ClassName HelloController
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/25 23:30
 * @Version 1.0
 */
@Controller
@Api(tags = "登录接口")
public class LoginController {

    /**
     * 登录跳转到主界面
     * @return 跳转到主界面
     */
    @PostMapping(value = "/login")
    public String toLogin(){
        return "main";
    }

    /**
     * 测试springboot整合thymeleaf
     * @return 返回到success界面
     */
    @GetMapping(value = "/logout")
    public String logout(){
        return "login";
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
