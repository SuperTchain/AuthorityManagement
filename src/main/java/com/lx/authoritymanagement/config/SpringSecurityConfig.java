package com.lx.authoritymanagement.config;

import com.lx.authoritymanagement.handler.MyAuthenticationFailHandler;
import com.lx.authoritymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName SpringSecurityConfig
 * @Description TODO
 * @Author ASUS
 * @Date 2020/6/7 21:53
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MyAuthenticationFailHandler authenticationFailHandler;
    /**
     * 配置userDetails的数据源，密码加密格式
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("{noop}123456").roles("USER");
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被SpringSecurity拦截的问题
        //这样我的webapp下static里的静态资源就不会被拦截了，也就不会导致我的网页的css全部失效了……
        web.ignoring().antMatchers("/static/**");
    }

    /**
     * 拦截页面
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                添加过滤界面
                .antMatchers("/login.html","/forget.html", "/css/**", "/img/**", "/js/**", "/layui/**", "/webjars/**","/getCode","/login").permitAll()
//                拦截所有页面
                .antMatchers("/**").hasAnyRole("USER")
                .anyRequest()
                .authenticated()
                .and()
//                登录表单
                .formLogin()
                //自定义用户名请求参数名
                .usernameParameter("account")
                //自定义密码请求参数名
                .passwordParameter("password")
//                表单界面
                .loginPage("/toLogin")
//                表单请求 可以不存在 但要与表但action地址一致
                .loginProcessingUrl("/login")
//                成功请求 post请求
                .successForwardUrl("/main")
//                .defaultSuccessUrl("/main")
//                .failureHandler(authenticationFailHandler)
//                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
//                请求头设置
                .headers()
//                允许嵌套页面
                .frameOptions()
                .disable()
                .and()
//                开启记住我
                .rememberMe()
                .rememberMeCookieName("remember-me")
                .tokenValiditySeconds(120)
                .and()
//                关闭csrf
                .csrf()
                .disable();

    }
}
