package concert;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

// 切面
@Aspect
public class Audience {
    // 以下方法我们称为横切关注点，是织入(Weaving)
    // 在执行之前
    @Before("execution(* concert.Performance.perform( .. ))")
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }

    @Before("execution(* concert.Performance.perform( .. ))")
    public void takeSeats() {
        System.out.println("Taking seats");
    }

    // 在执行之后
    @AfterReturning("execution(* concert.Performance.perform( .. ))")
    public void applause() {
        System.out.println("CLAP CLAP CLAP!!!");
    }

    @AfterThrowing("execution(* concert.Performance.perform( .. ))")
    public void demandRefund() {
        System.out.println("Demand a refund");
    }
}