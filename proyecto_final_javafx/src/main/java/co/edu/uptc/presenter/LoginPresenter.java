package co.edu.uptc.presenter;

import co.edu.uptc.model.User;
import co.edu.uptc.util.FileManagement;
import co.edu.uptc.view.LoginController;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class LoginPresenter {
    //private LoginController view;
    private List<User> users;
    private final FileManagement<User> fileManager;
    private final String usersFilePath = "Proyecto-JavaFx/proyecto_final_javafx/src/main/java/co/edu/uptc/persistence/users.json";
    private final Type userListType = new TypeToken<List<User>>() {}.getType();

    public LoginPresenter(LoginController view) {
        //this.view = view;
        this.fileManager = new FileManagement<>();
        
    }

     public boolean verificarCredenciales(String user, String password) {
        // Leer el archivo JSON
        users = fileManager.readObjects(usersFilePath, userListType);

        // Buscar si las credenciales son válidas
        for (User usuario : users) {
            if (usuario.getUser().equals(user) && usuario.getPassword().equals(password)) {
                return true; // Credenciales válidas
            }
        }

        return false; // Credenciales inválidas
    }




  

}
