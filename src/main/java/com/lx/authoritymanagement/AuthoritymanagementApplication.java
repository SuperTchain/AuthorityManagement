package com.lx.authoritymanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author lx
 */
@SpringBootApplication()
@EnableTransactionManagement
@MapperScan(basePackages = "com.lx.authoritymanagement.dao")
public class AuthoritymanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthoritymanagementApplication.class, args);
    }

}
