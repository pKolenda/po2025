package org.example.samochodgui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import symulator.Samochod;
import symulator.Silnik;
import symulator.SkrzyniaBiegow;
import symulator.Sprzeglo;
import symulator.Pozycja;

public class NewCarController {

    // Pola samochodu
    @FXML private TextField inputModel;
    @FXML private TextField inputRejestracja;
    @FXML private TextField inputwaga; // Waga nadwozia (bazowa)

    // Pola silnika
    @FXML private TextField inputSilnikProducent;
    @FXML private TextField inputSilnikWaga;
    @FXML private TextField inputSilnikCena;

    // Pola skrzyni
    @FXML private TextField inputSkrzyniaProducent;
    @FXML private TextField inputSkrzyniaWaga;
    @FXML private TextField inputSkrzyniaCena;

    // Pola sprzęgła
    @FXML private TextField inputSprzegloProducent;
    @FXML private TextField inputSprzegloWaga;
    @FXML private TextField inputSprzegloCena;

    private Stage dialogStage;
    private boolean isOkClicked = false;

    // Pole przechowujące gotowy, zbudowany obiekt Samochod
    private Samochod newCar;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    // Metoda udostępniająca gotowy obiekt głównemu kontrolerowi
    public Samochod getNewCar() {
        return newCar;
    }

    @FXML
    private void handleSave() {
        if (isInputValid()) {
            try {
                // 1. Tworzenie Sprzęgła (dane prosto z pól tekstowych)
                Sprzeglo sprzeglo = new Sprzeglo(
                        inputSprzegloProducent.getText(),
                        Integer.parseInt(inputSprzegloWaga.getText()),
                        Integer.parseInt(inputSprzegloCena.getText())
                );

                // 2. Tworzenie Silnika
                Silnik silnik = new Silnik(
                        inputSilnikProducent.getText(),
                        Integer.parseInt(inputSilnikWaga.getText()),
                        Integer.parseInt(inputSilnikCena.getText())
                );

                // 3. Tworzenie Skrzyni Biegów (wymaga obiektu sprzęgła)
                SkrzyniaBiegow skrzynia = new SkrzyniaBiegow(
                        inputSkrzyniaProducent.getText(),
                        Integer.parseInt(inputSkrzyniaWaga.getText()),
                        Integer.parseInt(inputSkrzyniaCena.getText()),
                        sprzeglo
                );

                // 4. Tworzenie Pozycji
                Pozycja pozycja = new Pozycja();

                // 5. Tworzenie głównego obiektu Samochod
                // Używamy konstruktora, który przyjmuje podzespoły
                newCar = new Samochod(silnik, skrzynia, pozycja);

                // Uzupełnienie danych specyficznych dla samochodu (Model, Rejestracja, Waga Bazowa)
                // Wymaga setterów lub publicznych pól w klasie Samochod
                newCar.setModel(inputModel.getText());
                newCar.setNrRejestracyjny(inputRejestracja.getText());
                newCar.setWaga(Integer.parseInt(inputwaga.getText()));

                isOkClicked = true;
                dialogStage.close();

            } catch (NumberFormatException e) {
                System.err.println("Błąd formatu liczby: " + e.getMessage());
                // Tutaj można dodać Alert dla użytkownika
            }
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        // Prosta walidacja czy kluczowe pola nie są puste
        // Warto rozszerzyć o wszystkie pola
        return !inputModel.getText().isEmpty() &&
                !inputwaga.getText().isEmpty() &&
                !inputSilnikWaga.getText().isEmpty();
    }
}