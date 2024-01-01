package soundsystem;

import org.springframework.beans.factory.BeanNameAware;

/**
 * BeanNameAware实现后，在创建bean之后会回调setBeanName来打印方法的名字
 */
public class CDPlayer implements MediaPlayer, BeanNameAware {
    private CompactDisc cd;

    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("====" + name);
    }
}
