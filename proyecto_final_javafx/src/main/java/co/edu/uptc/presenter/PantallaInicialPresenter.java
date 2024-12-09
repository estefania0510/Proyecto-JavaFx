package co.edu.uptc.presenter;

import co.edu.uptc.model.Producto;
import co.edu.uptc.util.FileManagement;
import co.edu.uptc.view.PantallaInicialController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class PantallaInicialPresenter {
    private List<Producto> productosCarrito;
    private final FileManagement<Producto> fileManager;
    private final String productosCarritoFilePath = "src/main/java/co/edu/uptc/persistence/productosCarrito.json";
    private final Type productoscarritoListType = new TypeToken<List<Producto>>() {}.getType();

    public PantallaInicialPresenter(PantallaInicialController view) {
        this.fileManager = new FileManagement<>();
        
    }

     public void vaciarCarrito() {
        // Leer el archivo JSON
        productosCarrito = new ArrayList<>();
        fileManager.saveObject(productosCarrito, productosCarritoFilePath, productoscarritoListType);
    }
}