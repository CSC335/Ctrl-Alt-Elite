package tests;

<<<<<<< HEAD
=======
import javafx.embed.swing.JFXPanel;
>>>>>>> bcf1460586638a6d6fca06f1037ef2458194b835
import javafx.scene.paint.Color;
import model.Snake;
import model.Snake.Direction;
import model.Tile;
<<<<<<< HEAD

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
=======
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
>>>>>>> bcf1460586638a6d6fca06f1037ef2458194b835

public class SnakeTest {
	
    @BeforeAll
    public static void setupJavaFXRuntime() {
        new JFXPanel(); 
    }

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

<<<<<<< HEAD
	@Test
	public void testSnakeMove() {
		snake.move();
		assertEquals(1, snake.size());
		assertNotNull(snake.getHead());
	}

	@Test
	public void testSnakeGrow() {
		snake.grow();
		assertEquals(2, snake.size());
		assertNotNull(snake.getHead());
	}

	@Test
	public void testSnakeSetDirection() {
		snake.setDirection(Snake.Direction.LEFT);
		assertNotEquals(Snake.Direction.RIGHT, snake.getDirection());
		assertEquals(Snake.Direction.LEFT, snake.getDirection());
	}
//
//	@Test
//	public void testCannotReverseDirection() {
//		snake.setDirection(Direction.RIGHT);
//		snake.setDirection(Direction.LEFT);
//		assertEquals(Direction.RIGHT, snake.getDirection(), "Snake should not be able to reverse direction.");
//	}
//
//	@Test
//	public void testHeadPositionAfterMove() {
//		snake.setDirection(Direction.RIGHT);
//		snake.move();
//		Tile head = snake.getHead();
//		assertEquals(11, head.getTileX());
//		assertEquals(10, head.getTileY());
//	}
//
//	@Test
//	public void testCollisionWithSelf() {
//		snake.grow();
//		snake.grow();
//		snake.setDirection(Direction.LEFT);
//		snake.move();
//		snake.setDirection(Direction.UP);
//		snake.move();
//		snake.setDirection(Direction.RIGHT);
//		snake.move();
//		assertTrue(snake.hasCollidedWithSelf(), "Snake should detect collision with itself.");
//
//	}
=======
    @Test
    public void testSnakeMove() {
        snake.move();
        assertEquals(1, snake.size());
        assertNotNull(snake.getHead());
    }

    @Test
    public void testSnakeGrow() {
        snake.grow();
        assertEquals(2, snake.size());
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
        assertEquals(11, head.getTileX());
        assertEquals(10, head.getTileY());
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
>>>>>>> bcf1460586638a6d6fca06f1037ef2458194b835
}
