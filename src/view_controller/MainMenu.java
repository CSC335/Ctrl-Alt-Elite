package view_controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CustomFont;
import model.SnakeAccount;
import model.SnakeAccountCollection;
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

    public MainMenu(SnakeGUI snakeGUI, Stage stage, String username) {
        buttonFont = new CustomFont(BUTTON_FONT_SIZE);
        if (username == null) {
            username = "Guest";
        }
        initializeComponents(snakeGUI, stage, username);
        layoutComponents();
    }

    

    private void initializeComponents(SnakeGUI snakeGUI, Stage stage, String username) {
        startGameButton = new Button("Start Game");
        startGameButton.setOnAction(event -> startGame(snakeGUI, stage));
        startGameButton.setFont(buttonFont.getCustomFont());
        startGameButton.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        startGameButton.setTextFill(Color.WHITE);

        settingsButton = new Button("Settings");
        settingsButton.setOnAction(event -> openSettings(snakeGUI, stage));
        settingsButton.setFont(buttonFont.getCustomFont());
        settingsButton.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        settingsButton.setTextFill(Color.WHITE);

        welcomeLabel = new Label("Welcome, " + username + "!");
        welcomeLabel.setFont(buttonFont.getCustomFont());
        welcomeLabel.setTextFill(Color.WHITE);

      
        SnakeAccountCollection accountCollection = new SnakeAccountCollection();
        SnakeAccount account = accountCollection.getAccount(username);
        if (account != null) {
            highScoreLabel = new Label("Overall High Score: " + account.getHighScore());
            highScoreLabel.setFont(buttonFont.getCustomFont());
            highScoreLabel.setTextFill(Color.WHITE);
        }
    }

    private void layoutComponents() {
        this.getChildren().addAll(welcomeLabel, highScoreLabel, startGameButton, settingsButton); // Include high score label
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setSpacing(20);
    }

    private void startGame(SnakeGUI snakeGUI, Stage stage) {
        snakeGUI.startGame(stage);
    }

    private void openSettings(SnakeGUI snakeGUI, Stage stage) {
        SettingsMenu settingsMenu = new SettingsMenu(snakeGUI, stage);
        Scene settingsScene = new Scene(settingsMenu, 400, 400);
        stage.setScene(settingsScene);
    }
}