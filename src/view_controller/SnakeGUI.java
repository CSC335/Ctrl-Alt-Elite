package view_controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import model.Board;
import model.SnakeGame;

public class SnakeGUI extends Application {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;
    private static final int TILE_SIZE = 20;
    private static final int ROWS = WINDOW_HEIGHT / TILE_SIZE;
    private static final int COLUMNS = WINDOW_WIDTH / TILE_SIZE;
    
    private Board board;
    private AnimationTimer gameLoop;

    
    private SnakeGame snakeGame;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        snakeGame = new SnakeGame(WINDOW_WIDTH, WINDOW_HEIGHT, gc);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        scene.setOnKeyPressed(event -> snakeGame.handleKeyPress(event.getCode()));

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        snakeGame.start(); // Start the game
    }

    public static void main(String[] args) {
        launch(args);
    }
}
    
// @Override
//     public void start(Stage primaryStage) {
//         StackPane root = new StackPane();
//         Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
//         GraphicsContext gc = canvas.getGraphicsContext2D();
//         root.getChildren().add(canvas);

//         board = new Board();
//         board.initialize();

//         Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
//         scene.setOnKeyPressed(event -> {
//             Snake snake = board.getSnake();
//             switch (event.getCode()) {
//                 case UP:
//                     snake.setDirection(Direction.UP);
//                     break;
//                 case DOWN:
//                     snake.setDirection(Direction.DOWN);
//                     break;
//                 case LEFT:
//                     snake.setDirection(Direction.LEFT);
//                     break;
//                 case RIGHT:
//                     snake.setDirection(Direction.RIGHT);
//                     break;
//             }
//         });

//         primaryStage.setTitle("Snake Game");
//         primaryStage.setScene(scene);
//         primaryStage.show();

//         gameLoop = new AnimationTimer() {
//             @Override
//             public void handle(long now) {
//                 board.update();
//                 gc.setFill(board.getBackgroundColor());
//                 gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//                 board.getSnake().render(gc);
//                 for (FoodPellet pellet : board.getFoodPellets()) {
//                     pellet.draw();
//                 }
//             }
//         };
//         gameLoop.start();
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
//     public class Snake {
//         private List<Point2D> body;
//         private Color color;
//         private int direction;
//         private final int SIZE = 20;

//         // Existing constructor and other methods

//         public void move() {
//             if (body.isEmpty()) {
//                 return;
//             }

//             Point2D head = body.get(0);
//             Point2D newHead = head.add(Direction.getX(direction) * SIZE, Direction.getY(direction) * SIZE);

//             // Move the body
//             for (int i = body.size() - 1; i > 0; i--) {
//                 body.set(i, body.get(i - 1));
//             }
//             body.set(0, newHead);
//         }

//         public void grow() {
//             Point2D tail = body.get(body.size() - 1);
//             Point2D newTail = tail.add(Direction.getX(direction) * SIZE, Direction.getY(direction) * SIZE);
//             body.add(newTail); // Add new segment to the body
//         }


//         public void updatePosition() {
//             // ??
//         }

//         public void render(GraphicsContext gc) {
//             gc.setFill(color);
//             for (Point2D segment : body) {
//                 gc.fillRect(segment.getX(), segment.getY(), SIZE, SIZE);
//             }
//         }
//     }



    
//    @Override
//  public void start(Stage stage) throws Exception {
//      
//      VBox gameOverHandler = new GameOverHandler(new ScoreManager());
//      Scene scene = new Scene(gameOverHandler, 400, 400);
//      stage.setScene(scene);
//      stage.show();
//  }
<<<<<<< HEAD
=======

  
>>>>>>> 152652fb19e40a67e97ba2c701e7415d207be103
