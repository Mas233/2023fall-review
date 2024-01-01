package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import soundsystem.CDPlayer;

public class MyXmlApp {
    public static void main(String[] args) {
        // 使用XML的方式了来生成上下文环境
        ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/soundsystem.xml");
        CDPlayer player = ctx.getBean("CDPlayer", CDPlayer.class);
        player.play();
    }
}
