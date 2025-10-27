package zadania;
import animals.Animal;
import animals.Dog;
import animals.Parrot;
import animals.Snake;
import java.util.Random;

public class Zoo {
    Animal[] animals = new Animal[100];
    public Zoo(){
        setanimals();
    }
    public void setanimals() {

        Random rand = new Random();
        for (int i = 0; i < animals.length; i++) {
            int r = rand.nextInt(3);
            switch (r){
                case 0:
                    animals[i] = new Parrot();
                    break;
                    case 1:
                        animals[i] = new Dog();
                        break;
                        case 2:
                            animals[i] = new Snake();
            }

        }
    }

    public void suma() {
        int suma = 0;
        for (int i = 0; i < animals.length; i++)
        {
            suma = suma + animals[i].legs;
        }
        System.out.println(suma);
    }

    public void show() {
        for (int i = 0; i < animals.length; i++) {
            System.out.println(animals[i].name + " " +  animals[i].legs);
        }
    }

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        zoo.suma();
        zoo.show();
        Dog dog = new Dog();
        dog.getDescription();

    }
}



