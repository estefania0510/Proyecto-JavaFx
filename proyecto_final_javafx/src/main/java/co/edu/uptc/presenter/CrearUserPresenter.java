package co.edu.uptc.presenter;


import co.edu.uptc.model.User;
import co.edu.uptc.util.FileManagement;
import co.edu.uptc.view.RegisterView;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class CrearUserPresenter {
    private final FileManagement<User> fileManager;
    private RegisterView view;
    private final String usersFilePath = "Proyecto-JavaFx/proyecto_final_javafx/src/main/java/co/edu/uptc/persistence/users.json";
    private final Type userListType = new TypeToken<List<User>>() {}.getType();

    public CrearUserPresenter(RegisterView view) {
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
        if(verificar(usuarios, user)==true){
            view.mostrarError("ERROR...\n El usuario "+ user +  " ya existe");
        }else{
            usuarios.add(new User(user,password));  
            fileManager.saveObject(usuarios, usersFilePath, userListType);
            view.mostrarExito("El usuario ha sido creado con éxito!!");

        }
        view.clearFields();
    }

    private boolean verificar(List <User> usuarios,String user){
        for (User usuario : usuarios) {
            if (usuario.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidString (String string) {
        if (string != null && !string.trim().isEmpty()) {
            return true;
        }
        return false;
    }

}
