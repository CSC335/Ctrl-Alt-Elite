package view_controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.SnakeAccount;
import model.SnakeAccountCollection;
import model.SnakeGame;

/**
 * A JavaFX GUI that represents a game of Snake
 *
 * @author Sameeka Maroli
 */

public class SnakeGUI extends Application {

	private static final int TILE_SIZE = 20;
	private int WINDOW_WIDTH = 600;
	private int WINDOW_HEIGHT = 600;
	private int ROWS = WINDOW_HEIGHT / TILE_SIZE;
	private int COLUMNS = WINDOW_WIDTH / TILE_SIZE;
	
	private Canvas canvas;

	private SnakeGame snakeGame;
	private Scene currentScene;
	private SnakeAccount account; 
	private LoginPane loginPane;
	private SettingsMenu settingsMenu;
	private PauseMenu pauseMenu;
	private MainMenu mainMenu;
	private Stage mainStage;

	private SnakeAccountCollection accountCollection;

	/**
	 * Initialize the game and display it to a window
	 *
	 * @param primaryStage A Stage used to display the elements of the game
	 */
	@Override
	public void start(Stage primaryStage) {
		accountCollection = new SnakeAccountCollection();
		// Display main menu, start game if that option is selected, show menus, etc.
		loginPane = new LoginPane(accountCollection, this, primaryStage);
		settingsMenu = new SettingsMenu(this);
		mainMenu = new MainMenu(this);
		pauseMenu = new PauseMenu(this, snakeGame);
		//Scene mainMenuScene = mainMenu.getScene();
		//primaryStage.setScene(mainMenuScene);
		currentScene = new Scene(loginPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		getAccounts();

		primaryStage.setTitle("Snake Game");
		primaryStage.setScene(currentScene);  

		mainStage = primaryStage;
		mainStage.show();
		mainStage.setResizable(false);

		setOnCloseRequest(mainStage);
	}

	public void startGame() {
		mainStage.close();

		// Resize the stage and scene to show the full game
		canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		snakeGame = new SnakeGame(WINDOW_WIDTH, WINDOW_HEIGHT, settingsMenu.getCurrentInterval(),
				settingsMenu.getNumPellets(), gc);

		// Create root node to hold the Canvas
		StackPane root = new StackPane();
		root.getChildren().add(canvas);
		mainMenu.getScene().setRoot(new VBox()); // temporarily reset currentScene's root to free MainMenu
		currentScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		currentScene.setOnKeyPressed(event -> snakeGame.handleKeyPress(event.getCode()));
		currentScene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent key) -> {
			if (key.getCode() == KeyCode.ESCAPE) {
				if (currentScene.getRoot().equals(pauseMenu)) {
					continueGame();
				} else {
					snakeGame.stop();
					currentScene.setRoot(pauseMenu);
				}
			}
		});

		// Re-initialize the Stage
		mainStage = new Stage();
		mainStage.setTitle("Snake Game");
		mainStage.setScene(currentScene);
		mainStage.setResizable(false);
		mainStage.show();
		setOnCloseRequest(mainStage);

		snakeGame.start();
	}
	
	public void continueGame() {
		// Create root node to hold the Canvas
		StackPane root = new StackPane();
		root.getChildren().add(canvas);
		currentScene.setRoot(root);
		currentScene.setOnKeyPressed(event -> snakeGame.handleKeyPress(event.getCode()));
		
		mainStage.setScene(currentScene);
		snakeGame.start();
	}

	// Methods to check the state of SnakeGame and display corresponding menus

	/**
	 * Set the current scene's root to a given menu
	 *
	 * @param pane A pane object representing one of the menus
	 */
	public void setSceneRoot(Pane pane) {
		currentScene.setRoot(pane);
	}

	public void setWindowSize(int tileWidth, int tileHeight) {
		WINDOW_WIDTH = tileWidth * TILE_SIZE;
		WINDOW_HEIGHT = tileHeight * TILE_SIZE;
	}

	private void saveAlert(Stage primaryStage) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Shutdown Confirmation");
		alert.setHeaderText("Save Data?");
		alert.setContentText("Do you want to save data before exiting?");

		ButtonType saveButton = new ButtonType("Save");
		ButtonType closeButton = new ButtonType("Close Without Saving");

		alert.getButtonTypes().setAll(saveButton, closeButton);

		alert.showAndWait().ifPresent(buttonType -> {
			if (buttonType == saveButton) {
				saveDataAndClose(primaryStage);
			} else if (buttonType == closeButton) {
				primaryStage.close();
			}
		});
	}


	private void saveDataAndClose(Stage primaryStage) {
		if (snakeGame.getScoreManager().getCurrentScore() > accountCollection.getOverallHighScore()) {
			accountCollection.updateOverallHighScore(snakeGame.getScoreManager().getCurrentScore());
			System.out.println("overall high score is now " + accountCollection.getOverallHighScore());
		}

		accountCollection.writeState();
		primaryStage.close();
	}

	private void setOnCloseRequest(Stage primaryStage) {
		if (snakeGame != null) {
			primaryStage.setOnCloseRequest(event -> {
				if (loginPane.getCurrentAccount() != null || snakeGame.getScoreManager().getCurrentScore() > accountCollection.getOverallHighScore()) {		

					if (loginPane.getCurrentAccount() != null && snakeGame.getScoreManager().getCurrentScore() > loginPane.getCurrentAccount().getHighScore()){
						loginPane.getCurrentAccount().setHighScore(snakeGame.getScoreManager().getCurrentScore());
					}
					saveAlert(primaryStage);
				}	
		});}
	}

	private void getAccounts() {
		try {
			accountCollection.readState();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SnakeAccountCollection getAccountCollection() {
		return accountCollection;
	}
	
	public SettingsMenu getSettingsMenu() {
		return settingsMenu;
	}

	public MainMenu getMainMenu() {
		if (mainMenu == null) {
			mainMenu = new MainMenu(this);
		}
		return mainMenu;
	}

	public LoginPane getLoginPane() {
		return loginPane;
	}

	public PauseMenu getPauseMenu() {
		return pauseMenu;
	}
}
