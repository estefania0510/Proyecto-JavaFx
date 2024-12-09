package co.edu.uptc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import co.edu.uptc.App;
import co.edu.uptc.model.Producto;
import co.edu.uptc.presenter.EditarYeliminarPresenter;

import java.util.Optional;

public class EditarYeliminarController {
    @FXML
    private ListView<Producto> listView;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnBack;

    private EditarYeliminarPresenter presenter;

    public void initialize() {
        presenter = new EditarYeliminarPresenter(this);

        btnEdit.setOnAction(event -> handleEdit());
        btnDelete.setOnAction(event -> handleDelete());
        btnBack.setOnAction(event -> App.switchScene("Admin.fxml"));

        presenter.loadProductos();
    }

    private void handleEdit() {
        Producto selectedProducto = listView.getSelectionModel().getSelectedItem();
        if (selectedProducto == null) {
            mostrarError("Selecciona un elemento para editar.");
            return;
        }

        TextInputDialog nameDialog = new TextInputDialog(selectedProducto.getNombre());
        nameDialog.setTitle("Editar Producto");
        nameDialog.setHeaderText("Editar Nombre");
        nameDialog.setContentText("Nombre:");
        Optional<String> newName = nameDialog.showAndWait();

        if (newName.isEmpty() || newName.get().trim().isEmpty()) {
            mostrarError("El nombre no puede estar vacío.");
            return;
        }

        TextInputDialog stockDialog = new TextInputDialog(String.valueOf(selectedProducto.getstock()));
        stockDialog.setTitle("Editar Producto");
        stockDialog.setHeaderText("Editar Stock del producto");
        stockDialog.setContentText("Stock:");
        Optional<String> newStock = stockDialog.showAndWait();

        if (newStock.isEmpty() || !isNumeric(newStock.get())) {
            mostrarError("El stock debe ser un número válido.");
            return;
        }

        TextInputDialog descripDialog = new TextInputDialog(selectedProducto.getDescription());
        descripDialog.setTitle("Editar Producto");
        descripDialog.setHeaderText("Editar Descripción");
        descripDialog.setContentText("Descripción:");
        Optional<String> newDescrip = descripDialog.showAndWait();

        if (newDescrip.isEmpty() || newDescrip.get().trim().isEmpty()) {
            mostrarError("La descripción no puede estar vacía.");
            return;
        }

        TextInputDialog priceDialog = new TextInputDialog(String.valueOf(selectedProducto.getPrice()));
        priceDialog.setTitle("Editar Producto");
        priceDialog.setHeaderText("Editar Stock del producto");
        priceDialog.setContentText("Stock:");
        Optional<String> newPrice = priceDialog.showAndWait();

        if (newPrice.isEmpty() || !isNumeric(newPrice.get())) {
            mostrarError("El precio debe ser un número válido.");
            return;
        }

        TextInputDialog categDialog = new TextInputDialog(selectedProducto.getCategoria());
        categDialog.setTitle("Editar Producto");
        categDialog.setHeaderText("Editar Categoria");
        categDialog.setContentText("Categoria:");
        Optional<String> newCateg = categDialog.showAndWait();

        if (newCateg.isEmpty() || newCateg.get().trim().isEmpty()) {
            mostrarError("La categoria no puede estar vacía.");
            return;
        }

        presenter.handleEdit(selectedProducto, newName.get(), Integer.parseInt(newStock.get()),newDescrip.get(),Double.parseDouble(newPrice.get()),newCateg.get());
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

    public void mostrarError( String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText(null); // Puedes personalizar o eliminar el encabezado
        alerta.setContentText(mensaje);
        alerta.showAndWait(); // Muestra la alerta y espera a que el usuario cierre el cuadro
    }

    private boolean isNumeric(String input) {
        return input.matches("\\d+");
    }
}
