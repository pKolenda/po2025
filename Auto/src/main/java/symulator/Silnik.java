package symulator;

public class Silnik extends Komponent {

    public Silnik(String producent,  int waga, int cena) {
        super(producent, waga, cena);
        obroty = 0;
    }

    int maxObroty = 7000;
    int obroty;

    public void start(){
        obroty = 1000;
    }

    public void stop(){
        obroty = 0;
    }

}
