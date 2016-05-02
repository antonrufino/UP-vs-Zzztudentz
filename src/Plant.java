package avs.models;

public abstract class Plant extends Entity {
    private int hp;

    public Plant(int x, int y, String sprite, int hp) {
        super(x, y, sprite);
        this.hp = hp;
    }

    public abstract void getEaten();
}
