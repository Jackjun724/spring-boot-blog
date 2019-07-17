package com.jacknoob.blog.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author JackJun
 * 2019/7/17 11:19
 * Life is just about survival.
 */
@Aspect
public class OperationTime {

    private static final Logger logger = LoggerFactory.getLogger(OperationTime.class);

    @Pointcut("execution(com.jacknoob.blog.web.rest.* *(*))")
    public void point() {
    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean isDebug = logger.isDebugEnabled();
        if (isDebug) {
            logger.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        long time = System.currentTimeMillis();
        Object res = joinPoint.proceed();
        if (isDebug) {
            logger.debug("Exit: {}.{}() with time = {},result = {}", System.currentTimeMillis() - time, joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), res);
        }
        return res;
    }

}
