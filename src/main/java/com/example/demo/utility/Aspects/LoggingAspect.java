package com.example.demo.utility.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.demo.controller.UserController.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Method: " + joinPoint.getSignature().toShortString());
        System.out.println("Inputs: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("Output: " + result);
        System.out.println("Execution Time: " + executionTime + " ms");

        return result;
    }
}
