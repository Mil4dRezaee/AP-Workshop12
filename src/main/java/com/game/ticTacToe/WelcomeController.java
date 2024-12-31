package com.game.ticTacToe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    private Button startGameButton;

    @FXML
    private Button quitButton;

    public void switchToTicTacToeController() {
        switchPage("fx/TicTacToe.fxml");
    }

    public void switchPage(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 400, 475);
            scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());

            Stage stage = (Stage) startGameButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Game");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void quitGame(ActionEvent actionEvent) {
        System.exit(0);
    }
}