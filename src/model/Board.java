package model;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents the game board of a game of Snake
 *
 * @author Kayla Pierson
 */

public class Board {
    private int width;
    private int height;
    private Color backgroundColor;
    private ArrayList<FoodPellet> foodPellets;
    private GraphicsContext gc;
    private Snake snake;
    
    /**
     * Create a new game board with the specified size and background color
     *
     * @param width           An integer representing the width of the board
     * @param height          An integer representing the height of the board
     * @param backgroundColor A Color representing the color of the board
     * @param gc              A GraphicsContext used to draw the game board
     * @param snake           A Snake object representing the player
     */
    public Board(int width, int height, Color backgroundColor, GraphicsContext gc, Snake snake) {
        this.gc = gc;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.snake = snake;
        foodPellets = new ArrayList<>();
        initialize();
    }
    
    /**
     * Set up the board by drawing the background color and spawn in the first FoodPellet
     */
    public void initialize() {
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, width, height);
        
        // spawn 5 random food pellets - adjust number if not enough/too much
        for (int i = 0; i < 5; i++) {
            spawnFoodPellet();
        }
    }
    
    /**
     * Create a new FoodPellet and spawn it on the board
     */
    public void spawnFoodPellet() {
        FoodPellet pellet = new FoodPellet(width, height, gc);
        foodPellets.add(pellet);
    }
    
    /**
     * Redraw the game board, FoodPellet, and Snake
     */
    public void update() {
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, width, height);
        
        for (FoodPellet pellet : foodPellets)
            pellet.draw();
        
        snake.draw(gc);
    }
    
    /**
     * Get the width of the board
     *
     * @return An integer representing the width of the board
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Get the height of the board
     *
     * @return An integer representing the height of the board
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * A list of all the current FoodPellets on the board
     *
     * @return An ArrayList of FoodPellets
     */
    public ArrayList<FoodPellet> getFoodPellets() {
        return foodPellets;
    }
    
    /**
     * Checks collision of the Snake's body that results in a game over
     *
     * @return True if the Snake has hit the walls of the board or itself, False otherwise
     */
    public boolean isCollision() {
        Point head = snake.getHead();
        
        // collided with walls
        if (head.getX() < 0 || head.getX() > width || head.getY() < 0 || head.getY() > height)
            return true;
        
        // collided with its own body
        if (snake.getBody().size() != 1) {
        	for (int i = 1; i<snake.getBody().size(); i++) {
        		Point segment = snake.getBody().get(i);
        	
                if (head.getX() == segment.getX() && head.getY() == segment.getY()) {
                	System.out.println("collided with own body");
                	return true;
                }
            }
        }
        
        return false;
    }
}
