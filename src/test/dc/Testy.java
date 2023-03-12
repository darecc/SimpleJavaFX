package dc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Testy {
    @Test
    @DisplayName("Test metody toString")
    public void test1() {
        OsobaFx osoba = new OsobaFx("Jan", "Nowak", "1111", "aa@aaa.pl");
        System.out.println(osoba.toString());
        assertEquals("Jan Nowak (1111)", osoba.toString());
    }
}
