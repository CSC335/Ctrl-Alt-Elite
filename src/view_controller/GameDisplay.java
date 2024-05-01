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
import javafx.scene.text.TextAlignment;
import model.CustomFont;
import model.SnakeGame;

public class GameDisplay extends VBox {
    private Canvas gameCanvas;
    private GraphicsContext gc;
    private VBox root;
    private SnakeGUI snakeGUI;
    private SnakeGame snakeGame;
    private Label scoreLabel;
    private CustomFont scoreFont;
    
    public GameDisplay(SnakeGUI snakeGUI, int width, int height, SnakeGame snakeGame) {
        gameCanvas = new Canvas(width, height);
        this.gc = gameCanvas.getGraphicsContext2D();
        //snakeGame.setGraphicsContext(gc);
        this.snakeGUI = snakeGUI;
        this.snakeGame = snakeGame;
        scoreLabel = new Label();
        scoreFont = new CustomFont(20);
        
        this.getChildren().addAll(scoreLabel, gameCanvas);
        this.setSpacing(10);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    public GraphicsContext getGraphicsContext() {
        return gc;
    }
    
    public void setSnakeGame(SnakeGame sG) {
        snakeGame = sG;
    }
    
    public void updateScore() {
        gc.setFont(scoreFont.getCustomFont());
        
        scoreLabel.setText("Score: " + snakeGame.getScoreManager().getCurrentScore());
        scoreLabel.setFont(scoreFont.getCustomFont());
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.setTextFill(Color.WHITE);
    }
}