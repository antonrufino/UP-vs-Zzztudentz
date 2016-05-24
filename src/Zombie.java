package avs.models;

public class Zombie extends Entity {
    private int hp;

    public Zombie(int x, int y, int hp) {
        super(x, y, "normal-zombie.jpg");
        this.hp = hp;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; ++i) {
                System.out.println("TTTRRREEEEESSSSSS");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
