package model;

import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SnakeGame {
    private Board board;
    private Snake snake;
    private ScoreManager scoreManager;
    private GraphicsContext gc;
    private GameOverHandler gameOverHandler;

    private AnimationTimer gameLoop;

    public SnakeGame(int width, int height, GraphicsContext gc) throws Exception {
        this.gc = gc;
        this.snake = new Snake(width / 2, height / 2, Color.GREEN);
        this.board = new Board(width, height, Color.BLACK, gc, snake);
        this.scoreManager = new ScoreManager();
        this.gameOverHandler = new GameOverHandler(scoreManager);

        setupGameLoop();
    }

    private void setupGameLoop() {
        gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;
            private final long interval = 100_000_000; // Nanoseconds (100ms)

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

    public void start() {
        board.initialize();
        gameLoop.start();
    }

    public void stop() {
        gameLoop.stop();
    }

    private void update() {
        snake.move();
        if (board.isCollision()) {
            gameOver();
        } else {
            checkFoodCollision();
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
    

    private void gameOver() {
        stop();
        gc.setFill(Color.RED);
        gc.setFont(Font.font(48));
        gc.fillText("Game Over", 200, 250);
       // gameOverHandler.show();
    }

    private void render() {
        board.update();
    }

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
}
