package co.edu.uptc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import co.edu.uptc.App;


public class PantallaInicialController {

    @FXML
    private Button btnTennis;

    @FXML
    private Button btnSuplementos;

    @FXML
    private Button btnElementos;

    @FXML
    private Button btnConjuntos;
    @FXML
    private Button btnBack;


    public void initialize() {

        btnTennis.setOnAction(event -> App.switchScene("Main.fxml"));
        btnElementos.setOnAction(event -> App.switchScene("Register.fxml") );
        btnConjuntos.setOnAction(event -> App.switchScene("Register.fxml") );
        btnSuplementos.setOnAction(event -> App.switchScene("Register.fxml") );
        btnBack.setOnAction(event -> App.switchScene("Login.fxml"));

    }

}
