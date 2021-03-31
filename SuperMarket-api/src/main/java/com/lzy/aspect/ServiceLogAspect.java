package com.lzy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {

    final Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);

    @Around("execution(* com.lzy.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 使用日志输出
        log.info("========开始执行{}，{}========",//
                 joinPoint.getTarget().getClass(),joinPoint.getSignature().getName());
        // 执行目标
        Object result = joinPoint.proceed();
        return result;
    }

}
