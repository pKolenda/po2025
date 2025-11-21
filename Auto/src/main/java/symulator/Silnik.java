package symulator;

public class Silnik extends Komponent {

    int maxObroty = 7000;
    public int obroty;

    public Silnik(String producent, int waga, int cena) {
        super(producent, waga, cena);
        obroty = 0;
    }

    public void start(){
        obroty = 1000;
    }

    public void stop(){
        obroty = 0;
    }

    public int getObroty(){
        return obroty;
    }

    public void up(){
        if((obroty + 1000) < maxObroty) {
            obroty = obroty + 1000;
        }
    }

    public void down(){
        if((obroty - 1000) > 1000) {
            obroty = obroty - 1000;
        } else if (obroty == 1000) {

        } else {
            obroty = 1000;
        }
    }

}