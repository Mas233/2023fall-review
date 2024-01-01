package concert;

public class DefaultEncoreable implements Encoreable {
    private Integer count = 1;

    @Override
    public void performEncore() {
        System.out.println("perform the encore!");
        System.out.println(count);
        count++;
    }

}