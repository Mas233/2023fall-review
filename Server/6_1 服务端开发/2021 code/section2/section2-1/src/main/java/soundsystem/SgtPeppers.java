package soundsystem;
import org.springframework.stereotype.Component;

// 实例化该类的实例，作为一个Bean
// Component也是可以用name来声明新名称的
@Component
public class SgtPeppers implements CompactDisc {

  private String title = "Sgt. Pepper's Lonely Hearts Club Band";  
  private String artist = "The Beatles";

  @Override
  public void play() {
    System.out.println("Playing " + title + " by " + artist);
  }
  
}
