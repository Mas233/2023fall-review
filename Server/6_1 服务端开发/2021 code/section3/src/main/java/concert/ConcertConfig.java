package concert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy //开启AspectJ的自动代理机制
public class ConcertConfig {
    @Bean
    public Performance concert() {
        return new Concert();
    }

    @Bean
    public Performance concert2() {
        return new Concert2();
    }

    // 只要恢复这部分，则可以完成织入
//    @Bean
//    public Audience audience() { //定义Audience的bean
//        return new Audience();
//    }


    @Bean
    public EncoreableIntroducer encoreableIntroducer() {
        return new EncoreableIntroducer();
    }
}