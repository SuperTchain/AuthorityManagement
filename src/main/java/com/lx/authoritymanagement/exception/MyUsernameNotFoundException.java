package com.lx.authoritymanagement.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName MyUsernameNotFoundException
 * @Description TODO
 * @Author ASUS
 * @Date 2020/6/9 23:53
 * @Version 1.0
 */
public class MyUsernameNotFoundException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public MyUsernameNotFoundException(String msg) {
        super(msg);
    }

    public MyUsernameNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
