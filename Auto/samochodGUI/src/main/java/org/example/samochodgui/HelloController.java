package org.example.samochodgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import symulator.Samochod;
import symulator.Silnik;
import symulator.SkrzyniaBiegow;
import symulator.Sprzeglo;
import symulator.Pozycja;

public class HelloController {

    private Samochod currentCar;

    @FXML private TextField modelTextField;
    @FXML private TextField nrRejestracyjnyTextField;
    @FXML private TextField wagaSamochoduTextField;
    @FXML private TextField predkoscTextField;

    @FXML private TextField sbProducentTextField;
    @FXML private TextField sbCenaTextField;
    @FXML private TextField sbWagaTextField;
    @FXML private TextField sbAktBiegTextField;

    @FXML private TextField sProducentTextField;
    @FXML private TextField sCenaTextField;
    @FXML private TextField sWagaTextField;
    @FXML private TextField sObrotyTextField;

    @FXML private TextField spProducentTextField;
    @FXML private TextField spCenaTextField;
    @FXML private TextField spWagaTextField;
    @FXML private TextField spStanTextField;


    @FXML
    public void initialize() {

        Sprzeglo sprzeglo = new Sprzeglo("Luk", 5, 200);

        Silnik silnik = new Silnik("BMW", 150, 8000);

        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("ZF", 40, 5000, sprzeglo);

        Pozycja pozycja = new Pozycja();

        currentCar = new Samochod(silnik, skrzynia, pozycja);

        modelTextField.setText("Sedan X");
        nrRejestracyjnyTextField.setText("KR12345");

        updateGuiStatus();
    }


    public void handleStartSamochod(ActionEvent event) {
        if (currentCar != null) {
            currentCar.start();
            System.out.println("Samochód: Włączono silnik i ustawiono 1. bieg.");
            updateGuiStatus();
        }
    }

    public void handleStopSamochod(ActionEvent event) {
        if (currentCar != null) {
            currentCar.stop();
            System.out.println("Samochód: Wyłączono silnik i ustawiono 0. bieg.");
            updateGuiStatus();
        }
    }

    public void handleZwiekszBieg(ActionEvent event) {
        if (currentCar != null) {
            currentCar.skrzynia.zwiekszBieg();
            System.out.println("Skrzynia: Zwiększono bieg.");
            updateGuiStatus();
        }
    }

    public void handleZmniejszBieg(ActionEvent event) {
        if (currentCar != null) {
            currentCar.skrzynia.zmniejszBieg();
            System.out.println("Skrzynia: Zmniejszono bieg.");
            updateGuiStatus();
        }
    }

    public void handleObrotyUp(ActionEvent event) {
        if (currentCar != null) {
            currentCar.silnik.up();
            System.out.println("Silnik: Dodano gazu (obroty +1000).");
            updateGuiStatus();
        }
    }

    public void handleObrotyDown(ActionEvent event) {
        if (currentCar != null) {
            currentCar.silnik.down();
            System.out.println("Silnik: Ujęto gazu (obroty -1000).");
            updateGuiStatus();
        }
    }

    public void handleWcisnijSprzeglo(ActionEvent event) {
        if (currentCar != null) {
            currentCar.skrzynia.sprzeglo.wcisnij();
            System.out.println("Sprzęgło: Wciśnięte.");
            updateGuiStatus();
        }
    }

    public void handleZwolnijSprzeglo(ActionEvent event) {
        if (currentCar != null) {
            currentCar.skrzynia.sprzeglo.zwolnij();
            System.out.println("Sprzęgło: Zwolnione.");
            updateGuiStatus();
        }
    }

    private void updateGuiStatus() {
        if (currentCar == null) {
            return;
        }

        int totalWaga = currentCar.silnik.waga + currentCar.skrzynia.waga + currentCar.skrzynia.sprzeglo.waga;
        wagaSamochoduTextField.setText(String.valueOf(totalWaga));
        predkoscTextField.setText("0");


        sObrotyTextField.setText(String.valueOf(currentCar.silnik.getObroty()));
        sProducentTextField.setText(currentCar.silnik.producent);
        sCenaTextField.setText(String.valueOf(currentCar.silnik.cena));
        sWagaTextField.setText(String.valueOf(currentCar.silnik.waga));


        sbAktBiegTextField.setText(String.valueOf(currentCar.skrzynia.getAktBieg()));
        sbProducentTextField.setText(currentCar.skrzynia.producent);
        sbCenaTextField.setText(String.valueOf(currentCar.skrzynia.cena));
        sbWagaTextField.setText(String.valueOf(currentCar.skrzynia.waga));

        spStanTextField.setText(currentCar.skrzynia.sprzeglo.getStan() ? "WCIŚNIĘTE" : "Zwolnione");
        spProducentTextField.setText(currentCar.skrzynia.sprzeglo.producent);
        spCenaTextField.setText(String.valueOf(currentCar.skrzynia.sprzeglo.cena));
        spWagaTextField.setText(String.valueOf(currentCar.skrzynia.sprzeglo.waga));
    }
}