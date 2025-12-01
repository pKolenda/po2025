package org.example.samochodgui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewCarController {

    @FXML private TextField inputModel;
    @FXML private TextField inputRejestracja;
    @FXML private TextField inputSilnikProducent;
    @FXML private TextField inputSilnikWaga;
    @FXML private TextField inputSilnikCena;

    private Stage dialogStage;
    private boolean isOkClicked = false;

    // Klasa pomocnicza do przenoszenia danych
    public static class CarParams {
        public String model;
        public String rejestracja;
        public String silnikProd;
        public int silnikWaga;
        public int silnikCena;
        public String producent;
        public String skrzyniaProducent;
        public int skrzyniaWaga;
        public String sprzęgłoProducent;
        public int skrzyniaCena;
        public int sprzęgłoWaga;
        public int sprzęgłoCena;
    }

    private CarParams carParams = new CarParams();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    public CarParams getCarParams() {
        return carParams;
    }

    @FXML
    private void handleSave() {
        if (isInputValid()) {
            carParams.model = inputModel.getText();
            carParams.rejestracja = inputRejestracja.getText();
            carParams.silnikProd = inputSilnikProducent.getText();

            // Konwersja tekstów na liczby całkowite
            try {
                carParams.silnikWaga = Integer.parseInt(inputSilnikWaga.getText());
                carParams.silnikCena = Integer.parseInt(inputSilnikCena.getText());
            } catch (NumberFormatException e) {
                // Tutaj powinien być kod wyświetlający błąd na ekranie, np. Alert
                System.err.println("Błąd: Waga i Cena muszą być liczbami!");
                return;
            }

            isOkClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        // Bardzo prosta walidacja: sprawdzamy, czy wszystkie pola tekstowe nie są puste
        if (inputModel.getText() == null || inputModel.getText().isEmpty() ||
                inputRejestracja.getText() == null || inputRejestracja.getText().isEmpty() ||
                inputSilnikProducent.getText() == null || inputSilnikProducent.getText().isEmpty() ||
                inputSilnikWaga.getText() == null || inputSilnikWaga.getText().isEmpty() ||
                inputSilnikCena.getText() == null || inputSilnikCena.getText().isEmpty())
        {
            // Tutaj powinien być kod wyświetlający błąd walidacji
            System.out.println("Błąd: Wypełnij wszystkie pola!");
            return false;
        }
        return true;
    }
}