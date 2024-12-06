package co.edu.uptc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import co.edu.uptc.App;
import co.edu.uptc.model.Sala;
import co.edu.uptc.presenter.EditarYeliminarPresenter;

import java.util.Optional;

public class EditarYeliminarController {
    @FXML
    private ListView<Sala> listView;

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
        btnBack.setOnAction(event -> App.switchScene("Main.fxml"));

        presenter.loadPersons();
    }

    private void handleEdit() {
        Sala selectedSala = listView.getSelectionModel().getSelectedItem();
        if (selectedSala == null) {
            showError("Selecciona un elemento para editar.");
            return;
        }

        TextInputDialog nameDialog = new TextInputDialog(selectedSala.getNombre());
        nameDialog.setTitle("Editar Sala");
        nameDialog.setHeaderText("Editar Nombre");
        nameDialog.setContentText("Nombre:");
        Optional<String> newName = nameDialog.showAndWait();

        if (newName.isEmpty() || newName.get().trim().isEmpty()) {
            showError("El nombre no puede estar vacío.");
            return;
        }

        TextInputDialog capMaximaDialog = new TextInputDialog(String.valueOf(selectedSala.getCapMaxima()));
        capMaximaDialog.setTitle("Editar Sala");
        capMaximaDialog.setHeaderText("Editar Capacidad Máxima");
        capMaximaDialog.setContentText("Capacidad Maxima:");
        Optional<String> newAge = capMaximaDialog.showAndWait();

        if (newAge.isEmpty() || !isNumeric(newAge.get())) {
            showError("La capacidad maxima debe ser un número válido.");
            return;
        }

        presenter.handleEdit(selectedSala, newName.get(), Integer.parseInt(newAge.get()));
    }

    private void handleDelete() {
        Sala selectedSala = listView.getSelectionModel().getSelectedItem();
        if (selectedSala == null) {
            showError("Selecciona un elemento para eliminar.");
            return;
        }
        presenter.handleDelete(selectedSala);
    }

    public void updateListView(java.util.List<Sala> salas) {
        listView.getItems().setAll(salas);
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isNumeric(String input) {
        return input.matches("\\d+");
    }
}
