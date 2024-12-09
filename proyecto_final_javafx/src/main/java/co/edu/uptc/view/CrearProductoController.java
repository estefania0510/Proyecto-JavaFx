package co.edu.uptc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import co.edu.uptc.App;
import co.edu.uptc.presenter.CrearProductoPresenter;

public class CrearProductoController {
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtDescrip;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtCateg;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnBack;

    private CrearProductoPresenter presenter;
    public void initialize() {
        presenter = new CrearProductoPresenter(this);
        btnSave.setOnAction(event -> presenter.handleSave("1", txtName.getText(), txtStock.getText(), txtDescrip.getText(),txtPrice.getText(),txtCateg.getText()));
        btnBack.setOnAction(event -> App.switchScene("Admin.fxml"));
    }

    public void mostrarError( String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText(null); // Puedes personalizar o eliminar el encabezado
        alerta.setContentText(mensaje);
        alerta.showAndWait(); // Muestra la alerta y espera a que el usuario cierre el cuadro
    }

    public void clearFields() {
        txtName.clear();
        txtStock.clear();
        txtDescrip.clear();
        txtPrice.clear();
        txtCateg.clear();
    }
}
