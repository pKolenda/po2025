package symulator;

public class SkrzyniaBiegow extends Komponent {

    private int iloscBiegow = 6;
    public int aktBieg;
    public Sprzeglo sprzeglo;

    public SkrzyniaBiegow(String producent, int waga, int cena, Sprzeglo sprzeglo) {
        super(producent, waga, cena);
        this.sprzeglo = sprzeglo;
        this.aktBieg = 0;
    }

    public void zwiekszBieg(){
        if(sprzeglo.getStan()) {
            if (aktBieg < iloscBiegow) {
                aktBieg++;
                System.out.println("Zwiększono bieg");
            } else {
                System.out.println("Masz max bieg");
            }
        }
        else{
            System.out.println("Wcisnij sprzeglo");
        }
    }

    public void zmniejszBieg(){
        if(sprzeglo.getStan()) {
            if (aktBieg > 1) {
                aktBieg--;
                System.out.println("Zmniejszono bieg");
            } else {
                System.out.println("Masz min bieg");
            }
        }
        else{
            System.out.println("Wcisnij sprzeglo");
        }
    }

    public int getAktBieg(){
        return aktBieg;
    }
}