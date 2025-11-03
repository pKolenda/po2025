package symulator;

public class Pozycja {

    public double x;
    public double y;

    public Pozycja() {
        x =  0.0;
        y = 0.0;
    }

    public void getPozycja() {
        System.out.println("x: " + x);
        System.out.println("y: " + y);
    }

    public void Pozycja(double x1, double y1) {
        x =+  x1;
        y =+ y1;
    }

}
