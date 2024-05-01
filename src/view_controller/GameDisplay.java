package view_controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.CustomFont;
import model.SnakeGame;

/**
 * A simple class to display the game and score
 *
 * @author Sameeka Maroli and Brendan Bamberg
 */

public class GameDisplay extends VBox {
    private Canvas gameCanvas;
    private GraphicsContext gc;
    private SnakeGame snakeGame;
    private Label scoreLabel;
    private CustomFont scoreFont;
    
    /**
     * Initializes a new GameDisplay object to display the game
     *
     * @param width An integer representing the width of the window
     * @param height An integer representing the height of the window
     * @param snakeGame An instance of a game of Snake
     */
    public GameDisplay(int width, int height, SnakeGame snakeGame) {
        gameCanvas = new Canvas(width, height);
        this.gc = gameCanvas.getGraphicsContext2D();
        this.snakeGame = snakeGame;
        scoreLabel = new Label();
        scoreFont = new CustomFont(20);
        
        this.getChildren().addAll(scoreLabel, gameCanvas);
        this.setSpacing(10);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    /**
     * Gets the GraphicsContext of the display
     *
     * @return A GraphicsContext object to draw the game
     */
    public GraphicsContext getGraphicsContext() {
        return gc;
    }
    
    /**
     * Gets the instance of a game of Snake
     *
     * @param sG A SnakeGame object representing the game of Snake
     */
    public void setSnakeGame(SnakeGame sG) {
        snakeGame = sG;
    }
    
    /**
     * Updates the label that displays the score
     */
    public void updateScore() {
        gc.setFont(scoreFont.getCustomFont());
        
        scoreLabel.setText("Score: " + snakeGame.getScoreManager().getCurrentScore());
        scoreLabel.setFont(scoreFont.getCustomFont());
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.setTextFill(Color.WHITE);
    }
}