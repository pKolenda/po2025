import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Lottov2 {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        ArrayList<Integer> bit = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj własne liczby: ");
        int i =6;
        int nr;;
        while(i>0)
        {
            nr = input.nextInt();
            if(nr > 1 && nr < 49)
                bit.add(nr);
            else
                System.out.println("Podaj liczby z zakresu <1;49>: ");
            i--;
        }
        System.out.println(bit);
        int trafienia = 0;
        int wygrana = 0;
        ArrayList<Integer> lista_wygrana = new ArrayList<>();
        while(wygrana != 1)
        {

            ArrayList<Integer> lista = new ArrayList<>();
            Random random = new Random();

            int b = 0;
            while (b < 6)
            {
                int x = random.nextInt(49) + 1;
                if (lista.contains(x)) {
                    b = b;
                } else
                    lista.add(x);
                b++;
            }

            int a = 0;

            while (a < 6)
            {
                for(int j = 0; j < 6; j++)
                {
                    if (bit.get(a) == lista.get(j))
                        trafienia++;
                    a++;
                }

            }
            if(trafienia == 6)
            {
             wygrana++;
             lista_wygrana = lista;

            }
            else
            {
               trafienia = 0;
            }

        }
        System.out.println(lista_wygrana);
        System.out.println(bit);
        System.out.println("Liczba trafień: " + trafienia);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Czas wykonania operacji: " + elapsedTime + " ms");

    }
}