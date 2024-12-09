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
import co.edu.uptc.presenter.ViewTennisPresenter;

public class ViewTennisController {
    @FXML
    private ListView<Producto> listView;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    private ViewTennisPresenter presenter;

    public void initialize() {
        presenter = new ViewTennisPresenter(this);

        btnAdd.setOnAction(event -> handleAdd());
        btnBack.setOnAction(event -> App.switchScene("PantallaInicial.fxml"));

        presenter.loadTennis();
    }

    private void handleAdd() {
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
        selectedProducto.setstock(cantidad);
        presenter.handleSave(selectedProducto);

     

        //presenter.handleAdd(selectedProducto, newName.get(), Integer.parseInt(newStock.get()),newDescrip.get(),Double.parseDouble(newPrice.get()),newCateg.get());
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
