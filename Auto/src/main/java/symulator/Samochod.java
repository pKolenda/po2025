package symulator;

import java.security.PublicKey;
import java.util.Scanner;

public class Samochod {

    Silnik silnik;
    SkrzyniaBiegow skrzynia;
    Pozycja pozycja;


    public Samochod() {

        int model..

        this.silnik = new Silnik();
        this.skrzynia =  new SkrzyniaBiegow();
        this.pozycja =  new Pozycja();

    }


    public void start(){
        silnik.start();
    }

    public void stop(){
        skrzynia.aktBieg = 0;
        silnik.stop();
    }

    public void Go(){
        Scanner input = new Scanner(System.in);
        int nowy_x;
        int nowy_y;
        System.out.println("Nowa pozycja");
        System.out.println("x: ");
        nowy_x = input.nextInt();
        System.out.println("y: ");
        nowy_y = input.nextInt();

    }

    public static void main(String[] args) {
        Samochod samochod = new Samochod();
        samochod.pozycja.getPozycja();


    }

}
