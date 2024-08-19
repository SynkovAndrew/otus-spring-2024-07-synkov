package otus.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExecutionTimeLoggerAspect {

    @Around("@annotation(ExecutionTimeLogger)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        var startTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        var stopTime = System.currentTimeMillis();
        var elapsedTime = stopTime - startTime;
        System.out.println(
                "\nMethod: " + joinPoint.getSignature() +
                        ". Elapsed time: " + elapsedTime / 1000 + " seconds"
        );

        return proceed;
    }
}
