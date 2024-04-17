package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Snake player in a game of Snake
 *
 * @author Vannela Chatla
 */

public class Snake {
    private static int TILE_SIZE = 20;
    private final List<Tile> body;
    private Direction direction;
    private Color color;
    
    /**
     * Create a new Snake object with default location and direction
     */
    public Snake() {
        body = new ArrayList<>();
        body.add(new Tile(10, 10));
        direction = Direction.RIGHT;
    }
    
    /**
     * Create a new Snake object with a specified location and color with the default direction
     *
     * @param x     The x-coordinate of the desired location
     * @param y     The y-coordinate of the desired location
     * @param color The desired color of the Snake
     */
    public Snake(int x, int y, Color color) {
        this.body = new ArrayList<>();
        body.add(new Tile(x, y));
        this.color = color;
        direction = Direction.RIGHT;
    }
    
    /**
     * Get the location of the head of the Snake
     *
     * @return a Point object with the x- and y-coordinate of the head of the Snake
     */
    public Tile getHead() {
        return body.get(0);
    }
    
    /**
     * Draw the snake to the screen one segment at a time
     *
     * @param gc A GraphicsContext object used to draw to the screen
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        // Draw each body segment of the snake
        for (Tile point : body) {
            gc.fillRect(point.getTileX() * TILE_SIZE, point.getTileY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }
    
    /**
     * Get the entire Snake's body
     *
     * @return A List of Point objects that give the coordinates of each segment
     */
    public List<Tile> getBody() {
        return body;
    }
    
    /**
     * Move the Snake by one tile in the current direction
     */
    public void move() {
        Tile head = body.get(0);
        Tile newHead = new Tile((head.getTileX() + direction.getX()) * TILE_SIZE,
                (head.getTileY() + direction.getY()) * TILE_SIZE);
        body.add(0, newHead);
        body.remove(body.size() - 1);
    }
    
    /**
     * Grow the Snake by one segment if the Snake has consumed a Pellet
     */
    public void grow() {
        Tile last = body.get(body.size() - 1);
        Tile newLast = new Tile((last.getTileX() - direction.getX()) * TILE_SIZE,
                (last.getTileX() - direction.getY()) * TILE_SIZE);
        body.add(newLast);
    }
    
    /**
     * Set the current direction of the Snake based off user input
     *
     * @param direction The new direction the Snake should move
     */
    public void setDirection(Direction direction) {
        if (this.direction == Direction.UP && direction == Direction.DOWN ||
                this.direction == Direction.DOWN && direction == Direction.UP ||
                this.direction == Direction.LEFT && direction == Direction.RIGHT ||
                this.direction == Direction.RIGHT && direction == Direction.LEFT) {
            return;
        }
        this.direction = direction;
    }

    
    
    /**
     * Get the current size of the Snake
     *
     * @return An integer representing the size of the Snake
     */
    public int size() {
        return body.size();
    }
    
    /**
     * An enum that represents one of the four directions: Up, Down, Left, or Right
     */
    public enum Direction {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);
        
        private final int x;
        private final int y;
        
        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int getX() {
            return x;
        }
        
        public int getY() {
            return y;
        }
        
    }
    
    public Direction getDirection() {
        return this.direction;
    }

    public boolean hasCollidedWithSelf() {
        Tile head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }
}
