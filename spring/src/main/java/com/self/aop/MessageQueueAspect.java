package com.self.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class MessageQueueAspect {
    private Logger logger = Logger.getLogger(this.getClass().toString());
    @Pointcut("execution(* com.self.activemq.producer.ProduceService.*(..))")
    public void aspect() {}

    /**
     * 前置增强
     * @param joinPoint
     */
    @Before("aspect()")
    public void beforeAspect(JoinPoint joinPoint) {
        logger.info("发送消息前执行：切入点[joinPoint]==" + joinPoint);
        logger.info("发送消息前执行：目标对象[joinPoint.getTarget()]==" + joinPoint.getTarget().toString());
        logger.info("发送消息前执行：参数列表[jp.getArgs()]==" + Arrays.toString(joinPoint.getArgs()));
        logger.info("发送消息前执行：代理对象本身[jp.getArgs()]==" + joinPoint.getThis().toString());

        // 获取目标方法的参数
        Object[] args = joinPoint.getArgs();
        if (args == null || args[0] == "")
            throw new IllegalArgumentException("消息不能为空！");
    }

    /**
     * 异常处理
     * @param joinPoint
     */
    @AfterThrowing("aspect()")
    public void exceptionAspect(JoinPoint joinPoint) {
        logger.info("出异常了！");
    }
}
