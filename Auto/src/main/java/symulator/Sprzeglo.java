package symulator;

public class Sprzeglo extends Komponent {
    boolean stan;
    private Silnik silnik;


    public Sprzeglo(String producent, int waga, int cena) {
        super(producent, waga, cena);
        this.stan = false;
    }

    public void wcisnij(){
        if(silnik.obroty > 0) {
            stan = true;
            System.out.println("Sprzęgło: Wciśnięte.");
        }
        else{
            System.out.println("silnik nie jest wlaczony");
        }
    }

    public void zwolnij(){
        if(silnik.obroty > 0) {
            stan = false;
            System.out.println("Sprzęgło: Zwolnione.");
        }
        else{
            System.out.println("silnik nie jest wlaczony");
        }
    }

    public boolean getStan(){
        return stan;
    }

    public void setSilnik(Silnik silnik){
        this.silnik = silnik;
    }

}