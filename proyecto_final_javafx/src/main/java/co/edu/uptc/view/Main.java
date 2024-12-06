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
}
