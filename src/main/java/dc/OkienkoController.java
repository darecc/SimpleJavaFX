package dc;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button ;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OkienkoController implements Initializable {
    String napis;
    @FXML
    Button button;
    @FXML
    TextField tNapis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        napis = "Ala ma kota";
       showMessage(napis);
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Komunikat");
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK");
            }
        });
    }

    public void setData(String s) {
        napis = s;
    }

    public String getData() {
        return napis;
    }

    @FXML
    public void onButton(ActionEvent ev) {
        napis = tNapis.getText();
        //region Zamknięcie okna (tak naprawdę to zamknięcie 'stage')
        Node  source = (Node)  ev.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
