package source.alert;

import javafx.scene.control.Alert;

public class AlertHelper {

    public void displayAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
