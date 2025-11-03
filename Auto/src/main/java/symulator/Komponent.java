package symulator;

public abstract class Komponent {

    public String producent;
    public int waga;
    public int cena;

    public Komponent(String producent, int waga, int cena) {
        this.producent = producent;
        this.waga = waga;
        this.cena = cena;
    }

    public String getNazwa() {
        return producent;
    }

    public String getModel(String model) {
        return model;
    }
}
