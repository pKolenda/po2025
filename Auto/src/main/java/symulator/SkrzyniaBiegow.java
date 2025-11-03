package symulator;

import java.util.Scanner;

public class SkrzyniaBiegow extends Komponent {

    int iloscBiegow = 6;
    int aktBieg;

    public SkrzyniaBiegow(String producent,  int waga, int cena) {
        super(producent, waga, cena);

        String producentSprzegla;
        int wagaSprzegla;
        int cenaSprzegla;

        Scanner input = new Scanner(System.in);
        System.out.println("Podaj wagę sprzęgła: ");
        wagaSprzegla = input.nextInt();
        System.out.println("Podaj cenę sprzęgła: ");
        cenaSprzegla = input.nextInt();
        System.out.println("Podaj producenta sprzęgła: ");
        producentSprzegla = input.next();
        Sprzeglo sprzeglo = new Sprzeglo(producent, waga, cena);

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

    public void aktBieg(){
        System.out.println(aktBieg);
    }







}



