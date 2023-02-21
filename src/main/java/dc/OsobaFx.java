package dc;

import javafx.beans.property.SimpleStringProperty;

public class OsobaFx {
        public SimpleStringProperty imie;
        public SimpleStringProperty nazwisko;
        public SimpleStringProperty telefon;
        public SimpleStringProperty email;

        public OsobaFx(String imie, String nazwisko, String telefon, String email) {
            this.imie = new SimpleStringProperty(imie);
            this.nazwisko = new SimpleStringProperty(nazwisko);
            this.telefon = new SimpleStringProperty(telefon);
            this.email = new SimpleStringProperty(email);
        }

        public OsobaFx getAccount() {
            return this;
        }
        public String getImie() {
            return imie.get();
        }
        public void setImie(String fName) {
            imie.set(fName);
        }

        public String getNazwisko() {
            return nazwisko.get();
        }
        public void setNazwisko(String fName) {
            nazwisko.set(fName);
        }

        public String getTelefon() {
            return telefon.get();
        }
        public void setTelefon(String telefon) { this.telefon.set(telefon); }
        @Override
        public String toString() {
            return imie + " " + nazwisko + " (" + telefon + ")";
        }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}

