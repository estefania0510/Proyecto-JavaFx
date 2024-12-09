package co.edu.uptc.presenter;

import co.edu.uptc.model.Orden;
import co.edu.uptc.model.Producto;
import co.edu.uptc.util.FileManagement;
import co.edu.uptc.view.FacturaView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class FacturaPresenter {
    private FacturaView view;
    private List<Producto> productosCarrito;
    private final FileManagement<Producto> fileManager;
    private final FileManagement<Orden> fileManager1;
    private final String productosCarritoFilePath = "Proyecto-JavaFx/proyecto_final_javafx/src/main/java/co/edu/uptc/persistence/productosCarrito.json";
    private final Type productoscarritoListType = new TypeToken<List<Producto>>() {}.getType();
    private final String ordenesFilePath = "Proyecto-JavaFx/proyecto_final_javafx/src/main/java/co/edu/uptc/persistence/ordenes.json";
    private final Type ordenesListType = new TypeToken<List<Orden>>() {}.getType();


    public FacturaPresenter(FacturaView view) {
        this.view = view;
        this.fileManager = new FileManagement<>();
        this.fileManager1 = new FileManagement<>();
        loadProductosCarrito();
    }
    
    public double calculatePrice() {
        productosCarrito = fileManager.readObjects(productosCarritoFilePath,productoscarritoListType);
        double priceTotal = 0;
        for (Producto producto : productosCarrito) {
            priceTotal =( producto.getPrice()*producto.getstock()) + priceTotal;
        }
        return priceTotal;

    }

    public void handleSave( String user, String direccion, double priceTotal) {
        if (!isValidString(user)) {
            view.mostrarError("El usuario no puede estar vacío.");
            return;
        }
        if (!isValidString(direccion)) {
            view.mostrarError("La dirección no puede estar vacía.");
            return;
        }
        productosCarrito = fileManager.readObjects(productosCarritoFilePath,productoscarritoListType);

        List<Orden> ordenes = fileManager1.readObjects(ordenesFilePath,ordenesListType);
        ordenes.add(new Orden(user,productosCarrito,direccion,priceTotal));
        fileManager1.saveObject(ordenes, ordenesFilePath, ordenesListType);
        view.mostrarExito("La factura ha sido creada con éxito!!");

        view.mostrarExito("--------------------------------------------------"
                         + "\n                    FACTURA"
                         +"\n--------------------------------------------------" 
                         +"\n\nTitular: " +user
                         +"\n\nProductos comprados:\n"
                         + productosCarrito
                         +"\n\nDirección de entrega:"+ direccion 
                         +"\n\nPrecio total de la compra: $" + priceTotal 
                         +"\n-------------------------------------------------");

        productosCarrito = new ArrayList<>();
        fileManager.saveObject(productosCarrito, productosCarritoFilePath, productoscarritoListType);
        view.clearFields();
    }

    public void loadProductosCarrito() {
        productosCarrito = fileManager.readObjects(productosCarritoFilePath, productoscarritoListType);
        view.updateListView(productosCarrito);
    }
    
    private boolean isValidString (String string) {
        if (string != null && !string.trim().isEmpty()) {
            return true;
        }
        return false;
    }

}