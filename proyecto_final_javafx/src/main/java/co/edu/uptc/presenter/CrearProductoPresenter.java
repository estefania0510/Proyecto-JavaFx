package co.edu.uptc.presenter;

import co.edu.uptc.model.Producto;
import co.edu.uptc.util.FileManagement;
import co.edu.uptc.view.CrearProductoController;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class CrearProductoPresenter {
    private final FileManagement<Producto> fileManager;
    private CrearProductoController view;
    private final String productoFilePath = "Proyecto-JavaFx/proyecto_final_javafx/src/main/java/co/edu/uptc/persistence/productos.json";
    private final Type productoListType = new TypeToken<List<Producto>>() {}.getType();

    public CrearProductoPresenter(CrearProductoController view) {
        this.view = view;
        this.fileManager = new FileManagement<>();
    }

    public void handleSave(String id, String nombre, String stock,String description, String price, String categoria) {
        if (!isValidString(nombre)) {
            view.mostrarError("El nombre no puede estar vacío.");
            return;
        }
        if (!isValidString(stock)) {
            view.mostrarError("El stock no puede estar vacío.");
            return;
        }
        if (!isValidString(description)) {
            view.mostrarError("La descripción no puede estar vacía.");
            return;
        }
        if (!isNumeric(price)) {
            view.mostrarError("El precio debe ser un número.");
            return;
        }
        if (!isValidString(categoria)) {
            view.mostrarError("La categoria no puede estar vacía.");
            return;
        }
        int parsedStock = Integer.parseInt(stock);
        int parsedId = Integer.parseInt(id);
        double parsedPrice = Double.parseDouble(price);
        List<Producto> productos = fileManager.readObjects(productoFilePath,productoListType);
        productos.add(new Producto(parsedId, nombre, parsedStock, description,parsedPrice,categoria));
        fileManager.saveObject(productos, productoFilePath, productoListType);

        view.clearFields();
    }

    private boolean isValidString (String string) {
        if (string != null && !string.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isNumeric(String input) {
        if (input.matches("\\d+")) {
            int parsedInput = Integer.parseInt(input);
            if (parsedInput > 0 ) {
                return true;
            }
        }
        return false;
    }
}