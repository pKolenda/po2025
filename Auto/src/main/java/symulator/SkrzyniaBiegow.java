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
        if(aktBieg < iloscBiegow){
            aktBieg++;
        }
    }

    public void zmniejszBieg(){
        if(aktBieg > 1){
            aktBieg--;
        }
    }

    public int getAktBieg(){
        return aktBieg;
    }
}