# SpringAopForLogging
 
 Process to Log. Config file where Aspect is defined
 
 
     package com.arun.config;
     
     import org.aspectj.lang.JoinPoint;
     import org.aspectj.lang.annotation.After;
     import org.aspectj.lang.annotation.AfterReturning;
     import org.aspectj.lang.annotation.Aspect;
     import org.aspectj.lang.annotation.Before;
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
     
         @Before("execution(* com.arun.service.StudentService.*(..))")
         public void before(JoinPoint joinPoint) {
             logger.info("Before method call {} with argument/s {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
         }
     
         @After("execution(* com.arun.service.StudentService.*(..))")
         public void after(JoinPoint joinPoint) {
             logger.info("After method call {} with argument/s {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
         }
     
         @AfterReturning(pointcut = "execution(* com.arun.service.StudentService.*(..))", returning = "result")
         public void afterReturn(JoinPoint joinPoint, Object result) {
             logger.info("After method call {} with argument/s {}, returning {}",
                     joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), result);
         }
     }

# Applying on the whole package

    package com.arun.config;
    
    import org.aspectj.lang.JoinPoint;
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
    
        @Before("within(com.arun.service.*)")
        public void before(JoinPoint joinPoint) {
            logger.info("Before method call {} with argument/s {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
    
        @After("within(com.arun.service.*)")
        public void after(JoinPoint joinPoint) {
            logger.info("After method call {} with argument/s {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
    
        @AfterReturning(pointcut = "within(com.arun.service.*)", returning = "result")
        public void afterReturn(JoinPoint joinPoint, Object result) {
            logger.info("After method call {} with argument/s {}, returning {}",
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), result);
        }
    
        @AfterThrowing(pointcut = "within(com.arun.service.*)", throwing = "e")
        public void afterThrow(Exception e) {
            logger.info("Throwing exception : {}", e.getMessage());
        }
    }
