package animals;

public abstract class Animal {
    public int legs;
    public String name;

    public String getDescription() {
        return name + legs;
    }
}
