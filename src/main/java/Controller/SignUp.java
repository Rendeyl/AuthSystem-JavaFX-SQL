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

import java.io.IOException;

import Database.DB;

public class SignUp {

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
        if(DB.checkIfUserAlreadyExist(username)){
            Alert invalid = new Alert(Alert.AlertType.WARNING);
            invalid.setTitle("Username Error");
            invalid.setHeaderText(null);
            invalid.setContentText("Username Already Exist");
            invalid.showAndWait();
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
        if(password.equals(confirm_Password)){
            DB.addAccount(username, password);
        }else {
            Alert invalid = new Alert(Alert.AlertType.WARNING);
            invalid.setTitle("Password Error");
            invalid.setHeaderText(null);
            invalid.setContentText("Password Does Not Match");
            invalid.showAndWait();
        }
    }

}
