package dc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OsobaFxTest {
    OsobaFx osoba;

    @BeforeEach
    void setUp() {
        osoba = new OsobaFx("Jan", "Nowak", "505404303", "jan@wp.pl");
    }

    @Test
    void setTelefon() {
        osoba.setTelefon("404404404");
        assertEquals("404404404", osoba.getTelefon());
    }

    @Test
    void testToString() {
        assertEquals("Jan Nowak (505404303)", osoba.toString());
    }

    @Test
    void setEmail() {
        osoba.setEmail("jan.nowak.wp.pl");
        assertEquals("jan.nowak.wp.pl", osoba.getEmail());
    }
}