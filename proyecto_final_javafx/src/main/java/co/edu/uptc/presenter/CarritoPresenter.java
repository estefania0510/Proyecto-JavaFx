package co.edu.uptc.presenter;

import co.edu.uptc.model.Producto;
import co.edu.uptc.util.FileManagement;
import co.edu.uptc.view.CarritoController;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class CarritoPresenter {
    private CarritoController view;
    private List<Producto> productosCarrito;
    private final FileManagement<Producto> fileManager;
    private final String productosCarritoFilePath = "Proyecto-JavaFx/proyecto_final_javafx/src/main/java/co/edu/uptc/persistence/productosCarrito.json";
    private final Type productoscarritoListType = new TypeToken<List<Producto>>() {}.getType();


    public CarritoPresenter(CarritoController view) {
        this.view = view;
        this.fileManager = new FileManagement<>();
        loadProductoCarrito();
    }
    public void handleSave( Producto selectProducto, int newCantidad) {
        selectProducto.setstock(newCantidad);
        view.mostrarExito("Haz editado correctamente la cantidad de producto en el carrito!!");
        saveChanges();

    }

    public void loadProductoCarrito() {
        productosCarrito = fileManager.readObjects(productosCarritoFilePath, productoscarritoListType);
        view.updateListView(productosCarrito);
    }


    public void handleDelete(Producto producto) {
        productosCarrito.remove(producto);
        saveChanges();
    }

    private void saveChanges() {
        fileManager.saveObject(productosCarrito, productosCarritoFilePath,productoscarritoListType);
        loadProductoCarrito();
    }
}
