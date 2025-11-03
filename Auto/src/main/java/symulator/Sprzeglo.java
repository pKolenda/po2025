package symulator;

public class Sprzeglo extends Komponent {

    boolean stan;

    public Sprzeglo(String producent,  int waga, int cena) {
        super(producent, waga, cena);
        this.stan = false;
        System.out.println("Sprzęgło jest zwolnione");
    }

    public void wcisnij(){
        stan = true;
    }

    public void zwolnij(){
        stan = false;
    }

    public boolean stan(){
        return stan;
    }

}
