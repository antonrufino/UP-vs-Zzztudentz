// Handles grid on which plants are placed.

package avs.models;

import java.awt.Rectangle;

public class Grid {
    //Constants for computations for grid.
    final static int TILE_WIDTH = 113;
    final static int TILE_HEIGHT = 109;
    final static int BUS_OFFSET = 139;
    final static int SIDEWALK_OFFSET = 157;

    public static final int ROWS = 5;
    public static final int COLS = 9;
    public static final int WIDTH = COLS * TILE_WIDTH;
    public static final int HEIGHT = ROWS * TILE_HEIGHT;

    private Rectangle[][] tiles = new Rectangle[ROWS][COLS]; // Contains bounds of tiles.
    private Plant[][] plants = new Plant[ROWS][COLS]; // Contains plants in grid.

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

    // Stop all the plant threads in the game.
    public void killPlants() {
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLS; ++j) {
                if (plants[i][j] != null) {
                    plants[i][j].kill();
                }
            }
        }
    }

    // Get the bounds of a tile
    public Rectangle getRectangle(int row, int col) {
        return tiles[row][col];
    }

    // Check if a tile has a plant.
    public boolean hasPlant(int row, int col) {
        return plants[row][col] != null;
    }

    // Get the plant contained by a tile.
    public synchronized Plant getPlant(int row, int col) {
        return plants[row][col];
    }

    // Add a plant to the grid.
    public synchronized void setPlant(int row, int col, Plant plant) {
        if(plant != null){
            int x = BUS_OFFSET + col * TILE_WIDTH;
            x += (TILE_WIDTH - plant.getWidth()) / 2;

            int y = SIDEWALK_OFFSET + row * TILE_HEIGHT - 18;
            y += (TILE_HEIGHT - plant.getHeight()) / 2;

            plant.setX(x);
            plant.setY(y);

            plant.setRow(row);
            plant.setCol(col);
        }

        plants[row][col] = plant;
    }
}
