package co.edu.uptc.presenter;

import co.edu.uptc.model.Sala;
import co.edu.uptc.util.FileManagement;
import co.edu.uptc.view.EditarYeliminarController;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class EditarYeliminarPresenter {
    private EditarYeliminarController view;
    private List<Sala> salas;
    private final FileManagement<Sala> fileManager;
    private final String salasFilePath = "src/main/java/co/edu/uptc/persistence/salas.json";
    private final Type salaListType = new TypeToken<List<Sala>>() {}.getType();

    public EditarYeliminarPresenter(EditarYeliminarController view) {
        this.view = view;
        this.fileManager = new FileManagement<>();
        loadPersons();
    }

    public void loadPersons() {
        salas = fileManager.readObjects(salasFilePath, salaListType);
        view.updateListView(salas);
    }

    public void handleEdit(Sala sala, String newName, int newCap) {
        sala.setNombre(newName);
        sala.setCapMaxima(newCap);
        saveChanges();
    }

    public void handleDelete(Sala sala) {
        salas.remove(sala);
        saveChanges();
    }

    private void saveChanges() {
        fileManager.saveObject(salas, salasFilePath,salaListType);
        loadPersons();
    }
}
