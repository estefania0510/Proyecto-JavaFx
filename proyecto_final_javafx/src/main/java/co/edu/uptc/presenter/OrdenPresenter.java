package co.edu.uptc.presenter;

import co.edu.uptc.model.Orden;
import co.edu.uptc.util.FileManagement;
import co.edu.uptc.view.OrdenController;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class OrdenPresenter {
    private OrdenController view;
    private List<Orden> ordenes;
    private final FileManagement<Orden> fileManager;
    private final String ordenesFilePath = "Proyecto-JavaFx/proyecto_final_javafx/src/main/java/co/edu/uptc/persistence/ordenes.json";
    private final Type ordenesListType = new TypeToken<List<Orden>>() {}.getType();

    public OrdenPresenter(OrdenController view) {
        this.view = view;
        this.fileManager = new FileManagement<>();
        loadOrdenes();
    }

    public void loadOrdenes() {
        ordenes = fileManager.readObjects(ordenesFilePath, ordenesListType);
        view.updateListView(ordenes);
    }

}
