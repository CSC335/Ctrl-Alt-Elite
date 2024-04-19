package model;

import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Represents the game logic of a game of Snake
 *
 * @author Vannela Chatla
 */

public class SnakeGame {
    private Board board;
    private Snake snake;
    private ScoreManager scoreManager;
    private GraphicsContext gc;
    
    private AnimationTimer gameLoop;
    private long interval;
    
    /**
     * Create a new SnakeGame object with a given board size
     *
     * @param width  An integer that represents the width of the game board
     * @param height An integer that represents the height of the game board
     * @param gc     A GraphicsContext used to draw the actors of the game
     */
    public SnakeGame(int width, int height, long interval, GraphicsContext gc) {
        this.gc = gc;
        this.snake = new Snake(width / 2, height / 2, Color.GREEN);
        this.board = new Board(width, height, Color.BLACK, gc, snake);
        this.scoreManager = new ScoreManager();
        this.interval = interval; // Nanoseconds (100ms)
        
        setupGameLoop();
    }
    
    /**
     * Set up the game timer to update the actors and draw the game board
     */
    private void setupGameLoop() {
        gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;
            
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= interval) {
                    update();
                    render();
                    lastUpdate = now;
                }
            }
        };
    }
    
    /**
     * Start the game loop
     */
    public void start() {
        gameLoop.start();
    }
    
    /**
     * Stop the game loop
     */
    public void stop() {
        gameLoop.stop();
    }
    
    /**
     * Update the location of the Snake and check for any special events
     */
    private void update() {
        if (board.isCollision()) {
            gameOver();
            return;
        } else {
            checkFoodCollision();
        }
        snake.move();
    }
    
    /**
     * Check if the Snake has eaten a FoodPellet and if so, grow the snake and respawn the FoodPellet
     */
    private void checkFoodCollision() {
        for (FoodPellet pellet : board.getFoodPellets()) {
            if (pellet.detectCollision(snake.getHead())) {
                pellet.respawn(board.getWidth(), board.getHeight());
                scoreManager.updateScore(1);
                snake.grow();
            }
        }
    }
    
    /**
     * Check if the Snake has collided with itself or the walls of the board and activate a game over
     */
    private void gameOver() {
        stop();
        gc.setFill(Color.RED); // Check to see why the text doesn't display
        gc.setFont(Font.font(48));
        gc.fillText("Game Over", 200, 250);
        // gameOverHandler.show();
    }
    
//    private void gameOver() {
//        stop();
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());  // Clear the canvas for testing
//        gc.setFill(Color.RED);
//        gc.setFont(Font.font(48));
//        System.out.println("Drawing Game Over text");  // Debugging output
//        gc.fillText("Game Over", 200, 250);
//        gc.setStroke(Color.WHITE);  // Adding a stroke to see if the fill is the issue
//        gc.strokeText("Game Over", 200, 250);
//    }
//
//    
    //
    /**
     * Draw the entire board
     */
    private void render() {
        board.update();
    }
    
    /**
     * Change the direction of the Snake based on user inputted key presses
     *
     * @param keyCode A KeyCode representing the user's keyboard input
     */
    @SuppressWarnings("incomplete-switch")
    public void handleKeyPress(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                snake.setDirection(Snake.Direction.UP);
                break;
            case DOWN:
                snake.setDirection(Snake.Direction.DOWN);
                break;
            case LEFT:
                snake.setDirection(Snake.Direction.LEFT);
                break;
            case RIGHT:
                snake.setDirection(Snake.Direction.RIGHT);
                break;
        }
    }
    
    /**
     * Get the ScoreManager of the game
     *
     * @return A ScoreManager that contains the current and high scores
     */
    public ScoreManager getScoreManager() {
        return scoreManager;
    }
    
    public Snake getSnake() {
        return this.snake;
    }

	public Object getBoard() {
		return this.board;
	}
}