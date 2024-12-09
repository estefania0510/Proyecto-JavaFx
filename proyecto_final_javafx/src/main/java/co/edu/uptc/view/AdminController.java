package co.edu.uptc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import co.edu.uptc.App;


public class AdminController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnView;

    @FXML
    private Button btnOrden;

    @FXML
    private Button btnBack;


    public void initialize() {

        btnAdd.setOnAction(event -> App.switchScene("AgregarProducto.fxml"));
        btnView.setOnAction(event -> App.switchScene("EditarYeliminar.fxml") );
        btnOrden.setOnAction(event -> App.switchScene("Orden.fxml") );
        btnBack.setOnAction(event -> App.switchScene("Login.fxml"));

    }

}
