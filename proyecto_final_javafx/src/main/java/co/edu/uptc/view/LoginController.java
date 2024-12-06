package co.edu.uptc.view;


import co.edu.uptc.App;
import co.edu.uptc.model.Sala;
import co.edu.uptc.model.User;
import co.edu.uptc.presenter.LoginPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class LoginController {
    @FXML
    private ListView<User> listView;

    @FXML
    private Button btnNext;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPass;

    @FXML
    private Button btnBack;

    private LoginPresenter presenter;
    private User user;

    @FXML
    public void initialize() {
        presenter = new LoginPresenter(this);

        btnNext.setOnAction(event -> {
            String user = txtUser.getText();
            String password = txtPass.getText();
            if(user.equals("admin") && password.equals("admin123")){
                App.switchScene("Admin.fxml"); // Cambiar a la próxima interfaz
            }else if (presenter.verificarCredenciales(user, password)) {
                App.switchScene("PantallaInicial.fxml"); // Cambiar a la próxima interfaz
            } else {
                mostrarError( "Usuario o contraseña no válidos.");
            }
        });
        btnBack.setOnAction(event -> App.switchScene("Main.fxml"));
        
    }
    public void updateListView(java.util.List<Sala> salas) {
        listView.getItems().setAll(user);
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

     public void mostrarError( String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText(null); // Puedes personalizar o eliminar el encabezado
        alerta.setContentText(mensaje);
        alerta.showAndWait(); // Muestra la alerta y espera a que el usuario cierre el cuadro
    }
}
