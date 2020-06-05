package com.lx.authoritymanagement.controller;

import cn.dsna.util.images.ValidateCode;
import com.lx.authoritymanagement.annotations.RecordOperation;
import com.lx.authoritymanagement.pojo.User;
import com.lx.authoritymanagement.service.UserService;
import com.lx.authoritymanagement.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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
     * 引入service
     */
    @Autowired
    private UserService userService;

    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(LoginController.class);

    /**
     * 登录校验
     * @return 登录校验
     */
    @PostMapping(value = "/login")
    @ResponseBody
    @ApiOperation("登录校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value = "账户名",dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",dataType = "String"),
            @ApiImplicitParam(name = "code",value = "验证码",dataType = "String"),
    })
    public Result login(String account,String password,String code,HttpServletRequest request){
        Result result = userService.findUserByName(account, password, code, request);
        return result;
    }

    /**
     * 测试springboot整合thymeleaf
     * @return 返回到success界面
     */
    @GetMapping(value = "/logout")
    @ApiOperation("登出")
    @RecordOperation(name = "登出操作",url = "/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "login";
    }


    /**
     * 跳转到欢迎界面
     * @return 欢迎界面
     */
    @GetMapping("/welcome")
    @ApiOperation("跳转到欢迎界面")
    public String toWelcome(){
        return "welcome";
    }

    /**
     * 返回主页面(main.html)
     * @param request 请求
     * @return 主界面
     */
    @GetMapping("/main")
    @ApiOperation("登录成功跳转到主界面")
    @RecordOperation(name = "登录",url = "/login")
    public String main(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user==null){
            //没有登录
            return "login";
        }else{
            //正常登录返回首页
            return "main";
        }
    }


    /**
     * 获取验证码
     * @param response 响应
     * @param request 请求
     */
    @GetMapping("/getCode")
    @ApiOperation("获取验证码")
    public void getCode(HttpServletResponse response, HttpServletRequest request){
        //验证码验证
        //参数分别是：宽   高    字符数量    干扰线数量
        ValidateCode vs = new ValidateCode(120, 40, 5, 10);
        logger.info("生成的验证码"+vs.getCode());
        //将本地生成的验证码存到session中(持续时间到本次会话结束之前)
        //session是可以设置有效期的，服务器级的缓存，服务器停止，session就会被清空
        HttpSession session = request.getSession();
        //将验证码存放到session中
        session.setAttribute("code",vs.getCode());
        //将验证码使用响应输出到前端页面
        try {
            vs.write(response.getOutputStream());
        } catch (IOException e) {
            logger.error("错误",e);
        }
    }
}
