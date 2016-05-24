package avs.models;

public class Energy extends Entity{
    public static final int AMOUNT = 1; // Temporary value

    public Energy(int x, int y) {
        super(x, y, "temporary.jpg");
    }

    public void run() {
        System.out.println("A wild Energy instance is updating its state.");
    }
}
