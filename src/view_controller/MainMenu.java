package view_controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.CustomFont;
import model.SnakeAccount;
/*
 * @author Vennela Chatla
 */
public class MainMenu extends VBox {

    private static final int BUTTON_FONT_SIZE = 20;

    private Button startGameButton;
    private Button settingsButton;
    private Label welcomeLabel;
    private Label highScoreLabel; 
    private CustomFont buttonFont;

    public MainMenu(SnakeGUI snakeGUI) {
        buttonFont = new CustomFont(BUTTON_FONT_SIZE);
    }

    

    public void initializeComponents(SnakeGUI snakeGUI) {
        SnakeAccount account = snakeGUI.getLoginPane().getCurrentAccount();
        startGameButton = new Button("Start Game");
        startGameButton.setOnAction(event -> startGame(snakeGUI));
        startGameButton.setFont(buttonFont.getCustomFont());
        startGameButton.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        startGameButton.setTextFill(Color.WHITE);

        settingsButton = new Button("Settings");
        settingsButton.setOnAction(event -> openSettings(snakeGUI));
        settingsButton.setFont(buttonFont.getCustomFont());
        settingsButton.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        settingsButton.setTextFill(Color.WHITE);

        welcomeLabel = new Label("Welcome, " + account.getUsername() + "!");
        welcomeLabel.setFont(buttonFont.getCustomFont());
        welcomeLabel.setTextFill(Color.WHITE);


        highScoreLabel = new Label("Overall High Score: " + account.getHighScore());
        highScoreLabel.setFont(buttonFont.getCustomFont());
        highScoreLabel.setTextFill(Color.WHITE);
    }

    public void layoutComponents() {
        this.getChildren().addAll(welcomeLabel, highScoreLabel, startGameButton, settingsButton); // Include high score label
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setSpacing(20);
    }

    private void startGame(SnakeGUI snakeGUI) {
        snakeGUI.startGame();
    }

    private void openSettings(SnakeGUI snakeGUI) {
        snakeGUI.setSceneRoot(snakeGUI.getSettingsMenu());
    }
}