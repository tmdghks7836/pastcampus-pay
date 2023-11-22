//package com.fastcampuspay.common;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class LoggingAspect {
//
//    public LoggingAspect(LoggingProducer loggingProducer) {
//
//        this.loggingProducer = loggingProducer;
//    }
//
//    private final LoggingProducer loggingProducer;
//
//    @Before("execution(* com.fastcampuspay.*.adapter.in.web.*.*(..))")
//    public void dd(JoinPoint joinPoint){
//        String methodName = joinPoint.getSignature().getName();
//
//        loggingProducer.sendMessage("logging", "Before executing method: " + methodName);
//    }
//
//}
