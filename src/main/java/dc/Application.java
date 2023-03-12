package dc;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("okno.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 500);
        stage.setTitle("Aplikacja JavaFX - testowa 2023!");
        stage.setScene(scene);
        stage.getIcons().add(new Image(dc.Application.class.getResourceAsStream("images/napkin.png")));
        //stage.getIcons().add(new Image(dc.Application.class.getResourceAsStream("images/hypseus-logo.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}