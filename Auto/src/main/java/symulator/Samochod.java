package symulator;

import java.security.PublicKey;
import java.util.Scanner;

public class Samochod {

    Silnik silnik;
    SkrzyniaBiegow skrzynia;
    Pozycja pozycja;


    public Samochod() {

        String producent;
        int waga;
        int cena;

        Scanner input = new Scanner(System.in);
        System.out.println("Podaj wagę silnika: ");
        waga = input.nextInt();
        System.out.println("Podaj cenę silnika: ");
        cena = input.nextInt();
        System.out.println("Podaj producenta silnika: ");
        producent = input.next();
        this.silnik = new Silnik(producent, waga, cena);

        System.out.println("Podaj wagę skrzyni biegów: ");
        waga = input.nextInt();
        System.out.println("Podaj cenę skrzyni biegów: ");
        cena = input.nextInt();
        System.out.println("Podaj producenta skrzyni biegów: ");
        producent = input.next();
        this.skrzynia =  new SkrzyniaBiegow(producent, waga, cena);

        this.pozycja =  new Pozycja();

    }


    public void start(){
        silnik.start();
        skrzynia.aktBieg = 1;
    }

    public void stop(){
        skrzynia.aktBieg = 0;
        silnik.stop();
    }

    public void Go(){
        Scanner input = new Scanner(System.in);
        double nowy_x = 0.0;
        double nowy_y = 0.0;
        System.out.println("Nowa pozycja");
        System.out.println("x: ");
        nowy_x = input.nextDouble();
        System.out.println("y: ");
        nowy_y = input.nextDouble();
        pozycja.x =+ nowy_x;
        pozycja.y =+ nowy_y;


    }

    public static void main(String[] args) {
        Samochod samochod = new Samochod();

       /*
        samochod.pozycja.getPozycja();
        samochod.Go();
        samochod.pozycja.getPozycja();

        samochod.start();
        samochod.silnik.obroty();
        samochod.silnik.up();
        samochod.silnik.obroty();

        samochod.skrzynia.zwiekszBieg();
        samochod.skrzynia.aktBieg();
       */

    }

}
