package animals;

public class Snake extends Animal {
    public Snake(String name) {
        this.name = name;
        this.legs = 0;
    }

    public Snake() {
        this.name = "Snake";
        this.legs = 0;
    }

    public String getDescription() {
        return name + legs;
    }
}
