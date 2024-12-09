package co.edu.uptc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import co.edu.uptc.App;

public class Main {
    @FXML
    private Button btnRegister;

    @FXML
    private Button btnLogin;

    public void initialize() {
        btnRegister.setOnAction(event -> App.switchScene("Register.fxml"));
        btnLogin.setOnAction(event -> App.switchScene("Login.fxml"));
    }
    @FXML
    private void onMouseEnteredButton() {
        // Cambiar color al pasar el ratón
        btnRegister.setStyle("-fx-background-color: #5dade2; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10;");
        btnLogin.setStyle("-fx-background-color: #58d68d; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10;");
    }

    @FXML
    private void onMouseExitedButton() {
        // Restaurar color original al salir
        btnRegister.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10;");
        btnLogin.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10;");
    }
}
