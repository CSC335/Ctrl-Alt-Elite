package model;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameOverHandler extends VBox {
    
    private ScoreManager scoreManager;
    private Label gameOverLabel, scoreLabel, highScoreLabel;
    private Button restart, quit;
    
    public GameOverHandler(ScoreManager scoreManager) throws Exception {
        this.scoreManager = scoreManager;
        intializeComponents();
        layoutGUI();
    }
    
    private void intializeComponents() throws Exception {
        gameOverLabel = new Label("GAME OVER");
        scoreLabel = new Label("Score: " + scoreManager.getCurrentScore());
        highScoreLabel = new Label("High Score: " + scoreManager.getHighScore());
        restart = new Button("Restart");
        quit = new Button("Quit");
        
        FileInputStream fontInputStream;
        try {
            fontInputStream = new FileInputStream("resources/Emulogic-zrEw.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        Font gameOverFont = Font.loadFont(fontInputStream, 36);
        
        try {
            fontInputStream = new FileInputStream("resources/Emulogic-zrEw.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        Font labelFont = Font.loadFont(fontInputStream, 15);
        
        gameOverLabel.setFont(gameOverFont);
        scoreLabel.setFont(labelFont);
        highScoreLabel.setFont(labelFont);
        restart.setFont(labelFont);
        quit.setFont(labelFont);
        
        restart.setOnAction(event -> {
        
        });
        quit.setOnAction(event -> {
        
        });
        
        fontInputStream.close();
    }
    
    private void layoutGUI() {
        this.getChildren().addAll(gameOverLabel, scoreLabel, highScoreLabel, restart, quit);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        
        gameOverLabel.setAlignment(Pos.CENTER);
        scoreLabel.setAlignment(Pos.CENTER);
        highScoreLabel.setAlignment(Pos.CENTER);
        restart.setAlignment(Pos.CENTER);
        quit.setAlignment(Pos.CENTER);
    }
//    public void show() {
//        Scene scene = new Scene(this, 400, 300);
//        stage.setScene(scene);
//        stage.show();
//    }

}