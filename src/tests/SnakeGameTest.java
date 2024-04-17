package tests;

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
=======
//        assertNotNull(snakeGame.board);
//        assertNotNull(snakeGame.snake);
//        assertNotNull(snakeGame.scoreManager);
>>>>>>> bcf1460586638a6d6fca06f1037ef2458194b835
    }

    @Test
    public void testHandleKeyPress() {
        // Test handling key presses
<<<<<<< HEAD
        snakeGame.handleKeyPress(KeyCode.UP);
        assertEquals(Snake.Direction.UP, snakeGame.getSnake().getDirection());

        snakeGame.handleKeyPress(KeyCode.DOWN);
       assertEquals(Snake.Direction.DOWN, snakeGame.getSnake().getDirection());

        snakeGame.handleKeyPress(KeyCode.LEFT);
        assertEquals(Snake.Direction.LEFT, snakeGame.getSnake().getDirection());

        snakeGame.handleKeyPress(KeyCode.RIGHT);
        assertEquals(Snake.Direction.RIGHT, snakeGame.getSnake().getDirection());
=======
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
>>>>>>> bcf1460586638a6d6fca06f1037ef2458194b835
    }


}

 
