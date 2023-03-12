package dc;

import de.saxsys.javafx.test.JfxRunner;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JfxRunner.class)
class OknoControllerTest {
    OknoController oc;

    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() throws MalformedURLException {
        oc = new OknoController();
        URL url = new URL("file:/D:/workspace/demoFX2023/target/classes/dc/okno.fxml");
        oc.initialize(url, null);
        //oc.tImie = new TextField();
        //oc.tImie.setText("abc");
        //System.out.println(oc.tImie.getText());
    }

    @Test
    void initialize() {
        //assertTrue(oc.tabTabele.isDisable());
        //assertTrue(oc.tabFotki.isDisable());
    }

    @Test
    @DisplayName("Test metody 'add")
    void add() {
        assertEquals(7, oc.add(3,4));
    }

    @Test
    @DisplayName("Test metody 'getHoursAndMinutesAndSeconds' podającej czas w postaci HH:MM:SS")
    void getHoursAndMinutesAndSeconds() {
        LocalDateTime ld = LocalDateTime.of(2023,3,12,12,30,5);
        assertEquals("12:30:05", oc.getHoursAndMinutesAndSeconds(ld));
    }
    @Test
    public void testPolaTekstowego() {
        oc.tImie.setText("Ania");
        assertEquals("Ania", oc.tImie.getText());
    }

    @Test
    public void testLogin() {
        oc.tLogin.setText("Darek");
        oc.tPassword.setText("1234");
        assertTrue(oc.isPasswordValid(oc.tLogin.getText(), oc.tPassword.getText()));
        assertTrue(oc.isPasswordValid("Marek", "2345"));
        //assertTrue(oc.isPasswordValid("Agata", "2345"));
    }
}