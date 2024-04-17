package tests;


import javafx.scene.paint.Color;
import model.Snake;
import model.Snake.Direction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SnakeTest {

    private Snake snake;

    @BeforeEach
    public void setUp() {
        snake = new Snake(10, 10, Color.GREEN);
    }

    @Test
    public void testSnakeCreation() {
        assertNotNull(snake);
        assertEquals(1, snake.size());
        assertNotNull(snake.getHead());
    }

    @Test
    public void testSnakeMove() {
        snake.move();
        assertEquals(1, snake.size()); // Snake should not grow after moving
        assertNotNull(snake.getHead());
    }

    @Test
    public void testSnakeGrow() {
        snake.grow();
        assertEquals(2, snake.size()); // Snake should grow after calling grow()
        assertNotNull(snake.getHead());
    }

    @Test
    public void testSnakeSetDirection() {
        snake.setDirection(Snake.Direction.LEFT);
        assertEquals(Snake.Direction.LEFT, snake.getDirection());
    }

    @Test
    public void testCannotReverseDirection() {
        snake.setDirection(Direction.RIGHT);
        snake.setDirection(Direction.LEFT);
        assertEquals(Direction.RIGHT, snake.getDirection(), "Snake should not be able to reverse direction.");
    }
    
    @Test
    public void testHeadPositionAfterMove() {
        snake.setDirection(Direction.RIGHT);
        snake.move();
        Tile head = snake.getHead();
        assertEquals(11, head.getTileX(), "Head X should increase by 1 when moving right.");
        assertEquals(10, head.getTileY(), "Head Y should not change when moving horizontally.");
    }
    
    @Test
    public void testCollisionWithSelf() {
        snake.grow();
        snake.grow();
        snake.setDirection(Direction.LEFT);
        snake.move();
        snake.setDirection(Direction.UP);
        snake.move();
        snake.setDirection(Direction.RIGHT);
        snake.move();
        assertTrue(snake.hasCollidedWithSelf(), "Snake should detect collision with itself.");
    }

}
