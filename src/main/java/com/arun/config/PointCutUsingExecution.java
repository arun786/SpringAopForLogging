package com.arun.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Adwiti on 7/23/2018.
 */
@Aspect
@Configuration
public class PointCutUsingExecution {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.arun.controller.*.*(..))")
    public void beforeMethodCall(JoinPoint joinPoint) {
        logger.info("Calling method {}, with arguments {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

}
