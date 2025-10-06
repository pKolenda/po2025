import java.util.Scanner;
public class Hallo { 

public static void main(String[] args) { 
        Scanner odczyt = new Scanner(System.in);
    System.out.println("podaj wartość:");
    int x = odczyt.nextInt();
    for(int i = x; i>0; i--)
    {
        for(int j = i; j>0;j--)
        {
            System.out.print("*"); 
        }
        System.out.println("");
    }
        } 
    } 

    