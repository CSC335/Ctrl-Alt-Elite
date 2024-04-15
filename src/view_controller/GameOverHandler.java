package view_controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.ScoreManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A Scene that shows the Game Over state of a game of Snake
 *
 * @author Brendan Bamberg
 */

public class GameOverHandler extends Scene {
    
    private VBox elements;
    private ScoreManager scoreManager;
    private Label gameOverLabel, scoreLabel, highScoreLabel;
    private Button restart, quit;
    
    /**
     * Creates a new GameOverHandler object
     *
     * @param scoreManager A ScoreManager object that keeps track of the player's score
     * @throws Exception throws an exception if a font file cannot be found
     */
    public GameOverHandler(ScoreManager scoreManager) throws Exception {
        super(new Button(), 600, 600);
        this.scoreManager = scoreManager;
        intializeComponents();
        layoutGUI();
    }
    
    /**
     * Initializes all components of the Scene with custom fonts
     *
     * @throws Exception throws an exception if a font file cannot be found
     */
    private void intializeComponents() throws Exception {
        elements = new VBox();
        gameOverLabel = new Label("GAME OVER");
        scoreLabel = new Label("Score: " + scoreManager.getCurrentScore());
        highScoreLabel = new Label("High Score: " + scoreManager.getHighScore());
        restart = new Button("Restart");
        quit = new Button("Quit");
        
        // Create a new custom font for the Game Over text
        FileInputStream fontInputStream;
        try {
            fontInputStream = new FileInputStream("resources/Emulogic-zrEw.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font gameOverFont = Font.loadFont(fontInputStream, 36);
        
        // Create a new custom font for the other text
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
        this.setRoot(elements);
    }
    
    /**
     * Creates the layout of the Scene
     */
    private void layoutGUI() {
        elements.getChildren().addAll(gameOverLabel, scoreLabel, highScoreLabel, restart, quit);
        elements.setAlignment(Pos.CENTER);
        elements.setSpacing(20);
        
        gameOverLabel.setAlignment(Pos.CENTER);
        scoreLabel.setAlignment(Pos.CENTER);
        highScoreLabel.setAlignment(Pos.CENTER);
        restart.setAlignment(Pos.CENTER);
        quit.setAlignment(Pos.CENTER);
    }
}