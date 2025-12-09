package symulator;

public class Samochod {

    public Silnik silnik;
    public SkrzyniaBiegow skrzynia;
    public Pozycja pozycja;

    // Pola dodane dla danych z GUI
    public String model = "";
    public String nrRejestracyjny = "";
    public int waga = 0; // Waga bazowa (karoserii)

    public Samochod(Silnik silnik, SkrzyniaBiegow skrzynia, Pozycja pozycja) {
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.pozycja = pozycja;
    }

    // --- SETTERY (Te metody są wywoływane w NewCarController) ---
    public void setModel(String model) {
        this.model = model;
    }

    public void setNrRejestracyjny(String nrRejestracyjny) {
        this.nrRejestracyjny = nrRejestracyjny;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }
    // ------------------------------------------------

    // Metoda obliczająca masę całkowitą (Baza + Podzespoły)
    public int getWagaCalkowita() {
        return this.waga + this.silnik.waga + this.skrzynia.waga + this.skrzynia.sprzeglo.waga;
    }

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