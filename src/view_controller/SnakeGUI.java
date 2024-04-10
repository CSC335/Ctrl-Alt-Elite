package view_controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import model.SnakeGame;

public class SnakeGUI extends Application {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;
    private static final int TILE_SIZE = 20;
    private static final int ROWS = WINDOW_HEIGHT / TILE_SIZE;
    private static final int COLUMNS = WINDOW_WIDTH / TILE_SIZE;
    
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
