package app;

import concert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyAnnotationApp {

    // 委托的方式，委托是有侵入性
    // private logger log;
    // log.info("xxx")

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConcertConfig.class);

        // 开启切面后，实际上得到的concert已经不是本身的concert，而是一个Proxy的对象
        Performance concert = ctx.getBean("concert", Performance.class);
        concert.perform();
        Encoreable encoreable1 = (Encoreable) concert;
        encoreable1.performEncore();

        // 如何在不调整Performance 和 本方法的情况下为perform添加行为

        Performance concert2 = ctx.getBean("concert2", Performance.class);
        concert2.perform();
        Encoreable encoreable2 = (Encoreable) concert2;
        encoreable2.performEncore();

        Encoreable encoreable3 = (Encoreable) ctx.getBean("concert", Performance.class);
        encoreable3.performEncore();

        Encoreable encoreable4 = (Encoreable) ctx.getBean("concert2", Performance.class);
        encoreable4.performEncore();
    }
}
