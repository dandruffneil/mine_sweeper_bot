package project;

public class Mine extends Item{
    
    public Mine() {
        super("x", -1);
    }

    @Override
    public void trigger() {
        System.out.println("ouch");
    }
}
