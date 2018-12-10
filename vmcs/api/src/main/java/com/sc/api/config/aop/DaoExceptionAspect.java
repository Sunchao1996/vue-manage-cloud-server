package com.sc.api.config.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**dao层的异常信息
 * Created by 孔垂云 on 2017/8/19.
 */
@Aspect
@Component
public class DaoExceptionAspect {
    private static Logger logger = LoggerFactory.getLogger("daoLog");

    @AfterThrowing(value = "execution (* com.sc.*.dao.*.*(..))", throwing = "e")
    public void loggingException(JoinPoint joinPoint, Exception e) {
        // 拦截的实体类
        Object target = joinPoint.getTarget(); // 拦截的方法名称
        e.printStackTrace();
        String methodName = joinPoint.getSignature().getName();
        e.printStackTrace();
        logger.error("实体类:" + target);
        logger.error("方法名:" + methodName);
        logger.error("异常类名：" + joinPoint.getSignature().getName().getClass());
        // 得到被拦截方法参数，并打印
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logger.error("抛异常拦截： 被拦截到的方法参数：" + i + " -- " + args[i]);
        }
        logger.error("异常信息: " + e.getMessage());
    }
}
