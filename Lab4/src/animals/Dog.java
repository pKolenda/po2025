package animals;

public class Dog extends Animal {
    public Dog(String name) {
        this.name = name;
        this.legs = 4;
    }

    public Dog() {
        this.name = "Dog";
        this.legs = 4;
    }

    public String getDescription() {
        return name + legs;
    }
}
