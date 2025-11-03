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

    public String getProducent() {
        return producent;
    }

    public int getWaga() {
        return waga;
    }

    public int getCena() {
        return cena;
    }
}
