package symulator;

public class Silnik extends Komponent {

    int maxObroty = 7000;
    public int obroty;

    public Silnik(String producent,  int waga, int cena) {
        super(producent, waga, cena);
        obroty = 0;
    }

    public void start(){
        obroty = 1000;
    }

    public void stop(){
        obroty = 0;
    }

    public void obroty(){System.out.println(obroty);}

    public void up(){
        if((obroty + 1000) < maxObroty) {
            obroty = obroty + 1000;
        }
        else{
            System.out.println("Nie da się zwiekszyć obrotów");
        }

    }

    public void down(){
        if((obroty - 1000) > 1000) {
            obroty = obroty - 1000;
        }
        if(obroty == 1000) {
            System.out.println("Nie da się zmniejszyć obrotów");
        }
        else {
            obroty = 1000;
        }
    }

}
