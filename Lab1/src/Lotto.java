import java.util.ArrayList;
import java.util.Random;



public class Lotto {

    public static void main(String[] args) {
        ArrayList<Integer> lista = new ArrayList<>();
        Random random = new Random();
        int i = 6;
        while(i>0){
            int x = random.nextInt(49) + 1;
            if(lista.contains(x))
            {
                i = i;
            }
            else
                lista.add(x);
            i--;
        }

        System.out.println(lista);
    }
}