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
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import Alerts.AlertClass;

import java.io.IOException;

import Database.DB;

public class SignUp {

    public boolean charLimit(String input){

        int len = input.length();
        if(len < 4 || len > 16) return false;
        else return true;

    }

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

    @FXML
    private TextField userInput;

    public void signup(ActionEvent e){
        //check for usernames that already exist
        String username = userInput.getText();
        int len = username.length();

        if(!charLimit(username)){
            AlertClass alert = new AlertClass();
            alert.AlertTemp(Alert.AlertType.WARNING, "Charcter Limit", null, "Minimal of 4 Characters and Max of 16");
            return;
        }

        if(DB.checkIfUserAlreadyExist(username)){
            AlertClass alert = new AlertClass();
            alert.AlertTemp(Alert.AlertType.WARNING, "Username Error", null, "Username Already Exist");
        }else {
            passwordConfirmation();
        }
    }

    @FXML
    private PasswordField passwordInput;

    @FXML
    private PasswordField confirmPassword;

    public void passwordConfirmation(){
        String username = userInput.getText();
        String password = passwordInput.getText();
        String confirm_Password = confirmPassword.getText();

        if(!charLimit(password)){
            AlertClass alert = new AlertClass();
            alert.AlertTemp(Alert.AlertType.WARNING, "Charcter Limit", null, "Minimal of 4 Characters and Max of 16");
            return;
        }

        if(password.equals(confirm_Password)){
            DB.addAccount(username, password);
            AlertClass alert = new AlertClass();
            alert.AlertTemp(Alert.AlertType.WARNING, "Account Created", null, "Account Succesfully Created");
        }else {
            AlertClass alert = new AlertClass();
            alert.AlertTemp(Alert.AlertType.WARNING, "Password Error", null, "Password Does Not Match");
        }
    }

}
