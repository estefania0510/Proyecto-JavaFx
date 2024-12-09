module co.edu.uptc {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.graphics;


    exports co.edu.uptc.model to co.edu.uptc;
    opens co.edu.uptc.model to com.google.gson; 
    opens co.edu.uptc.view to javafx.fxml;
    opens co.edu.uptc to javafx.fxml;
    exports co.edu.uptc;
}
