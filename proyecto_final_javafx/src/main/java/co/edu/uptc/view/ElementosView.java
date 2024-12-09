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
import co.edu.uptc.presenter.ElementosPresenter;

public class ElementosView {
    @FXML
    private ListView<Producto> listView;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    private ElementosPresenter presenter;

    public void initialize() {
        presenter = new ElementosPresenter(this);

        btnAdd.setOnAction(event -> handleAdd());
        btnBack.setOnAction(event -> App.switchScene("PantallaInicial.fxml"));

        presenter.loadElementos();
    }

    private void handleAdd() {
        try{
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
        if( presenter.verificacion(cantidad,selectedProducto)){
            selectedProducto.setstock(cantidad);
            presenter.handleSave(selectedProducto);
        }else{
            mostrarError("La cantidad del producto excede la disponible.");
            return;
        }}catch(NumberFormatException e){
            mostrarError("La cantidad del producto debe ser un número.");
        }
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