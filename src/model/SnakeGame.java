package model;

import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.Glow;


/**
 * Represents the game logic of a game of Snake
 *
 * @author Vannela Chatla and Sameeka Maroli
 */

public class SnakeGame {
    private Board board;
    private Snake snake;
    private ScoreManager scoreManager;
    private GraphicsContext gc;
    private boolean isGameOver;
    
    private AnimationTimer gameLoop;
    private long interval;
    
    /**
     * Create a new SnakeGame object with a given board size
     *
     * @param width  An integer that represents the width of the game board
     * @param height An integer that represents the height of the game board
     * @param gc     A GraphicsContext used to draw the actors of the game
     */
    public SnakeGame(int width, int height, long interval, int numPellets, GraphicsContext gc) {
        this.gc = gc;
        this.snake = new Snake(width / 2, height / 2, Color.GREEN);
        this.board = new Board(width, height, numPellets, Color.BLACK, gc, snake);
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
    
    public void stop() {
        gameLoop.stop();
    }
    
    public boolean gameOver() {
        isGameOver = true; // Set the game over flag
        stop();
        
        return true;
    }
    
    private void update() {
        if (!isGameOver) {
            if (board.isCollision()) {
                gameOver();
                return;
            }
            checkFoodCollision();
            snake.move();
        }
    }
    
    /**
     * Check if the Snake has eaten a FoodPellet and if so, grow the snake and
     * respawn the FoodPellet
     */
    private void checkFoodCollision() {
        for (FoodPellet pellet : board.getFoodPellets()) {
            if (snake.isPoweredUp()) {
                for (int i = -2; i <= 2; i++) {
                    for (int j = -2; j <= 2; j++) {
                        Tile currTile = new Tile(snake.getHead().getX() + i * board.TILE_SIZE,
                                snake.getHead().getY() + j * board.TILE_SIZE);
                        if (pellet.detectCollision(currTile)) {
                            pellet.respawn(board.getWidth(), board.getHeight(), false, snake);
                            scoreManager.updateScore(1);
                            snake.grow();
                        }
                    }
                }
            } else {
                if (pellet.detectCollision(snake.getHead())) {
                    if (pellet.isPowerUp()) {
                        snake.setPoweredUp(true);
                    }
                    
                    pellet.respawn(board.getWidth(), board.getHeight(), !snake.isPoweredUp(), snake);
                    scoreManager.updateScore(1);
                    snake.grow();
                }
            }
        }
    }
    
    /**
     * Draw the entire board
     */
    private void render() { //To Do: Sameeka
        if (!isGameOver) {
            board.update();
            
            String scoreText = "Score: " + scoreManager.getCurrentScore();
            double padding = 10;
            Font scoreFont = Font.font("Courier New", FontWeight.EXTRA_BOLD, 36);
            gc.setFont(scoreFont);
            
            javafx.scene.text.Text text = new javafx.scene.text.Text(scoreText);
            text.setFont(scoreFont);
            double textWidth = text.getBoundsInLocal().getWidth();
            double textY = padding + 36;
            double textX = board.getWidth() - textWidth - padding;
            
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2);
            gc.strokeText(scoreText, textX, textY);
            gc.setFill(Color.RED);
            gc.fillText(scoreText, textX, textY);
        } else {
            displayGameOverScreen();
        }
    }
    
    /**
     * Displays the game over screen. //To Do: Sameeka
     */
    public void displayGameOverScreen() {
        String gameOverText = "Game Over";
        String restartText = "Press 'R' to Restart";
        
        CustomFont gameOverFont = new CustomFont(48);
        CustomFont restartFont = new CustomFont(24);
        
        gc.setFill(Color.PURPLE);
        gc.setFont(gameOverFont.getCustomFont());
        
        javafx.scene.text.Text text = new javafx.scene.text.Text(gameOverText);
        text.setFont(gc.getFont());
        double textWidth = text.getBoundsInLocal().getWidth();
        double textHeight = text.getBoundsInLocal().getHeight();
        
        double x = (board.getWidth() - textWidth) / 2;
        double y = (board.getHeight() / 2) - (textHeight / 2);
        
        gc.setEffect(new Glow(0.8));
        gc.fillText(gameOverText, x, y);
        gc.setEffect(null);
        
        gc.setFont(restartFont.getCustomFont());
        text = new javafx.scene.text.Text(restartText);
        text.setFont(gc.getFont());
        textWidth = text.getBoundsInLocal().getWidth();
        y += 60;
        x = (board.getWidth() - textWidth) / 2;
        gc.fillText(restartText, x, y);
    }
    
    /**
     * Change the direction of the Snake based on user inputted key presses
     *
     * @param keyCode A KeyCode representing the user's keyboard input
     */
    @SuppressWarnings("incomplete-switch")
    public void handleKeyPress(KeyCode keyCode) {
        if (isGameOver) {
            if (keyCode == KeyCode.R) {
                restartGame();
            }
        } else {
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
    }
    
    /**
     * Get the ScoreManager of the game
     *
     * @return A ScoreManager that contains the current and high scores
     */
    public ScoreManager getScoreManager() {
        return scoreManager;
    }
    
    /**
     * Get the snake of the game
     *
     * @return A Snake object that represents the player
     */
    public Snake getSnake() {
        return this.snake;
    }
    
    /**
     * Get the board of the game
     *
     * @return A Board object that represents the NxN tile board of the game
     */
    public Object getBoard() {
        return this.board;
    }
    
    public void restartGame() {
        snake = new Snake(board.getWidth() / 2, board.getHeight() / 2, Color.GREEN);
        board = new Board(board.getWidth(), board.getHeight(), 5, Color.BLACK, gc, snake);  // Assuming 5 pellets
        scoreManager.resetScore();
        isGameOver = false;
        
        // Restart the game loop
        setupGameLoop();
        start();
    }
    
    
}
