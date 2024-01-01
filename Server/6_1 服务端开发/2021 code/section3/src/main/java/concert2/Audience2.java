package concert2;

import org.aspectj.lang.ProceedingJoinPoint;

public class Audience2 {

    public void watchPerformance(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println(".Silencing cell phones");
            System.out.println(".Taking seats");
            // 可以执行很多次
            joinPoint.proceed();
            System.out.println(".CLAP CLAP CLAP!!!");
        } catch (Throwable e) {
            System.out.println(".Demanding a refund");
        }
    }
}