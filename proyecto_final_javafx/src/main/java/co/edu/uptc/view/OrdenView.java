package co.edu.uptc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import co.edu.uptc.App;
import co.edu.uptc.model.Orden;
import co.edu.uptc.presenter.OrdenPresenter;

public class OrdenView {
    @FXML
    private ListView<Orden> listView;

    @FXML
    private Button btnBack;

    private OrdenPresenter presenter;

    public void initialize() {
        presenter = new OrdenPresenter(this);

        btnBack.setOnAction(event -> App.switchScene("Admin.fxml"));

        presenter.loadOrdenes();
    }

    
    public void updateListView(java.util.List<Orden> ordenes) {
        listView.getItems().setAll(ordenes);
    }
}