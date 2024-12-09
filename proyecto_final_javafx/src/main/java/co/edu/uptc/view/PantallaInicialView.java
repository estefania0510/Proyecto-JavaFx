package co.edu.uptc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import co.edu.uptc.App;
import co.edu.uptc.presenter.PantallaInicialPresenter;


public class PantallaInicialView {

    @FXML
    private Button btnTennis;

    @FXML
    private Button btnSuplementos;

    @FXML
    private Button btnElementos;

    @FXML
    private Button btnConjuntos;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCarrito;

    private PantallaInicialPresenter presenter;


    public void initialize() {
        presenter = new PantallaInicialPresenter(this);
        btnTennis.setOnAction(event -> App.switchScene("ViewTennis.fxml"));
        btnElementos.setOnAction(event -> App.switchScene("ViewElementos.fxml") );
        btnConjuntos.setOnAction(event -> App.switchScene("ViewConjuntos.fxml") );
        btnSuplementos.setOnAction(event -> App.switchScene("ViewSuplementos.fxml") );
        btnCarrito.setOnAction(event -> App.switchScene("Carrito.fxml"));
        btnBack.setOnAction(event ->{ presenter.vaciarCarrito();
        App.switchScene("Login.fxml");});

    }

}
