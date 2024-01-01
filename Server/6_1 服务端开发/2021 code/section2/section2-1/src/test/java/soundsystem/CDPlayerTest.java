package soundsystem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

// 启动一个Spring的上下问环境
@RunWith(SpringJUnit4ClassRunner.class)
// 声明如何进行初始化配置
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTest {

    // 可以使用右上角的debug右侧的按钮进行覆盖率检查(Run With Coverage) 源代码中左侧为绿色表示覆盖到，而红色表示未覆盖到
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

//    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Autowired
    private MediaPlayer player;

    @Autowired
    private CompactDisc cd;

    @Test
    public void cdShouldNotBeNull() {
        assertNotNull(cd);
    }

    @Test
    public void play() {
        // 先打印到Log中，然后在取出来
        player.play();
        // System.getProperty("line.separator")避免不同操作系统不同
        assertEquals(
                "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles" + System.getProperty("line.separator"),
                log.getLog());
    }

}
