package co.edu.uptc.presenter;


import co.edu.uptc.model.User;
import co.edu.uptc.util.FileManagement;
import co.edu.uptc.view.RegisterController;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class CrearUserPresenter {
    private final FileManagement<User> fileManager;
    private RegisterController view;
    private final String usersFilePath = "src/main/java/co/edu/uptc/persistence/users.json";
    private final Type userListType = new TypeToken<List<User>>() {}.getType();

    public CrearUserPresenter(RegisterController view) {
        this.view = view;
        this.fileManager = new FileManagement<>();
    }

    public void handleSave( String user, String password) {
        if (!isValidString(user)) {
            view.mostrarError("El usuario no puede estar vacío.");
            return;
        }
        if (!isValidString(password)) {
            view.mostrarError("La contraseña no puede estar vacía.");
            return;
        }
        

        List<User> usuarios = fileManager.readObjects(usersFilePath,userListType);
        usuarios.add(new User(user,password));
        fileManager.saveObject(usuarios, usersFilePath, userListType);

        view.clearFields();
    }

    private boolean isValidString (String string) {
        if (string != null && !string.trim().isEmpty()) {
            return true;
        }
        return false;
    }

}
