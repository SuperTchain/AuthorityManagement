package com.lx.authoritymanagement.controller;

import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.service.UserService;
import com.lx.authoritymanagement.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
    public Result findAllUser(Integer page, Integer limit) {
        Result result = userService.findAllUserByPage(page, limit);
        logger.info("查询成功");
        return result;
    }

    /**
     * 跳转到用户列表界面
     *
     * @return 用户列表界面
     */
    @GetMapping("/toUserList")
    public String tuUserList() {
        return "user/userList";
    }

    /**
     * 跳转到添加用户界面
     * @return 添加界面
     */
    @GetMapping("/toAddUser")
    public String toaddUser() {
        return "user/addUser";
    }


    /**
     * 根据传入的条件进行搜索
     *
     * @param userid   用户id
     * @param username 用户姓名
     * @param account  用户账号
     * @param page     页数
     * @param limit    每页条数
     * @return 查询结果
     */
    @GetMapping("/search")
    @ResponseBody
    public Result serachUser(Integer userid, String username, String account, Integer page, Integer limit) {
        Result result = userService.search(userid, username, account, page, limit);
        logger.info("条件搜索查询成功");
        return result;
    }


    /**
     * 添加用户
     * @param user
     * @return 添加结果
     */
    @PostMapping("/addUser")
    @ResponseBody
    public Result addUser(User user) {
        Result result =  userService.addUser(user);
        logger.info("成功添加用户");
        return result;
    }

    @PostMapping("/batchDelete")
    @ResponseBody
    public Result batchDeleteByUserId(String[] ids){
        Result result =  userService.batchDeleteByUserId(ids);
        logger.info("成功删除用户");
        return result;
    }
}
