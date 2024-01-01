package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import soundsystem.CDPlayerConfig;
import soundsystem.MediaPlayer;

public class MyAnnotationApp {
    public static void main(String[] args) {
        // 使用Annotation配置的上下文信息，进入到对应的类的容器环境中并按照定义创建
        ApplicationContext ctx = new AnnotationConfigApplicationContext(CDPlayerConfig.class);
        // 从容器上下文环境中获取到对应的对象
        MediaPlayer player = ctx.getBean(MediaPlayer.class);
        player.play();
    }
}
