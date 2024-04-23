package view_controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CustomFont;
import model.SnakeGame;

public class PauseMenu extends VBox {

    private Label pause;
    private Button continueGame, quitToMenu;
    
    private SnakeGame theGame;
    private SnakeGUI snakeGUI;
    private CustomFont labelFont, buttonFont;
    private Background background;
    
    public PauseMenu(SnakeGUI snakeGUI, SnakeGame theGame) {
        labelFont = new CustomFont(40);
        buttonFont = new CustomFont(12);
        background = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
        this.theGame = theGame;
        this.snakeGUI = snakeGUI;
        
        initializeComponents();
        layoutGUI();
    }
    
    private void initializeComponents() {
        pause = new Label("Pause");
        setFont(pause);
        
        continueGame = new Button("Continue");
        quitToMenu = new Button("Main Menu");
        setFont(continueGame);
        setFont(quitToMenu);
        
        continueGame.setOnAction(event-> {
            Stage currStage = (Stage) getScene().getWindow();
            
            snakeGUI.continueGame(currStage);
        });
        
        quitToMenu.setOnAction(event -> {
            // reset the SnakeGUI and game, maybe pull up an alert beforehand
        });
    }
    
    private void layoutGUI() {
        this.getChildren().addAll(pause, spacingButton(), continueGame, quitToMenu);
        this.setAlignment(Pos.CENTER);
        this.setBackground(background);
        this.setSpacing(20);
    }
    
    private Button spacingButton() {
        Button spacingButton = new Button();
        setFont(spacingButton);
        spacingButton.setMinWidth(60);
        return spacingButton;
    }
    
    private void setFont(Label label) {
        label.setFont(labelFont.getCustomFont());
        label.setTextFill(Color.WHITE);
        label.setBackground(background);
    }
    
    private void setFont(Button button) {
        button.setFont(buttonFont.getCustomFont());
        button.setTextFill(Color.WHITE);
        button.setBackground(background);
    }
    
}
