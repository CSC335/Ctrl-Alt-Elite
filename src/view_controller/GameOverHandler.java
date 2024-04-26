package view_controller;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A Scene that shows the Game Over state of a game of Snake
 * @author Brendan Bamberg
 */

public class GameOverHandler extends VBox {
    private SnakeGUI snakeGUI;
    private Scene gameOverScene;
    private Label gameOverLabel, scoreLabel, highScoreLabel;
    private Button restart, quit;

    public GameOverHandler(SnakeGUI snakeGUI, Stage stage) {
        this.snakeGUI = snakeGUI;
        initializeComponents();
        layoutGUI();
        gameOverScene = new Scene(this, 300, 200); 
        stage.setScene(gameOverScene);
        stage.show();
    }

    private void initializeComponents() {
        gameOverLabel = new Label("GAME OVER");
        scoreLabel = new Label("Score: " + snakeGUI.getSnakeGame().getScoreManager().getCurrentScore());
        highScoreLabel = new Label("High Score: " + snakeGUI.getSnakeGame().getScoreManager().getHighScore());
        restart = new Button("Restart");
        quit = new Button("Quit");

        Font gameOverFont = loadFont("resources/Emulogic-zrEw.ttf", 36);
        Font labelFont = loadFont("resources/Emulogic-zrEw.ttf", 15);

        gameOverLabel.setFont(gameOverFont);
        scoreLabel.setFont(labelFont);
        highScoreLabel.setFont(labelFont);
        restart.setFont(labelFont);
        quit.setFont(labelFont);

        restart.setOnAction(event -> snakeGUI.startGame()); // Assuming startGame resets and starts the game
        quit.setOnAction(event -> System.exit(0));

        this.getChildren().addAll(gameOverLabel, scoreLabel, highScoreLabel, restart, quit);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
    }

    private Font loadFont(String path, double size) {
        try (FileInputStream fontInputStream = new FileInputStream(path)) {
            return Font.loadFont(fontInputStream, size);
        } catch (IOException e) {
            System.err.println("Failed to load font: " + path);
            return Font.font("System", size); // Default to a system font on error
        }
    }

    private void layoutGUI() {
        gameOverLabel.setAlignment(Pos.CENTER);
        scoreLabel.setAlignment(Pos.CENTER);
        highScoreLabel.setAlignment(Pos.CENTER);
        restart.setAlignment(Pos.CENTER);
        quit.setAlignment(Pos.CENTER);
    }
}
