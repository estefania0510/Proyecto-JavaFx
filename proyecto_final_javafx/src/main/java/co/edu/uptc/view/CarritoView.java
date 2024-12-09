package co.edu.uptc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

import co.edu.uptc.App;
import co.edu.uptc.model.Producto;
import co.edu.uptc.presenter.CarritoPresenter;
public class CarritoView {
    @FXML
    private ListView<Producto> listView;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnShop;

    @FXML
    private Button btnBack;

    private CarritoPresenter presenter;

    public void initialize() {
        presenter = new CarritoPresenter(this);

        btnEdit.setOnAction(event -> handleEdit());
        btnDelete.setOnAction(event -> handleDelete());
        btnShop.setOnAction(event ->  App.switchScene("Factura.fxml"));
        btnBack.setOnAction(event -> App.switchScene("PantallaInicial.fxml"));
        presenter.loadProductoCarrito();
    }

    private void handleEdit() {
        Producto selectedProducto = listView.getSelectionModel().getSelectedItem();
        
        if (selectedProducto == null) {
            mostrarError("Selecciona un elemento para agregar al carrito.");
            return;
        }
        TextInputDialog cantidadDialog = new TextInputDialog(selectedProducto.getNombre());
        cantidadDialog.setTitle("Cantidad del producto");
        cantidadDialog.setContentText("Cantidad:");
        Optional<String> newCantidad = cantidadDialog.showAndWait();

        if (newCantidad.isEmpty() || !isNumeric(newCantidad.get())) {
            mostrarError("La cantidad del producto tiene que ser diferente de 0.");
            return;
        }
        int cantidad = Integer.parseInt(newCantidad.get());
        presenter.handleSave(selectedProducto,cantidad);
    }


    private void handleDelete() {
        Producto selectedProducto = listView.getSelectionModel().getSelectedItem();
        if (selectedProducto == null) {
            mostrarError("Selecciona un elemento para eliminar.");
            return;
        }
        presenter.handleDelete(selectedProducto);
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

    public void mostrarError( String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText(null); // Puedes personalizar o eliminar el encabezado
        alerta.setContentText(mensaje);
        alerta.showAndWait(); // Muestra la alerta y espera a que el usuario cierre el cuadro
    }
    private boolean isNumeric(String input) {
        int cantidad = Integer.parseInt(input);
        if(cantidad>0){
            return true;
        }else{
            return false;
        }
    }
}
