package view_controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CustomFont;

public class SettingsMenu extends VBox {
    
    private static final long EASY_INTERVAL = 100_000_000;
    private static final long MEDIUM_INTERVAL = 75_000_000;
    private static final long HARD_INTERVAL = 50_000_000;
    private static final long NIGHTMARE_INTERVAL = 25_000_000;
    
    private Label difficulty, sizeLabel, mode;
    private Slider boardSize;
    private Button easy, medium, hard, nightmare, backButton;
    
    private CustomFont customFont;
    private Background background;
    
    private SnakeGUI snakeGUI;
    private Stage stage;
    private long currentInterval;
    
    public SettingsMenu(SnakeGUI snakeGUI, Stage stage) {
        this.snakeGUI = snakeGUI;
        this.stage = stage;
        currentInterval = MEDIUM_INTERVAL;
        customFont = new CustomFont(12);
        background = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
        initializeElements();
        addListeners();
        layoutGUI();
    }
    
    private void initializeElements() {
        difficulty = new Label("Difficulty");
        sizeLabel = new Label("Board Size");
        mode = new Label("Game Mode");
        setFont(difficulty);
        setFont(sizeLabel);
        setFont(mode);
        
        easy = new Button("Easy");
        medium = new Button("Normal");
        hard = new Button("Hard");
        nightmare = new Button("Nightmare");
        setFont(easy);
        setFont(medium);
        setFont(hard);
        setFont(nightmare);
        medium.setTextFill(Color.CYAN);
        
        int min = 20, max = 50;
        boardSize = new Slider(min, max, 30);
        boardSize.setSnapToTicks(true);
        boardSize.setMajorTickUnit(10);
        boardSize.setBlockIncrement(1);
        boardSize.setMinorTickCount(0);
        boardSize.setShowTickMarks(true);
        boardSize.setShowTickLabels(true);
        boardSize.setMaxWidth(400);
        
        backButton = new Button("Back");
        setFont(backButton);
    }
    
    private void addListeners() {
        easy.setOnAction(new DifficultyHandler());
        medium.setOnAction(new DifficultyHandler());
        hard.setOnAction(new DifficultyHandler());
        nightmare.setOnAction(new DifficultyHandler());
        
        boardSize.valueProperty().addListener(
                (observable, oldValue, newValue) -> snakeGUI.setWindowSize(newValue.intValue(), newValue.intValue()));
        
        backButton.setOnAction(event -> {
            snakeGUI.startGame(stage);
        });
    }
    
    private void setFont(Label label) {
        label.setFont(customFont.getCustomFont());
        label.setTextFill(Color.WHITE);
        label.setBackground(background);
    }
    
    private void setFont(Button button) {
        button.setFont(customFont.getCustomFont());
        button.setTextFill(Color.WHITE);
        button.setBackground(background);
    }
    
    private void layoutGUI() {
        HBox difficultySettings = new HBox();
        difficultySettings.getChildren().addAll(easy, medium, hard, nightmare);
        difficultySettings.setSpacing(20);
        difficultySettings.setAlignment(Pos.CENTER);
        
        this.getChildren().addAll(difficulty, difficultySettings, spacingButton() , sizeLabel ,boardSize,
                spacingButton(), backButton);
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
    
    public long getCurrentInterval() {
        return currentInterval;
    }
    
    private class DifficultyHandler implements EventHandler<ActionEvent> {
        
        @Override
        public void handle(ActionEvent actionEvent) {
            Button source = (Button) actionEvent.getSource();
            easy.setTextFill(Color.WHITE);
            medium.setTextFill(Color.WHITE);
            hard.setTextFill(Color.WHITE);
            nightmare.setTextFill(Color.WHITE);
            
            if (source.equals(easy)) {
                currentInterval = EASY_INTERVAL;
            } else if (source.equals(medium)) {
                currentInterval = MEDIUM_INTERVAL;
            } else if (source.equals(hard)) {
                currentInterval = HARD_INTERVAL;
            } else if (source.equals(nightmare)) {
                currentInterval = NIGHTMARE_INTERVAL;
            }
            
            source.setTextFill(Color.CYAN);
        }
        
    }
    
}
