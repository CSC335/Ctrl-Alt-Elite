package tests;

/**
 
 * @author Vannela Chatla
 */

import javafx.embed.swing.JFXPanel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Snake;
import model.SnakeGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeGameTest {

    private SnakeGame snakeGame;
    private GraphicsContext gc;
    Canvas canvas = new Canvas(800, 600);

    @BeforeEach
    public void setUp() {
        new JFXPanel();

        gc = canvas.getGraphicsContext2D();

        snakeGame = new SnakeGame(800, 600, gc);
    }

    @Test
    public void testSnakeGameInitialization() {
<<<<<<< HEAD

        assertNotNull(snakeGame.getBoard());
        assertNotNull(snakeGame.getSnake());
        assertNotNull(snakeGame.getScoreManager());

//        assertNotNull(snakeGame.board);
//        assertNotNull(snakeGame.snake);
//        assertNotNull(snakeGame.scoreManager);

=======
        assertNotNull(snakeGame.getBoard());
        assertNotNull(snakeGame.getSnake());
        assertNotNull(snakeGame.getScoreManager());
<<<<<<< HEAD
=======
//        assertNotNull(snakeGame.board);
//        assertNotNull(snakeGame.snake);
//        assertNotNull(snakeGame.scoreManager);
>>>>>>> b95a3a72c16d54b6129474dc64335610911e381a
>>>>>>> d0fe5c0ed649a2391f2c6d52f194d32c151cfb18
    }

    @Test
    public void testHandleKeyPress() {
        // Test handling key presses
<<<<<<< HEAD

=======
>>>>>>> d0fe5c0ed649a2391f2c6d52f194d32c151cfb18
        snakeGame.handleKeyPress(KeyCode.UP);
        assertEquals(Snake.Direction.UP, snakeGame.getSnake().getDirection());

        snakeGame.handleKeyPress(KeyCode.DOWN);
       assertNotEquals(Snake.Direction.DOWN, snakeGame.getSnake().getDirection());

        snakeGame.handleKeyPress(KeyCode.LEFT);
        assertEquals(Snake.Direction.LEFT, snakeGame.getSnake().getDirection());

        snakeGame.handleKeyPress(KeyCode.RIGHT);
<<<<<<< HEAD
        assertEquals(Snake.Direction.RIGHT, snakeGame.getSnake().getDirection());

=======
<<<<<<< HEAD
        assertNotEquals(Snake.Direction.RIGHT, snakeGame.getSnake().getDirection());
=======
        assertEquals(Snake.Direction.RIGHT, snakeGame.getSnake().getDirection());
>>>>>>> d0fe5c0ed649a2391f2c6d52f194d32c151cfb18
//        snakeGame.handleKeyPress(KeyCode.UP);
//        assertEquals(Snake.Direction.UP, snakeGame.snake.getDirection());
//
//        snakeGame.handleKeyPress(KeyCode.DOWN);
//       assertEquals(Snake.Direction.DOWN, snakeGame.snake.getDirection());
//
//        snakeGame.handleKeyPress(KeyCode.LEFT);
//        assertEquals(Snake.Direction.LEFT, snakeGame.snake.getDirection());
//
//        snakeGame.handleKeyPress(KeyCode.RIGHT);
//        assertEquals(Snake.Direction.RIGHT, snakeGame.snake.getDirection());
<<<<<<< HEAD
=======
>>>>>>> b95a3a72c16d54b6129474dc64335610911e381a
>>>>>>> d0fe5c0ed649a2391f2c6d52f194d32c151cfb18
    }


}

 
