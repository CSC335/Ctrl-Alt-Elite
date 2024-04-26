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
 * @author Vannela Chatla and Sameeka Maroli
 */
public class SnakeGame {
    private Board board;
    private Snake snake;
    private ScoreManager scoreManager;
    private GraphicsContext gc;
    private boolean isGameOver;
    private boolean isPaused = false;


    private AnimationTimer gameLoop;
    private long interval;

    /**
     * Create a new SnakeGame object with a given board size
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
    
    public void pause() {
        setPaused(true);
        gameLoop.stop();
      }

      public void resume() {
        setPaused(false);
        gameLoop.start();
      }

    public boolean gameOver() {
        isGameOver = true; // Set the game over flag
        stop();

        displayGameOverScreen();
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

    private void checkFoodCollision() {
        for (FoodPellet pellet : board.getFoodPellets()) {
            if (pellet.detectCollision(snake.getHead())) {
                pellet.respawn(board.getWidth(), board.getHeight());
                scoreManager.updateScore(1);
                snake.grow();
            }
        }
    }

    private void render() {
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
        }
    }

    public void displayGameOverScreen() {
        String gameOverText = "Game Over";
        String restartText = "Press 'R' to Restart";

        Font gameOverFont = Font.font("Press Start 2P", FontWeight.BOLD, 48);
        Font restartFont = Font.font("Press Start 2P", FontWeight.BOLD, 24);

        gc.setFill(Color.PURPLE);
        gc.setFont(gameOverFont);

        javafx.scene.text.Text text = new javafx.scene.text.Text(gameOverText);
        text.setFont(gc.getFont());
        double textWidth = text.getBoundsInLocal().getWidth();
        double textHeight = text.getBoundsInLocal().getHeight();

        double x = (board.getWidth() - textWidth) / 2;
        double y = (board.getHeight() / 2) - (textHeight / 2);

        gc.setEffect(new Glow(0.8));
        gc.fillText(gameOverText, x, y);
        gc.setEffect(null);

        gc.setFont(restartFont);
        text = new javafx.scene.text.Text(restartText);
        text.setFont(gc.getFont());
        textWidth = text.getBoundsInLocal().getWidth();
        y += 60;
        x = (board.getWidth() - textWidth) / 2;
        gc.fillText(restartText, x, y);
    }

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
			default:
				break;
            }
        }
    }

    public void restartGame() {
        snake = new Snake(board.getWidth() / 2, board.getHeight() / 2, Color.GREEN);
        board = new Board(board.getWidth(), board.getHeight(), 5, Color.BLACK, gc, snake);  // Assuming 5 pellets
        scoreManager.resetScore();
        isGameOver = false;

        setupGameLoop();
        start();
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public Object getBoard() {
        return this.board;
    }

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
}
