package dc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class OknoController implements Initializable {
    private final String FILENAME = "osoby.txt";
    @FXML
    Button bLogin, bDodaj, bWczytaj,bZapisz;;
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
    Tab tabTabele, tabWykresy;
    @FXML
    ImageView image;
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
        tabWykresy.setDisable(true);
        cImie.setCellValueFactory(new PropertyValueFactory<OsobaFx, String>("Imie"));
        cNazwisko.setCellValueFactory(new PropertyValueFactory<OsobaFx, String>("Nazwisko"));
        cTelefon.setCellValueFactory(new PropertyValueFactory<OsobaFx, String>("Telefon"));
        cEmail.setCellValueFactory(new PropertyValueFactory<OsobaFx, String>("Email"));
        //region stylizacja kontrolek
        String styl1 = "-fx-background-color: #D7BCC9; -fx-color: #EE7C00; -fx-margin: 10px;";
        String styl2 = "-fx-background-color: #D78CA9; -fx-color: #33ACBB; -fx-margin: 10px;";
        String styl3 = "-fx-background-color: #3c7fb1,\n" +
                "        linear-gradient(#fafdfe, #e8f5fc),\n" +
                "        linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);\n" +
                "    -fx-background-insets: 0,1,2;\n" +
                "    -fx-background-radius: 3,2,1;\n" +
                "    -fx-padding: 3 20 3 20;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-font-size: 14px;";
        //endregion
        bDodaj.setStyle(styl3);
        bWczytaj.setStyle(styl3);
        bZapisz.setStyle(styl3);
        tabTabele.setStyle(styl1);
        bDodaj.setTooltip(new Tooltip("Dodanie nowej osoby do tabeli znajomych"));
        bZapisz.setTooltip(new Tooltip("Zapisanie listy znajomych do pliku"));
    }

    @FXML
    private void doLogin(ActionEvent actionEvent) {
        String login = tLogin.getText();
        String passw = tPassword.getText();
        if (login.equals("Darek") && passw.equals("1234")) {
            tabTabele.setDisable(false);
            tabWykresy.setDisable(false);
        }
    }

    @FXML
    private void doDodaj(ActionEvent ae) {
        String imie = tImie.getText();
        String nazw = tNazwisko.getText();
        String tele = tTelefon.getText();
        String emai = tEmail.getText();
        OsobaFx osoba = new OsobaFx(imie, nazw, tele, emai);
        tabOsoby.getItems().add(osoba);
    }

    /**
     * Zapis danych z tabeli 'tabOsoby' do pliku
     * @param ae
     */
    @FXML
    private void doZapisz(ActionEvent ae) {
        try {
            FileWriter fw = new FileWriter(FILENAME);
            BufferedWriter bw = new BufferedWriter(fw);
            for(OsobaFx osoba : tabOsoby.getItems()) {
                bw.write(osoba.getImie() + ";" + osoba.getNazwisko() + ";" + osoba.getTelefon() + ";" + osoba.getEmail());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void doWczytaj(ActionEvent ae) {
        boolean jest = sprawdzCzyPlikIstnieje(FILENAME);
        if (jest == false) {
            String ret = showMessage("Nie ma pliku: " + FILENAME);
        }
        tabOsoby.getItems().clear(); // oczyszczenie dotychczasowej zawartości tabeli
        try {
            FileReader fr = new FileReader(FILENAME);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                String[] fragments = line.split(";");
                OsobaFx osoba = new OsobaFx(fragments[0],fragments[1],fragments[2],fragments[3]);
                tabOsoby.getItems().add(osoba);
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private boolean sprawdzCzyPlikIstnieje(String fileName) {
        File f = new File(fileName);
        if (f.exists())
            return true;
        else
            return false;
    }

    /**
     * Wyświetlenie komunikatu w okienku alertu
     * @param message
     */
    private String showMessage(String message) {
        AtomicReference<String> msgRet = null;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Komunikat");
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                msgRet.set("OK");
            }
        });
        return "OK";
    }
}