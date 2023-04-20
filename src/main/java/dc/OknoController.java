package dc;

import com.google.gson.Gson;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class OknoController implements Initializable {
    private final String FILENAME = "osoby.txt";
    private Random rnd;
    final FileChooser fileChooser = new FileChooser();
    @FXML
    Button bLogin = new Button();
    @FXML
    Button bDodaj = new Button();
    @FXML
    Button bWczytaj = new Button();
    @FXML
    Button bZapisz = new Button();
    @FXML
    Button bSelect = new Button();
    @FXML
    Button bFoto = new Button();
    @FXML
    Button bRysuj = new Button();
    @FXML
    Button bList = new Button();
    @FXML
    Button newWindow;
    @FXML
    Button newVideo;
    @FXML
    ListView lista;
    @FXML
    TextField tImie = new TextField();
    @FXML
    TextField tLogin = new TextField();
    @FXML
    TextField tNazwisko, tTelefon, tEmail;
    @FXML
    PasswordField tPassword = new PasswordField();
    @FXML
    AnchorPane anchorPane, paneRysuj;
    @FXML
    Tab tabTabele = new Tab();
    @FXML
    Tab tabFotki = new Tab();
    @FXML
    Tab tabRysuj;
    @FXML
    ImageView imageView;
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
    @FXML
    Slider cOpacity;
    @FXML
    public Group grupa = new Group();
    @FXML
    ContextMenu tableMenu;
    //@FXML
    //MediaView film;
    @FXML
    Button playButton, pauseButton, resetButton;
    @FXML
    MediaView mediaView;
    @FXML
    MediaPlayer mediaPlayer;
    @FXML
    public static final ObservableList data =
            FXCollections.observableArrayList();
    public void initialize(URL location, ResourceBundle resources) {
        tabTabele.setDisable(true);
        tabFotki.setDisable(true);
        cImie.setCellValueFactory(new PropertyValueFactory<OsobaFx, String>("Imie"));
        cNazwisko.setCellValueFactory(new PropertyValueFactory<OsobaFx, String>("Nazwisko"));
        cTelefon.setCellValueFactory(new PropertyValueFactory<OsobaFx, String>("Telefon"));
        cEmail.setCellValueFactory(new PropertyValueFactory<OsobaFx, String>("Email"));
        //region stylizacja kontrolek
        String styl1 = "-fx-background-color: #D7BCC9; -fx-color: #EE7C00; -fx-margin: 10px;";
        String styl2 = "-fx-background-color: #D78CA9; -fx-color: #33ACBB; -fx-margin: 10px;";
        String styl3 = "-fx-background-color: #3c0fb1,\n" +
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
        bRysuj.setStyle(styl3);
        bDodaj.setTooltip(new Tooltip("Dodanie nowej osoby do tabeli znajomych"));
        bZapisz.setTooltip(new Tooltip("Zapisanie listy znajomych do pliku"));
        bWczytaj.setTooltip(new Tooltip("Wczytanie listy znajomych z pliku"));
        bSelect.setTooltip(new Tooltip("Dialog umożliwiający wybór pliku graficznego"));
        bRysuj.setTooltip(new Tooltip("Rysowanie magicznych kółek ;)"));
        bSelect.setStyle("-fx-border-color:Gold;-fx-border-width:2px;");
        bFoto.setStyle("-fx-border-color:Gold;-fx-border-width:2px;");
        bLogin.setDefaultButton(true); // do momentu zalogowania przycisk 'bLogin' jest domyślnym przyciskiem
        //region zainicjowanie media playera
        //File file = new File("src/main/resources/dc/video/putin.mp4");
        URL url = null;
        try {
            //url = new URL("https://youtube.com/shorts/MLobv-aOYrM");
            //url = new URL("https://www.youtube.com/embed/P_tAU3GM9XI?autoplay=1");
            url = new URL("https://assets.codepen.io/6093409/river.mp4");
            //url = new URL("https://download.oracle.com/otndocs/products/javafx/oow2010-2.flv ");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String URI = null;
        try {
            URI = url.toURI().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Media m = new Media(URI);
        mediaPlayer = new MediaPlayer(m);
        playButton.setStyle(styl3);
        pauseButton.setStyle(styl3);
        resetButton.setStyle(styl3);
        //endregion
        //region List view selected item listener
        lista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object old, Object newValue) {
               showMessage("Wybrane: " + observableValue.getValue());
            }
        });
        //endregion
    }

    /**
     * Logowanie do programu odblokowuje pozostałe zakładki
     * @param actionEvent
     */
    @FXML
    private void doLogin(ActionEvent actionEvent) {
        String login = tLogin.getText();
        String passw = tPassword.getText();
        if (isPasswordValid(login, passw) == true) {
            tabTabele.setDisable(false);
            tabFotki.setDisable(false);
            bLogin.setDefaultButton(false);
        }
        else {
            showMessage("Nieprawidłowe dane logowania!");
        }
    }

    public boolean isPasswordValid(String login, String passw) {
        Map<String,String> mapa = readPasswords();
        if (mapa.containsKey(login)) {
            String log = mapa.get(login);
            if (log.equals(passw))
                return true;
            else
                return false;
        }
        else
            return false;
    }
    private Map<String,String> readPasswords() {
        Map<String, String> mapa = new HashMap<>();
        try {
            FileReader fr = new FileReader("src/main/resources/dc/passwords.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                String[] split = line.split(";");
                mapa.put(split[0],split[1]);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return mapa;
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
        Gson js = new Gson();
        if (tabOsoby.getItems().isEmpty()) {
            showMessage("W tabeli nie ma znajomych do zapisania...");
            return;
        }
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
            showMessage("Nie ma pliku: " + FILENAME);
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

    @FXML
    private void onSelectFoto(ActionEvent ae) {
        File file = new File("images/szybowanie.jpg");
        Image im = new Image(file.toURI().toString());
        imageView.setImage(im);
    }

    @FXML
    private void onSelectImage(ActionEvent ae) {
            final String[] pliki = {null};
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG", "*.jpg")
                    ,new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            final Stage dialog = new Stage();
            dialog.setTitle("Wybór pliku");
            dialog.initModality(Modality.APPLICATION_MODAL);
            AnchorPane panel = new AnchorPane();
            String styl = "-fx-background-color: #E7BCC9; -fx-color: EE000C;";
            panel.setStyle(styl);
            Label etykieta = new Label("Wybór pliku do wczytania: ");
            etykieta.setLayoutX(70);
            etykieta.setLayoutY(20);
            Button button1 = new Button("Wybierz");
            button1.setLayoutX(110);
            button1.setLayoutY(50);
            button1.setMinSize(40, 25);
            button1.setOnAction(ev -> {
                File selectedFile = fileChooser.showOpenDialog(dialog);
                if (selectedFile != null) {
                    System.out.println(selectedFile.toString());
                    pliki[0] = selectedFile.toString();
                    dialog.close();
                }
                else
                    System.out.println("Nie nie wybrano");
            });
            panel.getChildren().addAll(etykieta, button1);
            Scene scene1 = new Scene(panel, 300, 250);
            dialog.setScene(scene1);
            dialog.showAndWait();
            dialog.close();
            panel.getChildren().remove(etykieta);
            panel.getChildren().remove(button1);
            // wczytanie pliku do 'imageView'
            String fileN = pliki[0];
            File file = new File(fileN);
            Image im = new Image(file.toURI().toString());
            imageView.setImage(im);
            grupa = new Group();
            paneRysuj.getChildren().add(grupa);
        }

    /**
     * Zmiana przezroczystości
      * @param e
     */
    @FXML
    private void onDrag(Event e) {
        double opacity = cOpacity.getValue() /100;
        System.out.println("Opacity: " + opacity);
        FadeTransition ftrans = new FadeTransition();
        ftrans.setNode(imageView);
        ftrans.setFromValue(opacity);
        ftrans.setToValue(opacity);
        ftrans.play();
    }

    /**
     * Przycisk bRysuj uruchamia malowanie "magicznych" kółek
     * @param e
     */
    @FXML
    private void onRysuj(Event e) {
        paneRysuj.getChildren().remove(grupa);
        grupa = new Group();
        paneRysuj.getChildren().add(grupa);
        rnd = new Random(LocalDateTime.now().getNano());
        namalujKolka(grupa, 300, 180, 86);
    }

    /**
     * Maolwanie kółek
     * @param grupa
     * @param x
     * @param y
     * @param r
     */
    private void namalujKolka(Group grupa, int x, int y, int r) {
        Circle kolko = new Circle(x , y, r, Color.rgb(0 + rnd.nextInt(70),60 + rnd.nextInt(70),10 + rnd.nextInt(70),0.3 + rnd.nextDouble() / 3));
        kolko.setStroke(Color.BLACK);
        grupa.getChildren().add(kolko);

        if (r > 12) {
            namalujKolka(grupa, x + r, y, r / 2);
            namalujKolka(grupa, x - r, y, r / 2);
            namalujKolka(grupa, x - (int)(1.9 * r), y + r, r / 2);
            namalujKolka(grupa, x + (int)(2.0 * r), y - r, r / 2);
        }
    }
    @FXML
    private void removeContact(ActionEvent ae) {
        var contact = tabOsoby.selectionModelProperty().get().getSelectedItem();
        if (contact == null) {
            showMessage("Zaznacz coś!");
            return;
        }
        tabOsoby.getItems().remove(contact);
        showMessage("Usunięto: " + contact.getImie() + "." + contact.getNazwisko());
    }

    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Metoda zwraca godzinę i minuty i sekundy w formacie HH:MM;SS np. 17:05:32, 7:00:05
     * @param ldt
     * @return
     */
    public String getHoursAndMinutesAndSeconds(LocalDateTime ldt) {
        int h = ldt.getHour();
        int m = ldt.getMinute();
        int s = ldt.getSecond();
        String text = "" + h + ":";
        if (m < 10)
            text += "0";
        text += m + ":";
        if (s < 10)
            text += "0";
        text += s;
        return text;
    }

    @FXML
    public void doPlay(ActionEvent ae) {
        mediaPlayer.setVolume(50);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    @FXML
    public void doPause(Event e) {
        mediaPlayer.pause();
    }

    @FXML
    public void doReset(Event ev) {
        mediaPlayer.seek(Duration.seconds(0.0));
        mediaPlayer.stop();
    }

    @FXML
    private void onLista(Event ev) {
        //źródło: https://mamotoja.pl/rodzina/imiona/elfickie-imiona-meskie-zenskie-znaczenie-38056-r1/
       String[] imionaElfow = new String[] {"Aafje", "Alfdís", "Alfiva", "Alruna", "Elladan", "Elrohir", "Aelfraed", "Idril", "Ithil", "Mithrandir", "Ingálvur", "Noralf", "Joralf", "Aveley", "Gunnalf"};
       int i = 0;
       for (String st : imionaElfow) {
           data.add(i++, st);
       }
       lista.setItems(data);
    }

    @FXML
    private void onNewVideo(Event ev) {
        File file = new File("src/main/resources/dc/video/putin.mp4");
        URL url = null;
        try {
            //url = new URL("https://www.youtube.com/embed/P_tAU3GM9XI?autoplay=1");
            url = new URL(file.toURI().toString());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        String URI = null;
        try {
            URI = url.toURI().toString();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        Media m = new Media(URI);
        mediaPlayer = new MediaPlayer(m);
    }

    /**
     * Okienko z przekazaniem danych (patrz: https://dev.to/devtony101/javafx-3-ways-of-passing-information-between-scenes-1bm8, oraz: https://www.tabnine.com/code/java/methods/javafx.fxml.FXMLLoader/setLocation
     * Jakoś to połączyłem ;)
     * @param ev
     */
    @FXML
    public void onNewWindow(Event ev) {
        try {
            Node node = (Node) ev.getSource();
            // Step 3
            //Stage stage = (Stage) node.getScene().getWindow();
            //stage.close();
            // Step 4
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("okienko.fxml"));
            // Step 5
            //stage.setUserData("ala");
            OkienkoController oc = new OkienkoController();
            loader.setController(oc);
            oc.setData("Ala ma psa");
            oc = loader.getController();
            loader.setLocation(Application.class.getResource("okienko.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            String napis = oc.getData();
            showMessage("Napis: " + napis);
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }
}