package co.edu.uptc.presenter;

import co.edu.uptc.model.Producto;
import co.edu.uptc.util.FileManagement;
import co.edu.uptc.view.SuplementosView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class SuplementosPresenter {
    private SuplementosView view;
    private List<Producto> productos;
    private List<Producto> productosCarrito;
    private final FileManagement<Producto> fileManager;
    private final String productosFilePath = "Proyecto-JavaFx/proyecto_final_javafx/src/main/java/co/edu/uptc/persistence/productos.json";
    private final Type productoListType = new TypeToken<List<Producto>>() {}.getType();
    private final String productosCarritoFilePath = "Proyecto-JavaFx/proyecto_final_javafx/src/main/java/co/edu/uptc/persistence/productosCarrito.json";
    private final Type productoscarritoListType = new TypeToken<List<Producto>>() {}.getType();


    public SuplementosPresenter(SuplementosView view) {
        this.view = view;
        this.fileManager = new FileManagement<>();
        loadSuplementos();
    }
    public void handleSave( Producto selectProducto) {
        productosCarrito = fileManager.readObjects(productosCarritoFilePath,productoscarritoListType);
        productosCarrito.add(selectProducto);
        fileManager.saveObject(productosCarrito, productosCarritoFilePath, productoscarritoListType);

        productos = fileManager.readObjects(productosFilePath,productoListType);
        for (Producto producto : productos) {
            if (producto.getNombre().equals(selectProducto.getNombre())) {
                producto.setstock(producto.getstock()-selectProducto.getstock());
            }
        }
        view.mostrarExito("Haz agregado un producto con éxito al carrito!!");
        fileManager.saveObject(productos, productosFilePath,productoListType);
        loadSuplementos();

    }


    public boolean verificacion( int cantidad, Producto selectProducto) {
        boolean verificar = false;
        productos = fileManager.readObjects(productosFilePath,productoListType);
        for (Producto producto : productos) {
            if (producto.getNombre().equals(selectProducto.getNombre())) {
                if(cantidad > producto.getstock()){
                    verificar = false;
                }else{
                    verificar = true;
                }
            }
        }
        return verificar;

    }

    public void loadSuplementos() {
        productos = fileManager.readObjects(productosFilePath, productoListType);
        List<Producto> productos1 = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCategoria().equals("Suplementos")) {
                productos1.add(producto);
            }
        }
        view.updateListView(productos1);
    }

}

