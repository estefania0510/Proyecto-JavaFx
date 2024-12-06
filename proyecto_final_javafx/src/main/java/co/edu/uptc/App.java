package co.edu.uptc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * JavaFX App
 */

 public class App extends Application {

    private static Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        switchScene("Main.fxml");
    }

    public static void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/" + fxmlFile));
            Scene scene = new Scene(loader.load());
            mainStage.setScene(scene);         
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


