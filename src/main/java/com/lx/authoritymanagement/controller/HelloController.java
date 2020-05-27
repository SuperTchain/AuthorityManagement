package com.lx.authoritymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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
        return "main";
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
