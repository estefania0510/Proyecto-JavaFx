package co.edu.uptc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import co.edu.uptc.App;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import co.edu.uptc.presenter.CrearUserPresenter;

public class RegisterController {
    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPass;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnBack;

    private CrearUserPresenter presenter;
    public void initialize() {
        presenter = new CrearUserPresenter(this);
        btnSave.setOnAction(event -> presenter.handleSave( txtUser.getText(), txtPass.getText()));
        btnBack.setOnAction(event -> App.switchScene("Main.fxml"));
    }

    public void showError(String message) {
        // Muestra un mensaje de error
        System.err.println("Error: " + message);
    }

    public void clearFields() {
        txtUser.clear();
        txtPass.clear();
    }
    public void mostrarExito(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Éxito");
        alerta.setHeaderText(null); // Sin encabezado
        alerta.setContentText(mensaje); // Mensaje personalizado
        alerta.showAndWait(); // Muestra el cuadro de diálogo y espera
    }
    public void mostrarError( String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText(null); // Puedes personalizar o eliminar el encabezado
        alerta.setContentText(mensaje);
        alerta.showAndWait(); // Muestra la alerta y espera a que el usuario cierre el cuadro
    }
}
