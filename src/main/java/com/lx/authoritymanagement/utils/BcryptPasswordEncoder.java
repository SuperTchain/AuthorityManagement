package com.lx.authoritymanagement.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @ClassName BcryptPasswordEncoder
 * @Description TODO
 * @Author ASUS
 * @Date 2020/6/8 11:42
 * @Version 1.0
 */
public class BcryptPasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

}
