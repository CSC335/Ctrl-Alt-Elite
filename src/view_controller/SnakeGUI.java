package view_controller;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import model.SnakeGame;

/**
 * A JavaFX GUI that represents a game of Snake
 *
 * @author Sameeka Maroli
 */

public class SnakeGUI extends Application {
    
    private static final int TILE_SIZE = 20;
    private int WINDOW_WIDTH = 600;
    private int WINDOW_HEIGHT = 600;
    private int ROWS = WINDOW_HEIGHT / TILE_SIZE;
    private int COLUMNS = WINDOW_WIDTH / TILE_SIZE;
    
    private SnakeGame snakeGame;
    private Scene currentScene;
    
    private SettingsMenu settingsMenu;
    
    /**
     * Initialize the game and display it to a window
     *
     * @param primaryStage A Stage used to display the elements of the game
     */
    @Override
    public void start(Stage primaryStage){
        // Display main menu, start game if that option is selected, show menus, etc.
        settingsMenu = new SettingsMenu(this, primaryStage);
        currentScene = new Scene(settingsMenu, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(currentScene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void startGame(Stage primaryStage) {
        primaryStage.close();
        
        // Resize the stage and scene to show the full game
        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        snakeGame = new SnakeGame(WINDOW_WIDTH, WINDOW_HEIGHT, settingsMenu.getCurrentInterval(), settingsMenu.getNumPellets(), gc);
        
        // Create root node to hold the Canvas
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        currentScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        currentScene.setOnKeyPressed(event -> snakeGame.handleKeyPress(event.getCode()));
        
        // Re-initialize the Stage
        primaryStage = new Stage();
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(currentScene);
        primaryStage.setResizable(false);
        
        primaryStage.show();
        snakeGame.start();
    }
    
    // Methods to check the state of SnakeGame and display corresponding menus
    
    
    public void setWindowSize(int tileWidth, int tileHeight) {
        WINDOW_WIDTH = tileWidth * TILE_SIZE;
        WINDOW_HEIGHT = tileHeight * TILE_SIZE;
    }
}