package view_controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import model.Board;
import model.Snake;
import model.FoodPellet;
import model.Snake.Direction;

import javafx.geometry.Point2D;

public class SnakeGUI extends Application {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;
    private static final int TILE_SIZE = 20;
    private static final int ROWS = WINDOW_HEIGHT / TILE_SIZE;
    private static final int COLUMNS = WINDOW_WIDTH / TILE_SIZE;
    
    private Board board;
    private AnimationTimer gameLoop;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        board = new Board();
        board.initialize();

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        scene.setOnKeyPressed(event -> {
            Snake snake = board.getSnake();
            switch (event.getCode()) {
                case UP:
                    snake.setDirection(Direction.UP);
                    break;
                case DOWN:
                    snake.setDirection(Direction.DOWN);
                    break;
                case LEFT:
                    snake.setDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    snake.setDirection(Direction.RIGHT);
                    break;
            }
        });

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                board.update();
                gc.setFill(board.getBackgroundColor());
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                board.getSnake().render(gc);
                for (FoodPellet pellet : board.getFoodPellets()) {
                    pellet.draw();
                }
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public class Snake {
        private List<Point2D> body;
        private Color color;
        private int direction;
        private final int SIZE = 20;

        // Existing constructor and other methods

        public void move() {
            if (body.isEmpty()) {
                return;
            }

            Point2D head = body.get(0);
            Point2D newHead = head.add(Direction.getX(direction) * SIZE, Direction.getY(direction) * SIZE);

            // Move the body
            for (int i = body.size() - 1; i > 0; i--) {
                body.set(i, body.get(i - 1));
            }
            body.set(0, newHead);
        }

        public void grow() {
            Point2D tail = body.get(body.size() - 1);
            Point2D newTail = tail.add(Direction.getX(direction) * SIZE, Direction.getY(direction) * SIZE);
            body.add(newTail); // Add new segment to the body
        }


        public void updatePosition() {
            // ??
        }

        public void render(GraphicsContext gc) {
            gc.setFill(color);
            for (Point2D segment : body) {
                gc.fillRect(segment.getX(), segment.getY(), SIZE, SIZE);
            }
        }
    }

    public class FoodPellet {
        private double x;
        private double y;
        private final int SIZE = 20;
        private Color color;
        private GraphicsContext gc;


        public void spawn(int boardWidth, int boardHeight) {
            Random random = new Random();
            x = random.nextInt(boardWidth / SIZE) * SIZE;
            y = random.nextInt(boardHeight / SIZE) * SIZE;
            draw();
        }

        public void draw() {
            gc.setFill(color);
            gc.fillOval(x, y, SIZE, SIZE);
        }

        public boolean detectCollision(Point2D head) {
            return head.getX() == x && head.getY() == y;
        }

    }

    public class Board {
        private int width;
        private int height;
        private Color backgroundColor;
        private Snake snake;
        private List<FoodPellet> foodPellets;
        private GraphicsContext gc;


        public void initialize() {
            snake = new Snake();
            foodPellets = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                spawnFoodPellet();
            }
        }

        public void update() {
            snake.move();
            checkCollisionsWithFood();
            checkCollisionsWithBounds();
        }

        private void checkCollisionsWithFood() {
            Point2D head = snake.getBody().get(0);
            FoodPellet toRemove = null;
            for (FoodPellet pellet : foodPellets) {
                if (pellet.detectCollision(head)) {
                    snake.grow();
                    toRemove = pellet;
                    break;
                }
            }
            if (toRemove != null) {
                foodPellets.remove(toRemove);
                spawnFoodPellet();
            }
        }

        private void checkCollisionsWithBounds() {
            Point2D head = snake.getBody().get(0);
            if (head.getX() < 0 || head.getY() < 0 || head.getX() >= width * SIZE || head.getY() >= height * SIZE) {
                // Handle collision with the boundaries
            }
        }

        // Existing getters and setters
    }
}


    
//    @Override
//  public void start(Stage stage) throws Exception {
//      
//      VBox gameOverHandler = new GameOverHandler(new ScoreManager());
//      Scene scene = new Scene(gameOverHandler, 400, 400);
//      stage.setScene(scene);
//      stage.show();
//  }

  