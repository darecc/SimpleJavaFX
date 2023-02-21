package dc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import dc.OsobaFx.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OknoController implements Initializable {
    @FXML
    Button login;
    @FXML
    TextField tLogin;
    @FXML
    TextField tImie;
    @FXML
    TextField tNazwisko;
    @FXML
    TextField tTelefon;
    @FXML
    TextField tEmail;
    @FXML
    PasswordField tPassword;
    @FXML
    AnchorPane anchorPane;
    @FXML
    Tab tabTabele;
    @FXML
    ImageView image;
    @FXML
    Button bDodaj;
    @FXML
    TableView<OsobaFx> tabOsoby;
    @FXML
    TableColumn cImie = new TableColumn();
    @FXML
    TableColumn cNazwisko = new TableColumn();
    @FXML
    TableColumn cTelefon = new TableColumn();
    @FXML
    TableColumn cEmail = new TableColumn();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabTabele.setDisable(true);
        cImie.setCellValueFactory(new PropertyValueFactory<OsobaFx, String>("Imie"));
        cNazwisko.setCellValueFactory(new PropertyValueFactory<OsobaFx, String>("Nazwisko"));
        cTelefon.setCellValueFactory(new PropertyValueFactory<OsobaFx, String>("Telefon"));
        //cEmail.setCellValueFactory(new PropertyValueFactory<OsobaFx, String>("Email"));
    }

    @FXML
    private void doLogin(ActionEvent actionEvent) {
        String login = tLogin.getText();
        String passw = tPassword.getText();
        if (login.equals("Darek") && passw.equals("1234")) {
            tabTabele.setDisable(false);
        }
    }

    @FXML
    private void DodajDoTabeli(ActionEvent ae) {
        String i = tImie.getText();
        String n = tNazwisko.getText();
        String t = tTelefon.getText();
        String e = tEmail.getText();
        //OsobaFx osoba = new OsobaFx(i, n, t, e);
        OsobaFx osoba = new OsobaFx(i, n, t);
        tabOsoby.getItems().add(osoba);
    }
}