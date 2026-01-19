package org.example.samochodgui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import symulator.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

import java.io.IOException;


public class HelloController implements Listener{

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

    @FXML private TextField pozycjaX;
    @FXML private TextField pozycjaY;

    @FXML private AnchorPane mapa;
    @FXML private ImageView carIcon;

    @FXML
    public void initialize() {
        carComboBox.setItems(FXCollections.observableArrayList());
        carComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Samochod selected = carList.stream()
                        .filter(car -> (car.getModel() + " (" + car.getNrRejestracyjny() + ")").equals(newValue))
                        .findFirst()
                        .orElse(null);

                if (selected != null) {
                    currentCar = selected;
                    updateGuiStatus();
                    System.out.println("Wybrano samochód: " + selected.getModel());
                }
            }
        });
        mapa.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            Pozycja nowaPozycja = new Pozycja(x, y);
            currentCar.Go(nowaPozycja);
        });
    }


    @FXML
    public void handleInitializeSamochod() {

        Sprzeglo sprzeglo = new Sprzeglo("Luk", 5, 200);
        Silnik silnik = new Silnik("BMW", 150, 8000);
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("ZF", 40, 5000, sprzeglo);
        Pozycja pozycja = new Pozycja();

        Samochod defaultCar = new Samochod(silnik, skrzynia, pozycja);
        defaultCar.setModel("Sedan X");
        defaultCar.setNrRejestracyjny("KR12345");

        carList.add(defaultCar);
        carComboBox.getItems().add(defaultCar.getModel() + " (" + defaultCar.getNrRejestracyjny() + ")");
        carComboBox.getSelectionModel().select(carList.size() - 1);

        modelTextField.setText(defaultCar.getModel());
        nrRejestracyjnyTextField.setText(defaultCar.getNrRejestracyjny());

        updateGuiStatus();
        defaultCar.addListener(this);
    }



    @FXML
    public void handleOpenNewCarWindow(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("new-car-view.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        stage.setTitle("Wprowadź nowy samochód");

        Stage primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        stage.initModality(javafx.stage.Modality.WINDOW_MODAL);

        stage.initOwner(primaryStage);

        stage.setScene(scene);

        NewCarController controller = loader.getController();
        controller.setDialogStage(stage);
        controller.init();

        stage.showAndWait();

        if (controller.isOkClicked()) {
            Samochod nowySamochod = controller.getNewCar();
            loadNewCar(nowySamochod);
        }
    }

    private ObservableList<Samochod> carList = FXCollections.observableArrayList();

    @FXML private ComboBox<String> carComboBox;

    private void loadNewCar(Samochod nowySamochod) {

        currentCar = nowySamochod;

        carList.add(currentCar);
        carComboBox.getItems().add(currentCar.getModel() + " (" + currentCar.getNrRejestracyjny() + ")");
        carComboBox.getSelectionModel().select(carList.size() - 1);

        modelTextField.setText(currentCar.getModel());
        nrRejestracyjnyTextField.setText(currentCar.getNrRejestracyjny());

        sProducentTextField.setText(currentCar.silnik.producent);
        sCenaTextField.setText(String.valueOf(currentCar.silnik.cena));
        sWagaTextField.setText(String.valueOf(currentCar.silnik.waga));

        spProducentTextField.setText(currentCar.skrzynia.sprzeglo.producent);
        spCenaTextField.setText(String.valueOf(currentCar.skrzynia.sprzeglo.cena));
        spWagaTextField.setText(String.valueOf(currentCar.skrzynia.sprzeglo.waga));

        sbProducentTextField.setText(currentCar.skrzynia.producent);
        sbCenaTextField.setText(String.valueOf(currentCar.skrzynia.cena));
        sbWagaTextField.setText(String.valueOf(currentCar.skrzynia.waga));

        System.out.println("Załadowano nowy samochód: " + currentCar.getModel());
        updateGuiStatus();
        currentCar.addListener(this);
        update();
    }

    public void handleStartSamochod(ActionEvent event) {
        if (currentCar != null) {
            currentCar.wlacz();
            updateGuiStatus();
        }
    }

    public void handleStopSamochod(ActionEvent event) {
        if (currentCar != null) {
            currentCar.wylacz();
            updateGuiStatus();
        }
    }

    public void handleZwiekszBieg(ActionEvent event) {
        if (currentCar != null) {
            currentCar.skrzynia.zwiekszBieg();
            updateGuiStatus();
        }
    }

    public void handleZmniejszBieg(ActionEvent event) {
        if (currentCar != null) {
            currentCar.skrzynia.zmniejszBieg();
            updateGuiStatus();
        }
    }

    public void handleObrotyUp(ActionEvent event) {
        if (currentCar != null) {
            currentCar.silnik.up();
            updateGuiStatus();
        }
    }

    public void handleObrotyDown(ActionEvent event) {
        if (currentCar != null) {
            currentCar.silnik.down();
            updateGuiStatus();
        }
    }

    public void handleWcisnijSprzeglo(ActionEvent event) {
        if (currentCar != null) {
            currentCar.skrzynia.sprzeglo.wcisnij();
            updateGuiStatus();
        }
    }

    public void handleZwolnijSprzeglo(ActionEvent event) {
        if (currentCar != null) {
            currentCar.skrzynia.sprzeglo.zwolnij();
            updateGuiStatus();
        }
    }

    private void updateGuiStatus() {
        if (currentCar == null) {
            return;
        }

        modelTextField.setText(currentCar.getModel());
        nrRejestracyjnyTextField.setText(currentCar.getNrRejestracyjny());

        int totalWaga = currentCar.silnik.waga +
                currentCar.skrzynia.waga +
                currentCar.skrzynia.sprzeglo.waga;
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

        pozycjaX.setText(String.valueOf(currentCar.pozycja.getX()));
        pozycjaY.setText(String.valueOf(currentCar.pozycja.getY()));
        update();

    }

    private void clearAllTextFields() {
        modelTextField.clear();
        nrRejestracyjnyTextField.clear();
        wagaSamochoduTextField.clear();
        predkoscTextField.clear();

        sProducentTextField.clear();
        sCenaTextField.clear();
        sWagaTextField.clear();
        sObrotyTextField.clear();

        sbProducentTextField.clear();
        sbCenaTextField.clear();
        sbWagaTextField.clear();
        sbAktBiegTextField.clear();

        spProducentTextField.clear();
        spCenaTextField.clear();
        spWagaTextField.clear();
        spStanTextField.clear();

        pozycjaX.clear();
        pozycjaY.clear();
        update();
    }


    @FXML
    public void clearWindow(ActionEvent actionEvent) {
        if (currentCar != null) {

            String itemToRemove = carComboBox.getSelectionModel().getSelectedItem();

            carList.remove(currentCar);
            carComboBox.getItems().remove(itemToRemove);


            currentCar = null;

            if (carList.isEmpty()) {
                carComboBox.getSelectionModel().clearSelection();
                clearAllTextFields();
                System.out.println("Usunięto ostatni samochód. Lista jest pusta.");
                carComboBox.getItems().clear();
            } else {
                carComboBox.getSelectionModel().selectFirst();
                System.out.println("Usunięto samochód. Wybrano pierwszy samochód na liście.");
            }
        } else {
            clearAllTextFields();
            System.out.println("Nie wybrano samochodu do usunięcia.");
            carComboBox.getItems().clear();
        }
        update();
    }

    @Override
    public void update() {
        Platform.runLater(() -> {
            if (currentCar != null && carIcon != null) {
                carIcon.setTranslateX(currentCar.pozycja.x);
                carIcon.setTranslateY(currentCar.pozycja.y);
                updateGuiStatus();
            }
        });
    }

}