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
# use of annotation
## Define an annotation

    package com.arun.config;
    
    import java.lang.annotation.ElementType;
    import java.lang.annotation.Retention;
    import java.lang.annotation.RetentionPolicy;
    import java.lang.annotation.Target;
    
    /**
     * Created by Adwiti on 7/23/2018.
     */
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RsLogging {
    }

## Define a class with Aspect 

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
    
        @AfterThrowing(pointcut = "@annotation(com.arun.config.RsLogging)", throwing = "e")
        public void afterThrow(Exception e) {
            logger.info("Throwing exception : {}", e.getMessage());
        }
    }

## Annotate the methods where logging is required
    package com.arun.service;
    
    import com.arun.config.RsLogging;
    import com.arun.model.Student;
    import org.springframework.stereotype.Service;
    
    /**
     * Created by Adwiti on 7/22/2018.
     */
    @Service
    public class StudentServiceImpl implements StudentService {
    
        private Student student;
    
        @Override
        @RsLogging
        public Student getStudentBasedOnId(String id) throws Exception {
            Student student = new Student("1", "Arun", "23", "scottsdale");
            if (id.equals("2")) {
                throw new Exception("Test Exception");
            }
            return student;
        }
    }

# types of pointcut

## execution(* PACKAGE.*.*(..))
    
    first wildcard is for any return type.
    second wildcard is for any class.
    third wildcard is for any method of any class in that package
    (..) pattern matches any number of argument.
    
### example 

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

## within("full path to the class or package")

### example 
    "within(com.arun.controller.*)" -> this will log all the methods of all the classes under controller
    
    "within(com.arun.controller.StudentController)" -> This will log all the methods under the class StudentController

    
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
    public class PointCutUsingWithin {
    
        private Logger logger = LoggerFactory.getLogger(this.getClass());
    
        @Before("within(com.arun.controller.EmployeeController)")
        public void beforeMethodCall(JoinPoint joinPoint) {
            logger.info("calling method {}", joinPoint.getSignature().getName());
        }
    }


## to Specify based on stereoType

    @Before("@within(org.springframework.stereotype.Service)")
        public void beforeMethodCallForService(JoinPoint joinPoint) {
            logger.info("Service steroeType call {} ", joinPoint.getSignature().getName());
    
        }
        
 
## to log the time for a method.

we will be using RsLogging annotation

    @Around("@annotation(com.arun.config.RsLogging)")
        public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
            long start = System.currentTimeMillis();
            Object proceed = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;
            logger.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);
            return proceed;
        }