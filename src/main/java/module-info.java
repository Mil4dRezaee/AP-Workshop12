module com.game.ticTacToe {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.game.ticTacToe; // This exports the package to all modules
    opens com.game.ticTacToe to javafx.fxml; // This opens the package to JavaFX runtime for reflection
}