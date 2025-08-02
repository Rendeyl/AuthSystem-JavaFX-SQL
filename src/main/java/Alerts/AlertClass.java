package Alerts;

import javafx.scene.control.Alert;

public class AlertClass {

    public void AlertTemp(Alert.AlertType type, String title, String header, String context){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
    }

    public void AlertConfirm(Alert.AlertType type, String title, String header, String context){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
    }

}
