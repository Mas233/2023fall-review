package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 使用JavaConfiguration，告诉Spring该类作为一个配置类进行使用
 * 配置Bean和Bean之间的关系
 * 可以解决第三方库的问题
 */
@Configuration
public class CDPlayerConfig {

    // 不声明的话Bean名称和方法名是一致的，使用name参数进行声明，命名多个只会调用第一个
    @Bean
    // @Scope 可以使用声明要不要用单实例，还是创建新的部分。
    public CompactDisc compactDisc() {
        return new SgtPeppers();
    }

    /**
     * 参数同样是从容器池中查找
     * @param cd
     * @return
     */
    @Bean
    public CDPlayer cdPlayer(CompactDisc cd) {
        // 也可以直接调用上面的方法，但是这个方法调用并不是正常的，而是被Spring拦截了
        return new CDPlayer(compactDisc());
        // return new CDPlayer(cd);
    }

//    @Bean
//    public CDPlayer cdPlayer2(CompactDisc cd){
//        // 这样子得到的仍然是单实例的，多个实例需要使用@Qulifier("bean名称")来完成
//        return new CDPlayer(compactDisc());
//    }

}
