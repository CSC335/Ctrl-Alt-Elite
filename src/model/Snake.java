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
    private final List<Point> body;
    private Direction direction;
    private int x;
    private int y;
    private Color color;
    
    /**
     * Create a new Snake object with default location and direction
     */
    public Snake() {
        body = new ArrayList<>();
        body.add(new Point(10, 10));
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
        body.add(new Point(x, y));
        this.x = x;
        this.y = y;
        this.color = color;
        direction = Direction.RIGHT;
    }
    
    /**
     * Get the location of the head of the Snake
     *
     * @return a Point object with the x- and y-coordinate of the head of the Snake
     */
    public Point getHead() {
        return body.get(0);
    }
    
    /**
     * Draw the snake to the screen one segment at a time
     *
     * @param gc A GraphicsContext object used to draw to the screen
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(color); // Set the fill color for the snake
        for (Point point : body) {
            gc.fillRect(point.getX(), point.getY(), 10, 10); // Draw each body segment of the snake
        }
    }
    
    /**
     * Get the entire Snake's body
     *
     * @return A List of Point objects that give the coordinates of each segment
     */
    public List<Point> getBody() {
        return body;
    }
    
    /**
     * Move the Snake by one square in the current direction
     */
    public void move() {
        Point head = body.get(0);
        Point newHead = new Point(head.getX() + direction.getX(), head.getY() + direction.getY());
        body.add(0, newHead);
        body.remove(body.size() - 1);
    }
    
    /**
     * Grow the Snake by one segment if the Snake has consumed a Pellet
     */
    public void grow() {
        Point head = body.get(0);
        Point newHead = new Point(head.getX() + direction.getX(), head.getY() + direction.getY());
        body.add(0, newHead);
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
}
