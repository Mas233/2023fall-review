package concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience2 {
    @Pointcut("execution(* concert.Performance.perform( .. )) ")
    public void performance() {
    }

    // 需要在对应方法前后执行
    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint joinPoint) {
        try {
            // 方法执行
            System.out.println(".Silencing cell phones");
            System.out.println(".Taking seats");
            joinPoint.proceed();
            // 方法之后执行
            System.out.println(".CLAP CLAP CLAP!!!");
        } catch (Throwable e) {
            System.out.println(".Demanding a refund");
        }
    }
}