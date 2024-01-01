package soundsystem;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Java的Root配置，添加了子配置
 */
@Configuration
// 数组，可以import很多
@Import({CDPlayerConfig.class})
// 引入外部资源
@ImportResource("classpath:cd-config.xml")
public class SoundSystemConfig {

}
