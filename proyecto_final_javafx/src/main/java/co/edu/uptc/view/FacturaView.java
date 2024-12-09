package co.edu.uptc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import co.edu.uptc.App;
import co.edu.uptc.model.Producto;
import co.edu.uptc.presenter.FacturaPresenter;

public class FacturaView {
    @FXML
    private ListView<Producto> listView;

    @FXML
    private Button btnFact;

    @FXML
    private Button btnBack;

     @FXML
    private TextField txtUser;

    @FXML
    private TextField txtDir;

    @FXML
    private Label lblCalculatedValue;

    private FacturaPresenter presenter;

    public void initialize() {
        presenter = new FacturaPresenter(this);
        double priceTotal = presenter.calculatePrice();
        btnFact.setOnAction(event ->{ presenter.handleSave( txtUser.getText(), txtDir.getText(), priceTotal);
                                      App.switchScene("PantallaInicial.fxml");});
        btnBack.setOnAction(event -> App.switchScene("Carrito.fxml"));
        lblCalculatedValue.setText("Precio Total: $" + priceTotal);
        presenter.loadProductosCarrito();
    }


    public void updateListView(java.util.List<Producto> productos) {
        listView.getItems().setAll(productos);
    }
    public void mostrarExito(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Éxito");
        alerta.setHeaderText(null); // Sin encabezado
        alerta.setContentText(mensaje); // Mensaje personalizado
        alerta.showAndWait(); // Muestra el cuadro de diálogo y espera
    }

    public void clearFields() {
        txtUser.clear();
        txtDir.clear();
    }

    public void mostrarError( String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText(null); // Puedes personalizar o eliminar el encabezado
        alerta.setContentText(mensaje);
        alerta.showAndWait(); // Muestra la alerta y espera a que el usuario cierre el cuadro
    }
}