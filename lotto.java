import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;
public class lotto { 

public static void main(String[] args) { 

    HashSet<Integer> set = new HashSet<>();
   Random random = new Random();
   int i = 6;
   while(i>0){
        int x = random.nextInt(49) + 1;
        set.add(x);
        i--;
   }

   System.out.println(set);
    } 
} 

    