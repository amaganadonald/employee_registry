package com.amagana.technicaltest.employeemanagement.aspect;

import com.amagana.technicaltest.employeemanagement.exceptions.EmployeeBussnessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.amagana.technicaltest.employeemanagement.controller..*(..))")
    public void pointCutMethod() {}

    @Around(value = "pointCutMethod()")
    public Object logAround(ProceedingJoinPoint joinPoint)  {
        long startTime = System.currentTimeMillis();
        log.info("Starting execution of {}.{}", joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName());
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new EmployeeBussnessException("An error occurred during execution of method: " + joinPoint.getSignature().getName());
        }  finally {
            long endTime = System.currentTimeMillis();
            log.info("Execution of {}.{} took {} ms.", joinPoint.getSignature().getDeclaringType().getSimpleName(),
                    joinPoint.getSignature().getName(), endTime - startTime);
        }
    }

    @AfterThrowing(value = "pointCutMethod", throwing = "errorsException")
    public void logAfterThrowing(ProceedingJoinPoint joinPoint, RuntimeException errorsException) {
        log.error("An error occurred during execution of {}.{}: {}", joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(), errorsException.getMessage());
    }
}
