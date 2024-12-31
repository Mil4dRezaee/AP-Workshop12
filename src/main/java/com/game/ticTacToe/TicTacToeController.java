package com.game.ticTacToe;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TicTacToeController {

    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    @FXML
    private Label statusLabel;

    private boolean isXTurn = true;
    private Button[][] buttons;

    @FXML
    public void initialize() {
        buttons = new Button[][]{
                {btn1, btn2, btn3},
                {btn4, btn5, btn6},
                {btn7, btn8, btn9}
        };
        statusLabel.setText("Player X's turn");
    }

    @FXML
    private void handleButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (!clickedButton.getText().isEmpty()) return;

        clickedButton.setText(isXTurn ? "X" : "O");
        if (checkWin()) {
            statusLabel.setText((isXTurn ? "Player X" : "Player O") + " wins!");
            disableButtons();
            delayAndSwitch("Winner");
        } else if (isBoardFull()) {
            statusLabel.setText("It's a draw!");
            delayAndSwitch("Draw");
        } else {
            isXTurn = !isXTurn;
            statusLabel.setText("Player " + (isXTurn ? "X" : "O") + "'s turn");
        }
    }

    private void delayAndSwitch(String result) {
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(3));
        pause.setOnFinished(event -> {
            if (result.equals("Winner")) {
                switchToWelcomeController();
            } else if (result.equals("Draw")) {
                switchToWelcomeController();
            }
        });
        pause.play();
    }

    private boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().isEmpty()) {
                return true;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[0][i].getText().equals(buttons[2][i].getText()) &&
                    !buttons[0][i].getText().isEmpty()) {
                return true;
            }
        }
        // Check diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().isEmpty()) {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                if (button.getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setDisable(true);
            }
        }
    }

    public void switchToWelcomeController() {
        switchPage("fx/Welcome.fxml");
    }

    public void switchPage(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 400, 475);
            scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());

            Stage stage = (Stage) statusLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Game");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
