package com.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLogger {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @After("execution(public * com.service.*.save(..))")
    public void afterCallServiceSave(JoinPoint jp) {
        logger.info("Saved successfully " + jp.getSignature());
    }

    @After("execution(public * com.service.*.delete(..))")
    public void afterCallServiceDelete(JoinPoint jp) {
        logger.info("Deleted successfully " + jp.getSignature());
    }

}
