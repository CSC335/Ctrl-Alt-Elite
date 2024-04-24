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
    
    private Label headerLabel, difficultyLabel, sizeLabel, modeLabel;
    private Slider boardSizeSlider;
    private Button easy, medium, hard, nightmare, backButton, regularMode, frenzyMode;
    
    private CustomFont headerFont, labelFont, settingsFont;
    private Background background;
    
    private SnakeGUI snakeGUI;
    private long currentInterval;
    private int numPellets;
    
    public SettingsMenu(SnakeGUI snakeGUI) {
        this.snakeGUI = snakeGUI;
        currentInterval = MEDIUM_INTERVAL;
        numPellets = 1;
        settingsFont = new CustomFont(12);
        headerFont = new CustomFont(40);
        labelFont = new CustomFont(16);
        background = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
        
        initializeElements();
        addListeners();
        layoutGUI();
    }
    
    private void initializeElements() {
        headerLabel = new Label("Settings");
        difficultyLabel = new Label("Difficulty");
        sizeLabel = new Label("Board Size");
        modeLabel = new Label("Game Mode");
        setFont(headerLabel, headerFont);
        setFont(difficultyLabel, labelFont);
        setFont(sizeLabel, labelFont);
        setFont(modeLabel, labelFont);
        
        easy = new Button("Easy");
        medium = new Button("Normal");
        hard = new Button("Hard");
        nightmare = new Button("Nightmare");
        setFont(easy);
        setFont(medium);
        setFont(hard);
        setFont(nightmare);
        medium.setTextFill(Color.CYAN);
        
        regularMode = new Button("Regular");
        frenzyMode = new Button("Frenzy");
        setFont(regularMode);
        setFont(frenzyMode);
        regularMode.setTextFill(Color.CYAN);
        
        int min = 20, max = 50;
        boardSizeSlider = new Slider(min, max, 30);
        boardSizeSlider.setSnapToTicks(true);
        boardSizeSlider.setMajorTickUnit(10);
        boardSizeSlider.setBlockIncrement(1);
        boardSizeSlider.setMinorTickCount(0);
        boardSizeSlider.setShowTickMarks(true);
        boardSizeSlider.setShowTickLabels(true);
        boardSizeSlider.setMaxWidth(400);
        
        backButton = new Button("Back");
        setFont(backButton);
    }
    
    private void addListeners() {
        easy.setOnAction(new DifficultyHandler());
        medium.setOnAction(new DifficultyHandler());
        hard.setOnAction(new DifficultyHandler());
        nightmare.setOnAction(new DifficultyHandler());
        
        regularMode.setOnAction(new ModeHandler());
        frenzyMode.setOnAction(new ModeHandler());
        
        boardSizeSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> snakeGUI.setWindowSize(newValue.intValue(), newValue.intValue()));
        
        backButton.setOnAction(event -> {
            snakeGUI.setSceneRoot(snakeGUI.getMainMenu());
        });
    }
    
    private void setFont(Label label, CustomFont customFont) {
        label.setFont(customFont.getCustomFont());
        label.setTextFill(Color.WHITE);
        label.setBackground(background);
    }
    
    private void setFont(Button button) {
        button.setFont(settingsFont.getCustomFont());
        button.setTextFill(Color.WHITE);
        button.setBackground(background);
    }
    
    private void layoutGUI() {
        HBox difficultySettings = new HBox();
        difficultySettings.getChildren().addAll(easy, medium, hard, nightmare);
        difficultySettings.setSpacing(20);
        difficultySettings.setAlignment(Pos.CENTER);
        
        HBox modeSettings = new HBox();
        modeSettings.getChildren().addAll(regularMode, frenzyMode);
        modeSettings.setAlignment(Pos.CENTER);
        
        this.getChildren().addAll(headerLabel, spacingButton(), difficultyLabel, difficultySettings, spacingButton(),
                sizeLabel, boardSizeSlider, spacingButton(), modeLabel, modeSettings, spacingButton(), backButton);
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
    
    public int getNumPellets() {
        return numPellets;
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
    
    private class ModeHandler implements EventHandler<ActionEvent> {
        
        @Override
        public void handle(ActionEvent actionEvent) {
            Button source = (Button) actionEvent.getSource();
            regularMode.setTextFill(Color.WHITE);
            frenzyMode.setTextFill(Color.WHITE);
            
            if (source.equals(regularMode)) {
                numPellets = 1;
                currentInterval = MEDIUM_INTERVAL;
                
                easy.setTextFill(Color.WHITE);
                hard.setTextFill(Color.WHITE);
                nightmare.setTextFill(Color.WHITE);
                medium.setTextFill(Color.CYAN);
                
                boardSizeSlider.setValue(30);
                snakeGUI.setWindowSize((int) boardSizeSlider.getValue(), (int) boardSizeSlider.getValue());
            } else if (source.equals(frenzyMode)) {
                numPellets = 10;
                currentInterval = NIGHTMARE_INTERVAL;
                
                easy.setTextFill(Color.WHITE);
                medium.setTextFill(Color.WHITE);
                hard.setTextFill(Color.WHITE);
                nightmare.setTextFill(Color.CYAN);
                
                boardSizeSlider.setValue(50);
                snakeGUI.setWindowSize((int) boardSizeSlider.getValue(), (int) boardSizeSlider.getValue());
            }
            
            source.setTextFill(Color.CYAN);
        }
    }
    
}
