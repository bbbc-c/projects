package fuzzy;

public class Decomposition {
    // a-уровень
    private double a;
    // A нижняя граница обычного множества разложения
    private double downA;
    // A верхняя граница обычного множества разложения
    private double upA;

    public Decomposition(double a, double downA, double upA) {
        this.a = a;
        this.downA = downA;
        this.upA = upA;
    }

    public double getA() {
        return a;
    }

    public double getDownA() {
        return downA;
    }

    public double getUpA() {
        return upA;
    }

    public String getInfo() {
        double scale = Math.pow(10, 3);
        return "a = " + Math.round(a * scale) / scale + " downA = " + Math.round(downA * scale) / scale + " upA = " + Math.round(upA * scale) / scale;
    }
}
