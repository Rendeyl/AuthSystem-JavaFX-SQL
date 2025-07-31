package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

//Database
import Database.DB;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void Login(ActionEvent e) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/Scenes/LoginWin.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private Label myText;

    public void Test(){
        String temp = DB.deyl(3);
        myText.setText(temp);
    }

}
