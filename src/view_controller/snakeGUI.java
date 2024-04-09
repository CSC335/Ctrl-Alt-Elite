package view_controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.GameOverHandler;
import model.ScoreManager;

public class snakeGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        VBox gameOverHandler = new GameOverHandler(new ScoreManager());
        Scene scene = new Scene(gameOverHandler, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}
