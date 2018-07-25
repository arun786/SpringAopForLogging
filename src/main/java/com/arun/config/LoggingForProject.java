package com.arun.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Adwiti on 7/22/2018.
 */
@Aspect
@Component
public class LoggingForProject {
    private static final Logger logger = LoggerFactory.getLogger(LoggingForProject.class);

    @Before("@annotation(com.arun.config.RsLogging)")
    public void before(JoinPoint joinPoint) {
        logger.info("Before method call {} with argument/s {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    @After("@annotation(com.arun.config.RsLogging)")
    public void after(JoinPoint joinPoint) {
        logger.info("After method call {} with argument/s {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "@annotation(com.arun.config.RsLogging)", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        logger.info("After method call {} with argument/s {}, returning {}",
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), result);
    }

    @Around("@annotation(com.arun.config.RsLogging)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);
        return proceed;
    }
}
