package org.example.samochodgui;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import symulator.Samochod;
import symulator.Silnik;
import symulator.SkrzyniaBiegow;
import symulator.Sprzeglo;
import symulator.Pozycja;
import javafx.stage.Modality;

import java.io.IOException;

public class HelloController {

    private ObservableList<Samochod> samochodyList = FXCollections.observableArrayList();
    private Samochod currentCar;

    // Pola FXML dla Symulacji i Pozycji
    @FXML private ImageView carImageView;
    @FXML private ImageView targetImageView;
    @FXML private TextField positionXTextField; // Nowe pole X
    @FXML private TextField positionYTextField; // Nowe pole Y

    // Cel podróży i logika animacji
    private double targetX = -1;
    private double targetY = -1;
    private AnimationTimer timer;
    private static final double SCALE_FACTOR = 0.005; // Prędkość w pikselach/s na jednostkę (Obroty/Bieg)

    // Pola GUI
    @FXML private ComboBox<String> carSelectorComboBox;
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
        carSelectorComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                handleCarSelection(newVal);
            }
        });

        initializeSamochod();
        setupAnimationTimer();
    }

    // Uproszczona fizyka: Oblicza prędkość w pikselach/s
    private double calculateSpeed() {
        if (currentCar == null || currentCar.skrzynia.aktBieg == 0) return 0;

        // Obliczanie prędkości jako (Obroty / Bieg) * Współczynnik
        return (double)currentCar.silnik.obroty / currentCar.skrzynia.aktBieg * SCALE_FACTOR;
    }

    // Timer do animacji ruchu samochodu
    private void setupAnimationTimer() {
        timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (lastUpdate == 0) {
                    lastUpdate = now;
                    return;
                }

                // Czas (w sekundach) od ostatniej klatki
                double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;
                lastUpdate = now;

                if (currentCar != null && targetX != -1 && calculateSpeed() > 0) {
                    moveCar(elapsedSeconds);
                }
            }
        };
        timer.start();
    }

    // Logika ruchu (krok w stronę celu)
    private void moveCar(double elapsedSeconds) {
        double currentX = currentCar.pozycja.x;
        double currentY = currentCar.pozycja.y;

        double speed = calculateSpeed();
        double distanceToTravel = speed * elapsedSeconds;

        double dx = targetX - currentX;
        double dy = targetY - currentY;
        double totalDistance = Math.sqrt(dx * dx + dy * dy);

        if (totalDistance < distanceToTravel) {
            // Samochód osiągnął cel
            currentCar.pozycja.setPozycja(targetX, targetY);
            targetX = -1;
            targetY = -1;
            if (targetImageView != null) targetImageView.setVisible(false);
        } else {
            // Ruch w kierunku celu
            double factor = distanceToTravel / totalDistance;
            double newX = currentX + dx * factor;
            double newY = currentY + dy * factor;

            currentCar.pozycja.setPozycja(newX, newY);
        }

        // Aktualizujemy wizualizację i pola tekstowe
        updateCarVisualization();
    }

    // Wizualne umiejscowienie ImageView i aktualizacja pól tekstowych (X, Y, Prędkość)
    private void updateCarVisualization() {
        // Ustawienie ImageView na nową pozycję
        carImageView.setLayoutX(currentCar.pozycja.x);
        carImageView.setLayoutY(currentCar.pozycja.y);

        // Aktualizacja pól tekstowych pozycji
        positionXTextField.setText(String.format("%.1f", currentCar.pozycja.x));
        positionYTextField.setText(String.format("%.1f", currentCar.pozycja.y));

        // Aktualizacja prędkości
        predkoscTextField.setText(String.format("%.1f", calculateSpeed()));
    }


    @FXML
    public void handleMapClick(MouseEvent event) {
        if (currentCar != null) {
            // Ustawienie celu na pozycję kliknięcia myszą
            // Odejmujemy połowę szerokości/wysokości obrazka dla wyśrodkowania celu
            targetX = event.getX() - carImageView.getFitWidth() / 2;
            targetY = event.getY() - carImageView.getFitHeight() / 2;

            // Ustawienie znacznika celu (opcjonalne)
            if (targetImageView != null) {
                targetImageView.setLayoutX(targetX);
                targetImageView.setLayoutY(targetY);
                targetImageView.setVisible(true);
            }

            System.out.println("Nowy cel: (" + targetX + ", " + targetY + ")");
        }
    }

    private void addCarToList(Samochod car) {
        samochodyList.add(car);
        carSelectorComboBox.getItems().add(car.model);
    }

    private void handleCarSelection(String selectedModel) {
        currentCar = samochodyList.stream()
                .filter(car -> car.model.equals(selectedModel))
                .findFirst()
                .orElse(null);

        if (currentCar != null) {
            // Przeniesienie samochodu na pozycję z modelu i zresetowanie celu
            updateCarVisualization();
            targetX = -1;
            targetY = -1;
            if (targetImageView != null) targetImageView.setVisible(false);
        }
        updateGuiStatus();
    }

    @FXML
    public void initializeSamochod() {
        if (currentCar == null && samochodyList.isEmpty()) {
            Sprzeglo sprzeglo = new Sprzeglo("Luk", 5, 200);
            Silnik silnik = new Silnik("BMW", 150, 8000);
            SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("ZF", 40, 5000, sprzeglo);
            Pozycja pozycja = new Pozycja();

            Samochod defaultCar = new Samochod(silnik, skrzynia, pozycja);
            defaultCar.setModel("Domyślny Model");
            defaultCar.setNrRejestracyjny("START00");
            defaultCar.setWaga(1000);

            // Ustawienie startowej pozycji wizualnej
            defaultCar.pozycja.setPozycja(50, 50);

            addCarToList(defaultCar);
            carSelectorComboBox.getSelectionModel().select(defaultCar.model);
        }
    }


    // --- Metody obsługi zdarzeń (symulator) ---

    public void handleStartSamochod(ActionEvent event) { if (currentCar != null) { currentCar.start(); updateGuiStatus(); } }
    public void handleStopSamochod(ActionEvent event) {
        if (currentCar != null) {
            currentCar.stop();
            targetX = -1;
            targetY = -1;
            if (targetImageView != null) targetImageView.setVisible(false);
            updateGuiStatus();
        }
    }
    public void handleZwiekszBieg(ActionEvent event) { if (currentCar != null) { currentCar.skrzynia.zwiekszBieg(); updateGuiStatus(); } }
    public void handleZmniejszBieg(ActionEvent event) { if (currentCar != null) { currentCar.skrzynia.zmniejszBieg(); updateGuiStatus(); } }
    public void handleObrotyUp(ActionEvent event) { if (currentCar != null) { currentCar.silnik.up(); updateGuiStatus(); } }
    public void handleObrotyDown(ActionEvent event) { if (currentCar != null) { currentCar.silnik.down(); updateGuiStatus(); } }
    public void handleWcisnijSprzeglo(ActionEvent event) { if (currentCar != null) { currentCar.skrzynia.sprzeglo.wcisnij(); updateGuiStatus(); } }
    public void handleZwolnijSprzeglo(ActionEvent event) { if (currentCar != null) { currentCar.skrzynia.sprzeglo.zwolnij(); updateGuiStatus(); } }

    @FXML
    public void handleOpenNewCarWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("new-car-view.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        stage.setTitle("Wprowadź nowy samochód");
        stage.initModality(Modality.WINDOW_MODAL);

        Stage primaryStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.initOwner(primaryStage);

        stage.setScene(scene);

        NewCarController controller = loader.getController();
        controller.setDialogStage(stage);

        stage.showAndWait();

        if (controller.isOkClicked()) {
            Samochod newCarFromDialog = controller.getNewCar();

            if (newCarFromDialog != null) {
                this.currentCar = newCarFromDialog;
                this.currentCar.pozycja.setPozycja(50, 50); // Nowy samochód startuje w (50, 50)
                addCarToList(newCarFromDialog);
                System.out.println("Załadowano nowy samochód: " + currentCar.model);
            }
        }
    }


    @FXML
    public void handleDeleteSelectedCar(ActionEvent actionEvent) {
        if (currentCar != null) {
            String selectedModel = currentCar.model;

            samochodyList.remove(currentCar);
            carSelectorComboBox.getItems().remove(selectedModel);

            if (!samochodyList.isEmpty()) {
                carSelectorComboBox.getSelectionModel().selectFirst();
            } else {
                currentCar = null;
                carSelectorComboBox.getSelectionModel().clearSelection();
            }
            updateGuiStatus();
        }
    }

    @FXML
    public void clearWindow(ActionEvent actionEvent) {
        currentCar = null;
        carSelectorComboBox.getSelectionModel().clearSelection();
        updateGuiStatus();
    }

    private void updateGuiStatus() {
        if (currentCar == null) {
            modelTextField.clear(); nrRejestracyjnyTextField.clear(); wagaSamochoduTextField.clear(); predkoscTextField.clear();
            positionXTextField.clear(); positionYTextField.clear();

            sObrotyTextField.clear(); sProducentTextField.clear(); sCenaTextField.clear(); sWagaTextField.clear();
            sbAktBiegTextField.clear(); sbProducentTextField.clear(); sbCenaTextField.clear(); sbWagaTextField.clear();
            spStanTextField.clear(); spProducentTextField.clear(); spCenaTextField.clear(); spWagaTextField.clear();

            carImageView.setVisible(false);
            if (targetImageView != null) targetImageView.setVisible(false);
            return;
        }

        // Zapewnienie widoczności wizualizacji
        carImageView.setVisible(true);

        // Aktualizacja pól ogólnych i pozycji
        modelTextField.setText(currentCar.model);
        nrRejestracyjnyTextField.setText(currentCar.nrRejestracyjny);
        wagaSamochoduTextField.setText(String.valueOf(currentCar.getWagaCalkowita()));
        predkoscTextField.setText(String.format("%.1f", calculateSpeed()));
        positionXTextField.setText(String.format("%.1f", currentCar.pozycja.x));
        positionYTextField.setText(String.format("%.1f", currentCar.pozycja.y));

        // Aktualizacja Silnika
        sObrotyTextField.setText(String.valueOf(currentCar.silnik.getObroty()));
        sProducentTextField.setText(currentCar.silnik.producent);
        sCenaTextField.setText(String.valueOf(currentCar.silnik.cena));
        sWagaTextField.setText(String.valueOf(currentCar.silnik.waga));

        // Aktualizacja Skrzyni
        sbAktBiegTextField.setText(String.valueOf(currentCar.skrzynia.getAktBieg()));
        sbProducentTextField.setText(currentCar.skrzynia.producent);
        sbCenaTextField.setText(String.valueOf(currentCar.skrzynia.cena));
        sbWagaTextField.setText(String.valueOf(currentCar.skrzynia.waga));

        // Aktualizacja Sprzęgła
        spStanTextField.setText(currentCar.skrzynia.sprzeglo.getStan() ? "WCIŚNIĘTE" : "Zwolnione");
        spProducentTextField.setText(currentCar.skrzynia.sprzeglo.producent);
        spCenaTextField.setText(String.valueOf(currentCar.skrzynia.sprzeglo.cena));
        spWagaTextField.setText(String.valueOf(currentCar.skrzynia.sprzeglo.waga));

        // Aktualizacja wizualizacji (ImageView)
        updateCarVisualization();
    }
}