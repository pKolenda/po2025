package symulator;

public class Pozycja {

    public double x;
    public double y;

    public Pozycja() {
        x =  0.0;
        y = 0.0;
    }

    public void setPozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void Pozycja(double x1, double y1) {
        x += x1;
        y += y1;
    }

}