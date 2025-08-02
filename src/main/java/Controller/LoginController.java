package Controller;

import Database.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import Alerts.AlertClass;

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void back(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Scenes/Main.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public boolean charLimit(String input){

        int len = input.length();
        if(len < 4 || len > 16) return false;
        else return true;

    }

    @FXML
    private TextField userInput;

    @FXML
    private PasswordField passwordInput;

    private int userID;

    public void checkForUser(ActionEvent e){
        //search for user
        String username = userInput.getText();

        if(!charLimit(username)){
            AlertClass alert = new AlertClass();
            alert.AlertTemp(Alert.AlertType.WARNING, "Character Limit", null, "Minimal of 4 Characters and Max of 16");
            return;
        }

        if (DB.checkUser(username)){
            matchPassword();
        }else {
            AlertClass alert = new AlertClass();
            alert.AlertTemp(Alert.AlertType.WARNING, "Error Logging in", null, "Invalid Username or Password");
        }

    }

    public void matchPassword(){
        String username = userInput.getText();
        String pass = passwordInput.getText();
        if(DB.matchPassword(username, pass)){
            AlertClass alert = new AlertClass();
            alert.AlertTemp(Alert.AlertType.WARNING, "Logged in", null, "Logged in");
        }else {
            AlertClass alert = new AlertClass();
            alert.AlertTemp(Alert.AlertType.WARNING, "Error Logging in", null, "Invalid Username or Password");
        }
    }

}
