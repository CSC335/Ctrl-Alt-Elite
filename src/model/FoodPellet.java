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
    private int SIZE = 20; // size of a tile
    private Color color;
    private int x;
    private int y;
    private int tileX;
    private int tileY;
    private boolean isEaten = false;
    private GraphicsContext gc;
    private Color[] pelletColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK,
            Color.PURPLE};
    
    /**
     * Create a new FoodPellet object for a given board size
     *
     * @param boardWidth  An integer representing the board width
     * @param boardHeight An integer representing the board height
     * @param gc          A GraphicsContext used to draw the FoodPellet
     */
    public FoodPellet(int boardWidth, int boardHeight, GraphicsContext gc) {
        this.gc = gc;
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
        int index = random.nextInt(6);
        Color color = pelletColors[index];
        this.color = color;
        
        random = new Random();
        x = random.nextInt(boardWidth);
        y = random.nextInt(boardHeight);
        
        tileX = Math.floorDiv(x, SIZE);
        tileY = Math.floorDiv(y, SIZE);
        
        draw();
    }
    
    /**
     * Draw the FoodPellet at the current location on the board
     */
    public void draw() {
        gc.setFill(color);
        gc.fillRect(tileX * SIZE, tileY * SIZE, SIZE, SIZE);
    }
    
    /**
     * Respawn the FoodPellet and set its status to no longer be eaten
     *
     * @param boardWidth  An integer representing the width of the board
     * @param boardHeight An integer representing the height of the board
     */
    public void respawn(int boardWidth, int boardHeight) {
        spawn(boardWidth, boardHeight);
        isEaten = false;
    }
    
    /**
     * Detect collision with the head of the Snake
     *
     * @param head A Point object representing the head of the Snake
     * @return True if the head of the Snake has collided, False otherwise
     */
    public boolean detectCollision(Point head) {
        if (head.getTileX() == tileX && head.getTileY() == tileY) {
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
     * Get the current x-coordinate of the FoodPellet
     *
     * @return An integer representing the x-coordinate on the board
     */
    public double getX() {
        return x;
    }
    
    /**
     * Get the current y-coordinate of the FoodPellet
     *
     * @return An integer representing the y-coordinate on the board
     */
    public double getY() {
        return y;
    }
}
