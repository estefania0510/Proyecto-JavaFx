package co.edu.uptc.presenter;

import co.edu.uptc.model.Producto;
import co.edu.uptc.util.FileManagement;
import co.edu.uptc.view.EditarYeliminarController;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class EditarYeliminarPresenter {
    private EditarYeliminarController view;
    private List<Producto> productos;
    private final FileManagement<Producto> fileManager;
    private final String productosFilePath = "Proyecto-JavaFx/proyecto_final_javafx/src/main/java/co/edu/uptc/persistence/productos.json";
    private final Type productoListType = new TypeToken<List<Producto>>() {}.getType();

    public EditarYeliminarPresenter(EditarYeliminarController view) {
        this.view = view;
        this.fileManager = new FileManagement<>();
        loadProductos();
    }

    public void loadProductos() {
        productos = fileManager.readObjects(productosFilePath, productoListType);
        view.updateListView(productos);
    }

    public void handleEdit(Producto producto, String newName, int newStock,String newDescrip, Double newPrice,String newCateg) {
        producto.setNombre(newName);
        producto.setstock(newStock);
        producto.setDescription(newDescrip);
        producto.setPrice(newPrice);
        producto.setCategoria(newCateg);
        saveChanges();
    }

    public void handleDelete(Producto producto) {
        productos.remove(producto);
        saveChanges();
    }

    private void saveChanges() {
        fileManager.saveObject(productos, productosFilePath,productoListType);
        loadProductos();
    }
}
