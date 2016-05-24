package avs.models;

public abstract class Entity implements Runnable {
    protected int x;
    protected int y;
    protected boolean isAlive;
    protected String sprite;

    public Entity(int x, int y, String sprite) {
        this.x = x;
        this.y = y;
        this.isAlive = true;
        this.sprite = sprite;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public String getSprite() { return this.sprite; }
}
