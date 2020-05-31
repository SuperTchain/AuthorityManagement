package com.lx.authoritymanagement.utils;

import com.lx.authoritymanagement.annotations.RecordOperation;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 记录用户操作切面类
 * @ClassName RecordOperationAspect
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/19 20:11
 * @Version 1.0
 */
@Component
@Aspect
public class RecordOperationAspect {
    /**
     * 日志监控
     */
    private static Logger logger = Logger.getLogger(RecordOperationAspect.class);

    /**
     * 抽象切点方法 到时候只代理添加注解的方法
     */
    @Pointcut("@annotation(com.lx.authoritymanagement.annotations.RecordOperation)")
    public void pt1(){};


    @After("pt1()")
    public void logMessage(JoinPoint joinPoint)  {
        // 1:在切面方法里面获取一个request，
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 2:通过springAOP切面JoinPoint类对象，获取该类，或者该方法，或者该方法的参数
        Class<? extends Object> clazz =  joinPoint.getTarget().getClass();
        // 3:获取方法名
        String controllerOperation = clazz.getName();
        if(clazz.isAnnotationPresent(RecordOperation.class)){
            // 当前controller操作的名称
            controllerOperation = clazz.getAnnotation(RecordOperation.class).name();
        }
        //获取当前方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //clazz类下的所有方法
        Method[] methods = clazz.getDeclaredMethods();
        String methodOperation = "";
        for (Method m : methods) {
            if(m.equals(method)){
                methodOperation = m.getName();
                if(m.isAnnotationPresent(RecordOperation.class)){
                    methodOperation = m.getAnnotation(RecordOperation.class).name();
                }
            }
        }
//        String username = (String) request.getSession().getAttribute("username");
//        if(username != null){
//            logger.info(username + " 执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！ ip地址为"
//                    + request.getRemoteHost());
//        }else{
//            logger.info("未知用户 执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！ ip地址为"
//                    + request.getRemoteHost());
//        }
        //获取操作时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info(" 执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！");
        logger.info("执行时间"+simpleDateFormat.format(new Date()));
    }
}
