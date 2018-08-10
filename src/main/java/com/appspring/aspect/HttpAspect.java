package com.appspring.aspect;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final static Logger log = LoggerFactory.getLogger(HttpAspect.class);
    @Pointcut("execution (public * com.appspring.controller.UserController.*(..))")
    public void log() {

    }

    @Before("log()")
    public void beforeAction() {
        log.info("before");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

    }

    @After("log()")
    public void afterAction() {
        log.info("after");
    }
}
