package view_controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.CustomFont;
import model.SnakeGame;

public class PauseMenu extends VBox {

    private Label pauseLabel, confirmLabel;
    private Button continueGame, quitToMenu, confirm, back;
    
    private SnakeGame theGame;
    private SnakeGUI snakeGUI;
    private CustomFont labelFont, labelFontTwo, buttonFont;
    private Background background;
    
    public PauseMenu(SnakeGUI snakeGUI, SnakeGame theGame) {
        labelFont = new CustomFont(40);
        labelFontTwo = new CustomFont(24);
        buttonFont = new CustomFont(12);
        background = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
        this.theGame = theGame;
        this.snakeGUI = snakeGUI;
        
        initializeComponents();
        layoutGUI();
    }
    
    private void initializeComponents() {
        pauseLabel = new Label("Pause");
        confirmLabel = new Label("Are you sure?");
        setFont(pauseLabel);
        confirmLabel.setFont(labelFontTwo.getCustomFont());
        confirmLabel.setTextFill(Color.WHITE);
        confirmLabel.setBackground(background);
        
        continueGame = new Button("Continue");
        quitToMenu = new Button("Main Menu");
        confirm = new Button("Yes");
        back = new Button("No");
        setFont(continueGame);
        setFont(quitToMenu);
        setFont(confirm);
        setFont(back);
        
        continueGame.setOnAction(event-> {
            snakeGUI.continueGame();
        });
        
        quitToMenu.setOnAction(event -> {
            // reset the SnakeGUI and game, maybe pull up an alert beforehand
            switchLayout(1);
        });

        confirm.setOnAction(event -> {
            snakeGUI.setSceneRoot(snakeGUI.getMainMenu());
            switchLayout(0);
        });

        back.setOnAction(event -> {
            switchLayout(0);
        });
    }
    
    private void layoutGUI() {
        this.getChildren().addAll(pauseLabel, spacingButton(), continueGame, quitToMenu);
        this.setAlignment(Pos.CENTER);
        this.setBackground(background);
        this.setSpacing(20);
    }

    private void switchLayout(int layout) {
        this.getChildren().clear();
        if (layout == 0) {
            this.getChildren().addAll(pauseLabel, spacingButton(), continueGame, quitToMenu);
            this.setAlignment(Pos.CENTER);
            this.setBackground(background);
            this.setSpacing(20);
        } else if (layout == 1) {
            HBox buttonBox = new HBox();
            buttonBox.getChildren().addAll(confirm, spacingButton(), back);
            buttonBox.setAlignment(Pos.CENTER);
            buttonBox.setSpacing(20);

            this.getChildren().addAll(confirmLabel, buttonBox);
            this.setAlignment(Pos.CENTER);
            this.setBackground(background);
            this.setSpacing(20);
        }
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
