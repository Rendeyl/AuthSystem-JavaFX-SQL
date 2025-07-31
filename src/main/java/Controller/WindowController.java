package Controller;

import com.mysql.cj.x.protobuf.Mysqlx;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class WindowController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void back(ActionEvent e) throws IOException {

        Alert log = new Alert(Alert.AlertType.CONFIRMATION);
        log.setTitle("Log-out");
        log.setHeaderText("Are you sure you want to log-out?");
        log.setContentText("Confirm to log-out");

        Optional<ButtonType> result = log.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            root = FXMLLoader.load(getClass().getResource("/Scenes/Main.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

}
