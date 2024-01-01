package soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 实例化该类的实例，作为一个Bean
// 想要看参数则可以进入对应的源码中从查看
@Component
public class CDPlayer implements MediaPlayer {
//    @Autowired 也可以直接添加到变量上
    private CompactDisc cd;

    /**
     * 构造方法注入
     * 实例化CDplayer时调用本构造方法对类进行构造，这时候他发现需要参数，则从容器池中查找该参数，并将该参数作为输入
     * Autowired可以添加require参数 如果值为空则没有找到也不会报错
     * @param cd
     */
    @Autowired
    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

//    /**
//     * 属性注入
//     * 同样也可以完成注入
//     * @param cd
//     */
//    @Autowired
//    public void setCd(CompactDisc cd) {
//        this.cd = cd;
//    }

    @Override
    public void play() {
        cd.play();
    }

}
