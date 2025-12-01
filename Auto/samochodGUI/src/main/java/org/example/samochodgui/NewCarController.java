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
    @FXML private TextField inputSkrzyniaProducent;
    @FXML private TextField inputSkrzyniaWaga;
    @FXML private TextField inputSkrzyniaCena;

    @FXML private TextField inputSprzegloProducent;
    @FXML private TextField inputSprzegloWaga;
    @FXML private TextField inputSprzegloCena;
    @FXML private TextField inputwaga;

    private Stage dialogStage;
    private boolean isOkClicked = false;

    public static class CarParams {
        public String model;
        public String rejestracja;
        public String silnikProd;
        public int silnikWaga;
        public int silnikCena;
        public String skrzyniaProducent;
        public int skrzyniaWaga;
        public int skrzyniaCena;
        public String sprzegloProducent;
        public int sprzegloWaga;
        public int sprzegloCena;
        public int wagaSamochodu;
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

        try {
            if (isInputValid()) {

                carParams.model = inputModel.getText();
                carParams.rejestracja = inputRejestracja.getText();
                carParams.wagaSamochodu = Integer.parseInt(inputwaga.getText());

                carParams.silnikProd = inputSilnikProducent.getText();
                carParams.silnikWaga = Integer.parseInt(inputSilnikWaga.getText());
                carParams.silnikCena = Integer.parseInt(inputSilnikCena.getText());

                carParams.skrzyniaProducent = inputSkrzyniaProducent.getText();
                carParams.skrzyniaWaga = Integer.parseInt(inputSkrzyniaWaga.getText());
                carParams.skrzyniaCena = Integer.parseInt(inputSkrzyniaCena.getText());

                carParams.sprzegloProducent = inputSprzegloProducent.getText();
                carParams.sprzegloWaga = Integer.parseInt(inputSprzegloWaga.getText());
                carParams.sprzegloCena = Integer.parseInt(inputSprzegloCena.getText());


                isOkClicked = true;
                dialogStage.close();
            }
        } catch (NumberFormatException e) {
            System.err.println("Błąd: Wprowadzone Wagi/Ceny nie są liczbami całkowitymi.");
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        if (inputModel.getText() == null || inputModel.getText().isEmpty() ||
                inputRejestracja.getText() == null || inputRejestracja.getText().isEmpty() ||
                inputwaga.getText() == null || inputwaga.getText().isEmpty() ||
                inputSilnikProducent.getText() == null || inputSilnikProducent.getText().isEmpty() ||
                inputSilnikWaga.getText() == null || inputSilnikWaga.getText().isEmpty() ||
                inputSilnikCena.getText() == null || inputSilnikCena.getText().isEmpty() ||
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