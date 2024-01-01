package soundsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 声明为配置类
@Configuration
/**
 * 组件扫描，让Spring到当前的包和子包下面，找到被@Component注解的类并实例化
 * 进入对应的类发现添加参数
 *      basePackages = {"package_path1", "package_path2"}，默认扫描当前目录以及所有的子包(重要)
 *      basePackageClasses = {xxx.class}，搜索对应类所在包及其子包
 *      空接口MyInter.class是为了避免之后修改后有些类不存在了
 *      更建议使用后一种，因为第一种字符串没有办法检查是否出错
 */
@ComponentScan(basePackageClasses = MyInter.class)
public class CDPlayerConfig {
}
