import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Lottov1 {

    public static void main(String[] args) {


        ArrayList<Integer> bit = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj własne liczby: ");
        int i =6;
        int nr;;
        while(i>0)
        {
            nr = input.nextInt();
            if(nr >= 1 && nr < 49)
                bit.add(nr);
            else
                System.out.println("Podaj liczby z zakresu <1;49>: ");
            i--;
        }

        int trafienia = 0;

        ArrayList<Integer> lista = new ArrayList<>();
        Random random = new Random();

        int b = 0;
        while (b < 6)
        {
            int x = random.nextInt(49) + 1;
            if (lista.contains(x))
            {
                b = b;
            }
            else {
                lista.add(x);
                b++;
            }

        }

        System.out.println(lista);

        int a = 0;

        while (a < 6)
            {
                for(int j = 0; j < 6; j++)
                {
                    if (Objects.equals(bit.get(a), lista.get(j)))
                        trafienia++;
                    a++;
                }

            }


        System.out.println(bit);
        System.out.println("Liczba trafień: " + trafienia);


    }
}