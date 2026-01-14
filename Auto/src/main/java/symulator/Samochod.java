package symulator;

import java.util.ArrayList;
import java.util.List;

public class Samochod extends Thread {

    public Silnik silnik;
    public SkrzyniaBiegow skrzynia;
    public Pozycja pozycja;
    private String model;
    private String nrRejestracyjny;
    private Pozycja cel;
    private List<Listener> listeners = new ArrayList<>();

    public Samochod(Silnik silnik, SkrzyniaBiegow skrzynia, Pozycja pozycja) {
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.pozycja = pozycja;
        this.cel = null;

        this.skrzynia.sprzeglo.setSilnik(silnik);

        this.start();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNrRejestracyjny() {
        return nrRejestracyjny;
    }

    public void setNrRejestracyjny(String nrRejestracyjny) {
        this.nrRejestracyjny = nrRejestracyjny;
    }

    public void wlacz() {
        silnik.start();
        skrzynia.aktBieg = 1;
    }

    public void wylacz() {
        skrzynia.aktBieg = 0;
        silnik.stop();
    }

    public void Go(Pozycja nowaPozycja) {
        this.cel = nowaPozycja;
    }

    public double getPredkosc() {
        return (this.silnik.getObroty() * (this.skrzynia.getAktBieg())) / 100.0;
    }

    @Override
    public void run() {
        double deltat = 0.1;
        while (true) {
            if (cel != null) {
                double odleglosc = Math.sqrt(Math.pow(cel.x - pozycja.x, 2) + Math.pow(cel.y - pozycja.y, 2));
                double krok = getPredkosc() * deltat;
                if (odleglosc > krok) {
                    double dx = getPredkosc() * deltat * (cel.x - pozycja.x) / odleglosc;
                    double dy = getPredkosc() * deltat * (cel.y - pozycja.y) / odleglosc;

                    pozycja.x += dx;
                    pozycja.y += dy;
                    notifyListeners();
                } else {
                    pozycja.x = cel.x;
                    pozycja.y = cel.y;
                    cel = null;
                    notifyListeners();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (Listener l : listeners) {
            l.update();
        }
    }

}