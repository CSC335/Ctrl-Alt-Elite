package model;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a Food Pellet to be eaten by a player in a game of Snake
 *
 * @author Kayla Pierson
 */

public class FoodPellet {
    private static int TILE_SIZE = 20;
    private Color color;
    private Tile currentTile;
    private boolean isEaten = false;
    private GraphicsContext gc;  // This is used only for drawing
    private Color[] pelletColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.CYAN, Color.BLUE, Color.PINK, Color.PURPLE};
    private boolean isWhite;
    
    /**
     * Create a new FoodPellet object for a given board size
     *
     * @param boardWidth  An integer representing the board width
     * @param boardHeight An integer representing the board height
     * @param gc          A GraphicsContext used to draw the FoodPellet
     */
    public FoodPellet(int boardWidth, int boardHeight, boolean isWhite, GraphicsContext gc) {
        this.gc = gc;
        this.isWhite = isWhite;;
        spawn(boardWidth, boardHeight);
    }
    
    public FoodPellet(int boardWidth, int boardHeight) {
        spawn(boardWidth, boardHeight);
    }

    /**
     * Spawn the FoodPellet at a new location on the board
     *
     * @param boardWidth  An integer representing the width of the board
     * @param boardHeight An integer representing the height of the board
     */
    public void spawn(int boardWidth, int boardHeight) {
        Random random = new Random();
        int index;
        
        if (isWhite) {
            this.color = Color.WHITE;
        } else {
            index = random.nextInt(pelletColors.length); // corrected to use the length of the array
            this.color = pelletColors[index];
        }

        int x = random.nextInt(boardWidth - 2 * TILE_SIZE);
        int y = random.nextInt(boardHeight - 2 *TILE_SIZE);

        currentTile = new Tile(x, y);
    }

    /**
     * Draw the FoodPellet at the current location on the board
     */
    public void draw() {
        gc.setFill(color);
        gc.fillRect(currentTile.getTileX() * TILE_SIZE, currentTile.getTileY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    
    /**
     * Respawn the FoodPellet and set its status to no longer be eaten
     *
     * @param boardWidth  An integer representing the width of the board
     * @param boardHeight An integer representing the height of the board
     */
    public void respawn(int boardWidth, int boardHeight) {
        Tile oldTile = new Tile(currentTile.getTileX(), currentTile.getTileY());
        do {
            spawn(boardWidth, boardHeight);
        } while (oldTile.equals(currentTile));
        isEaten = false;
    }

    
    /**
     * Detect collision with the head of the Snake
     *
     * @param head A Tile object representing the head of the Snake
     * @return True if the head of the Snake has collided, False otherwise
     */
    public boolean detectCollision(Tile head) {
        if (head.equals(currentTile)) {
            isEaten = true;
            return true;
        }
        return false;
    }
    
    /**
     * Get whether the FoodPellet is eaten
     *
     * @return A boolean representing the FoodPellet's eaten status
     */
    public boolean isEaten() {
        return isEaten;
    }
    
    /**
     * Get the current tile of the FoodPellet
     *
     * @return A Tile object representing the tile location on the board
     */
    public Tile getCurrentTile() {
        return currentTile;
    }
}
