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
        System.out.println("Samochód: Włączono silnik i ustawiono 1. bieg.");
    }

    public void stop(){
        obroty = 0;
        System.out.println("Samochód: Wyłączono silnik i ustawiono 0. bieg.");

    }

    public int getObroty(){
        return obroty;
    }

    public void up(){
        if(obroty != 0) {
            if (obroty < maxObroty) {
                obroty = obroty + 1000;
            } else {
                System.out.println("Masz max obroty");
            }
        }
        else{
            System.out.println("Samochod nie jest wlaczony");
        }
    }

    public void down(){
        if(obroty != 0) {
            if (obroty > 1000) {
                obroty = obroty - 1000;
            } else {
                System.out.println("Masz min obroty");
            }
        }
        else{
            System.out.println("Samochod nie jest wlaczony");
        }
    }

}