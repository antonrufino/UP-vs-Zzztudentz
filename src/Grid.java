package avs.models;

import java.awt.Rectangle;

public class Grid {
    public static final int ROWS = 5;
    public static final int COLS = 9;
    private final int TILE_WIDTH = 113;
    private final int TILE_HEIGHT = 109;
    private final int BUS_OFFSET = 139;
    private final int SIDEWALK_OFFSET = 157;

    private Rectangle[][] tiles = new Rectangle[ROWS][COLS];
    private AllyEntity[][] plants = new AllyEntity[ROWS][COLS]; //temporary

    public Grid() {
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLS; ++j) {
                int x = BUS_OFFSET + j * TILE_WIDTH;
                int y = SIDEWALK_OFFSET + i * TILE_HEIGHT;
                tiles[i][j] = new Rectangle(x, y, TILE_WIDTH, TILE_HEIGHT);
                plants[i][j] = null;
            }
        }
    }

    public Rectangle getRectangle(int row, int col) {
        return tiles[row][col];
    }

    public boolean hasPlant(int row, int col) {
        return plants[row][col] != null;
    }

    public void setPlant(int row, int col, AllyEntity plant) {
        plant.setX(BUS_OFFSET + col * TILE_WIDTH);
        plant.setY(SIDEWALK_OFFSET + row * TILE_HEIGHT - 18);
        plants[row][col] = plant;
    }

    public AllyEntity getPlant(int row, int col) {
        return plants[row][col];
    }
}
