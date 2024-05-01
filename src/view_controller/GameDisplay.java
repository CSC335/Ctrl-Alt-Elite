package view_controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.CustomFont;
import model.SnakeGame;

public class GameDisplay extends VBox {
    private Canvas gameCanvas;
    private GraphicsContext gc;
    private VBox root;
    private SnakeGUI snakeGUI;
    private SnakeGame snakeGame;
    private Text scoreText;
    private CustomFont scoreFont;
    
    public GameDisplay(SnakeGUI snakeGUI, int width, int height, SnakeGame snakeGame) {
        gameCanvas = new Canvas(width, height);
        this.gc = gameCanvas.getGraphicsContext2D();
        //snakeGame.setGraphicsContext(gc);
        this.snakeGUI = snakeGUI;
        this.snakeGame = snakeGame;
        scoreText = new Text();
        scoreFont = new CustomFont(36);
        
        this.getChildren().addAll(gameCanvas);
        this.setSpacing(10);
        //this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    public GraphicsContext getGraphicsContext() {
        return gc;
    }
    
    public void setSnakeGame(SnakeGame sG) {
        snakeGame = sG;
    }
    
    public void updateScore() {
        gc.setFont(scoreFont.getCustomFont());
        
        scoreText.setText("Score: " + snakeGame.getScoreManager().getCurrentScore());
        scoreText.setFont(scoreFont.getCustomFont());
        scoreText.setTextAlignment(TextAlignment.CENTER);
        scoreText.setStyle("-fx-text-fill: white;");
    }
}