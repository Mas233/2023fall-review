package concert;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class EncoreableIntroducer {
    // 引入，多个对象时，是单实例还是多实例？多实例 delegate-ref
    @DeclareParents(value = "concert.Performance+",//后面的+表示应用到所有实现了该接口的Bean
            defaultImpl = DefaultEncoreable.class)
    public static Encoreable encoreable;
}