package symulator;

public class SkrzyniaBiegow extends Komponent {

    int iloscBiegow = 6;
    int aktBieg;

    public SkrzyniaBiegow(String producent,  int waga, int cena) {
        super(producent, waga, cena);
        Sprzeglo sprzeglo = new Sprzeglo();
        this.aktBieg = 0;

    }

    public void zwiekszBieg(){
        if(aktBieg < iloscBiegow){
            aktBieg++;
        }
        else{
            System.out.println("Masz najwyższy bieg");
        }
    }

    public void zmniejszBieg(){
        if(aktBieg > 1){
            aktBieg--;
        }
        else{
            System.out.println("Masz najnizszy bieg");
        }

    }

    public int aktBieg(){
        return aktBieg;
    }







}



