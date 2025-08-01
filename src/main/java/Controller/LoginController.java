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
        System.out.println("LOL: " + charLimit(username));

        if(!charLimit(username)){
            System.out.println("CHAR MAX MIN");
            Alert invalid = new Alert(Alert.AlertType.WARNING);
            invalid.setTitle("Character Limit");
            invalid.setHeaderText(null);
            invalid.setContentText("Minimal of 4 Characters and Max of 16");
            invalid.showAndWait();
            return;
        }
        //a

        if (DB.checkUser(username)){
            matchPassword();
            System.out.println("cool");
        }else {
            Alert invalid = new Alert(Alert.AlertType.WARNING);
            invalid.setTitle("Error logging in");
            invalid.setHeaderText(null);
            invalid.setContentText("Invalid Username or Password");
            invalid.showAndWait();
        }

    }

    public void matchPassword(){
        String username = userInput.getText();
        String pass = passwordInput.getText();
        if(DB.matchPassword(username, pass)){
            Alert valid = new Alert(Alert.AlertType.WARNING);
            valid.setTitle("Logged in");
            valid.setHeaderText(null);
            valid.setContentText("Logged in");
            valid.showAndWait();
        }else {
            Alert invalid = new Alert(Alert.AlertType.WARNING);
            invalid.setTitle("Error logging in");
            invalid.setHeaderText(null);
            invalid.setContentText("Invalid Username or Password");
            invalid.showAndWait();
        }
    }

}
