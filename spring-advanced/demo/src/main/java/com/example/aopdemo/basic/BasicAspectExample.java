package com.example.aopdemo.basic;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(name = "examples.basic.enabled", havingValue = "true")
public class BasicAspectExample {

    private final Logger LOGGER = LoggerFactory.getLogger(BasicAspectExample.class);

    @Pointcut("execution(* com.example.aopdemo.Student.*(..))")
    public void track() {}

    @Pointcut("execution(* com.example.aopdemo.Student.echo(..))")
    public void trackEcho() {}

    @Before("track()")
    public void beforeAnyMethod(JoinPoint joinPoint) {
        LOGGER.info("Before calling {}: ", joinPoint.getSignature());
    }
    @Before("trackEcho()")
    public void beforeEcho() {
        LOGGER.info("We are doing this before echo method!");
    }

    @AfterThrowing(pointcut = "track()", throwing = "error")
    public void afterThrowing(Throwable error) {
        LOGGER.info("I caught the an {} exception!", error);
    }


}
