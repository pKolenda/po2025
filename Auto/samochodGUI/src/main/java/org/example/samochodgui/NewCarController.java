package org.example.samochodgui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import symulator.Samochod;
import symulator.Silnik;
import symulator.SkrzyniaBiegow;
import symulator.Sprzeglo;
import symulator.Pozycja;

public class NewCarController {

    @FXML private TextField inputModel;
    @FXML private TextField inputRejestracja;
    @FXML private ComboBox<String> inputSilnikProducent;
    @FXML private TextField inputSilnikWaga;
    @FXML private TextField inputSilnikCena;
    @FXML private TextField inputSkrzyniaProducent;
    @FXML private TextField inputSkrzyniaWaga;
    @FXML private TextField inputSkrzyniaCena;
    @FXML private TextField inputSprzegloProducent;
    @FXML private TextField inputSprzegloWaga;
    @FXML private TextField inputSprzegloCena;
    private Samochod nowySamochod;

    private Stage dialogStage;
    private boolean isOkClicked = false;

    public void init(){
            inputSilnikProducent.getItems().addAll("BWM", "Audi", "FIAT");
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    public Samochod getNewCar() {
        return nowySamochod;
    }


    @FXML
    private void handleSave() {
        String silnikProducent = inputSilnikProducent.getValue();

        try {
            if (isInputValid()) {

                int silnikWaga = Integer.parseInt(inputSilnikWaga.getText());
                int silnikCena = Integer.parseInt(inputSilnikCena.getText());
                int skrzyniaWaga = Integer.parseInt(inputSkrzyniaWaga.getText());
                int skrzyniaCena = Integer.parseInt(inputSkrzyniaCena.getText());
                int sprzegloWaga = Integer.parseInt(inputSprzegloWaga.getText());
                int sprzegloCena = Integer.parseInt(inputSprzegloCena.getText());

                Sprzeglo sprzeglo = new Sprzeglo(
                        inputSprzegloProducent.getText(),
                        sprzegloWaga,
                        sprzegloCena
                );

                Silnik silnik = new Silnik(
                        silnikProducent,
                        silnikWaga,
                        silnikCena
                );

                SkrzyniaBiegow skrzynia = new SkrzyniaBiegow(
                        inputSkrzyniaProducent.getText(),
                        skrzyniaWaga,
                        skrzyniaCena,
                        sprzeglo
                );

                Pozycja pozycja = new Pozycja();

                Samochod samochod = new Samochod(silnik, skrzynia, pozycja);

                samochod.setModel(inputModel.getText());
                samochod.setNrRejestracyjny(inputRejestracja.getText());

                nowySamochod = samochod;

                isOkClicked = true;
                dialogStage.close();
            }
        } catch (NumberFormatException e) {
            System.err.println("Błąd: Wprowadzone Wagi/Ceny nie są liczbami całkowitymi. " + e.getMessage());
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        if (inputModel.getText() == null || inputModel.getText().isEmpty() ||
                inputRejestracja.getText() == null || inputRejestracja.getText().isEmpty() ||
                inputSilnikWaga.getText() == null || inputSilnikWaga.getText().isEmpty() ||
                inputSilnikCena.getText() == null || inputSilnikCena.getText().isEmpty() ||
                inputSilnikProducent.getSelectionModel() == null || inputSilnikProducent.getSelectionModel().isEmpty() ||
                inputSkrzyniaProducent.getText() == null || inputSkrzyniaProducent.getText().isEmpty() ||
                inputSkrzyniaWaga.getText() == null || inputSkrzyniaWaga.getText().isEmpty() ||
                inputSkrzyniaCena.getText() == null || inputSkrzyniaCena.getText().isEmpty() ||
                inputSprzegloProducent.getText() == null || inputSprzegloProducent.getText().isEmpty() ||
                inputSprzegloWaga.getText() == null || inputSprzegloWaga.getText().isEmpty() ||
                inputSprzegloCena.getText() == null || inputSprzegloCena.getText().isEmpty()
        )
        {
            System.out.println("Błąd: Wypełnij wszystkie pola!");
            return false;
        }
        return true;
    }
}