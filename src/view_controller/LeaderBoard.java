package view_controller;

import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.SnakeAccount;
import model.SnakeAccountCollection;
import java.util.Comparator;
import java.util.stream.Collectors;
import javafx.scene.Scene;
import model.CustomFont;

public class LeaderBoard extends VBox {
    private SnakeAccountCollection accountCollection;
    private CustomFont customFont;

    public LeaderBoard(SnakeAccountCollection accountCollection) {
        this.accountCollection = accountCollection;
        this.customFont = new CustomFont(18);  // Set font size for the leaderboard
        initializeComponents();
    }

    private void initializeComponents() {
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(accountCollection.getAccounts().stream()
                .sorted(Comparator.comparing(SnakeAccount::getHighScore).reversed())
                .limit(5)
                .map(account -> account.getUsername() + ": " + account.getHighScore())
                .collect(Collectors.toList()));
        listView.setPrefSize(300, 200);
        //listView.setFont(customFont.getCustomFont().getFont());
        getChildren().add(listView);
    }

    public void display(Stage stage) {
        Scene scene = new Scene(this, 350, 250);
        stage.setTitle("Leaderboard");
        stage.setScene(scene);
        stage.show();
    }
}

