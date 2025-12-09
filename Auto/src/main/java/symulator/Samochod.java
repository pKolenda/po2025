package symulator;

public class Samochod {

    public Silnik silnik;
    public SkrzyniaBiegow skrzynia;
    public Pozycja pozycja;
    private String model;
    private String nrRejestracyjny;

    public Samochod(Silnik silnik, SkrzyniaBiegow skrzynia, Pozycja pozycja) {
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.pozycja = pozycja;
    }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getNrRejestracyjny() { return nrRejestracyjny; }
    public void setNrRejestracyjny(String nrRejestracyjny) { this.nrRejestracyjny = nrRejestracyjny; }

    public void start(){
        silnik.start();
        skrzynia.aktBieg = 1;
    }

    public void stop(){
        skrzynia.aktBieg = 0;
        silnik.stop();
    }

    public void Go(double nowy_x, double nowy_y){
        pozycja.x += nowy_x;
        pozycja.y += nowy_y;
    }

}