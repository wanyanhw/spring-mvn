package com.self.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

// 声明这是切面
@Aspect
// 声明这是组件
@Component
public class UserAspect {
    private Logger logger = Logger.getLogger("com.self.aop.UserAspect");
    // 定义切入点
    @Pointcut("execution(* com.self.service..*.*(..))")
    private void aspect() {}

    // 前置增强
    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        logger.info("切入点对象：" + joinPoint.getTarget());
    }
}
