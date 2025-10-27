package animals;

public class Parrot extends Animal {
    public Parrot(String name) {
        this.name = name;
        this.legs = 2;
    }

    public Parrot() {
        this.name = "Parrot";
        this.legs = 2;
    }

    public String getDescription() {
        return name + legs;
    }
}
