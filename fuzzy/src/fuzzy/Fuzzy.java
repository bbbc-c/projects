package fuzzy;

import java.util.ArrayList;

public class Fuzzy {
    private double a,b,c,d;
    // Какой функция принодлежности
    // 0 - график 1
    private int funMembership;
    private ArrayList<Decomposition> decompositions = new ArrayList<>();

    private Fuzzy(int funMembership, ArrayList<Decomposition> decompositions){
        this.funMembership = funMembership;
        this.decompositions = decompositions;
    }
    public Fuzzy(int funMembership,double a, double b, double c, double d, int countALevels) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.funMembership = funMembership;
        for (int i = 0; i <= countALevels; i++){
            double aLevels = (double) i * ((double)1 /(double) countALevels);
            double downA = 0, upA = 0;
            switch (funMembership){
                case 0:
                    downA = findXFun(0,aLevels,a,b,c,d);
                    upA = findXFun(0,aLevels,a,b,c,d);
                    break;
                case 1:
                    downA = findXFun(0,aLevels,a,b,c,d);
                    upA = findXFun(1,aLevels,c,c+b-a,c,d);
                    break;
                case 2:
                    downA = findXFun(2,aLevels,a,b,c,d);
                    upA = findXFun(2,aLevels,b,b,c,d);
                    break;
                case 3:
                    downA = findXFun(3,aLevels,a,b,c,d);
                    upA = findXFun(3,aLevels,a,b,c,d);
                    if(aLevels == 0){
                        downA = c+10;
                        upA = c+10;
                    }

                    break;
                case 4:
                    downA = findXFun(2,aLevels,a,b,c,d);
                    upA = findXFun(2,aLevels,b,b,d,d);
                    break;
                case 5:
                    if (aLevels == 0){
                        downA = a - 3*b;
                        upA = a + 3*b;
                    } else {
                        downA = a - findXFun(5,aLevels,a,b,c,d);
                        upA = a + findXFun(5,aLevels,a,b,c,d);
                    }
                    break;
                case 6:
                    if (aLevels == 0){
                        upA = b - 10;
                        downA = b - 10;
                    } else if (aLevels == 1){
                        upA = b + 10;
                        downA = b + 10;
                    } else {
                        downA = findXFun(6,aLevels,a,b,c,d);
                        upA = findXFun(6,aLevels,a,b,c,d);
                    }
                    break;
                case 7:
                    if (aLevels == 0){
                        downA = a - 3*b;
                        upA = a + 3*b;
                    } else {
                        downA = a - findXFun(5,aLevels,a,b,c,d);
                        upA = a + findXFun(5,aLevels,a,c,c,d);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + funMembership);
            }
            decompositions.add(new Decomposition(aLevels,downA,upA));
        }
    }

    private double findXFun(int funMembership,double y, double a, double b, double c, double d){
        double value = -1;
        switch (funMembership){
            case 0:
                if (y >= 0 && y <= 0.5)
                    return a - (Math.sqrt(2*y)*(a-b))/2;
                if (y > 0.5 && y <= 1)
                    return b - ((Math.sqrt(2)*(a-b)*(-1)*Math.sqrt(Math.abs(y-1)))/2);
                break;
            case 1:
                if (y >= 0 && y <= 0.5)
                    return b - (Math.sqrt(2*y)*(b-c))/2;
                if (y > 0.5 && y <= 1)
                    return c - ((Math.sqrt(2)*(b-c)*(-1)*Math.sqrt(Math.abs(y-1)))/2);
                break;
            case 2:
                return a-a*y+c*y;
            case 3:
                return Math.pow((1/y-1)/a, 1.0 / b)+c;
            case 5:
                return b*(Math.sqrt(2.0*Math.abs(Math.log(y))));
            case 6:
                return -1*((Math.log(1/y-1)-a*b)/a);
        }
        return value;
    }
    public static Fuzzy arithmetic(int typeArithmetic, Fuzzy leftValue,Fuzzy rightValue) {
        // 0 - "+"
        // 1 - "-"
        // 2 - "*"
        // 3 - "/"
        if (leftValue.getFunMembership() != rightValue.getFunMembership())
            throw new IllegalArgumentException("Типы графиков должны совпадать!");
        if (leftValue.decompositions.size() != rightValue.decompositions.size())
            throw new IllegalArgumentException("Кол-во а-уровней должно совпадать!");
        ArrayList<Decomposition> decompositions = new ArrayList<>();
        int i = 0;
        System.out.println(leftValue.getInfoDecomposition());
        System.out.println(rightValue.getInfoDecomposition());
        while (i != leftValue.decompositions.size()){
            Decomposition decomposition = null;
            switch (typeArithmetic){
                case 0:
                    decomposition = new Decomposition(leftValue.decompositions.get(i).getA(),
                            leftValue.decompositions.get(i).getDownA() + rightValue.decompositions.get(i).getDownA(),
                            leftValue.decompositions.get(i).getUpA() + rightValue.decompositions.get(i).getUpA());
                    break;
                case 1:
                    decomposition = new Decomposition(leftValue.decompositions.get(i).getA(),
                            leftValue.decompositions.get(i).getDownA() - rightValue.decompositions.get(i).getUpA(),
                            leftValue.decompositions.get(i).getUpA() - rightValue.decompositions.get(i).getDownA());
                    break;
                case 2:
                    decomposition = new Decomposition(leftValue.decompositions.get(i).getA(),
                            leftValue.decompositions.get(i).getDownA() * rightValue.decompositions.get(i).getDownA(),
                            leftValue.decompositions.get(i).getUpA() * rightValue.decompositions.get(i).getUpA());
                    break;
                case 3:
                    decomposition = new Decomposition(leftValue.decompositions.get(i).getA(),
                            leftValue.decompositions.get(i).getUpA() / rightValue.decompositions.get(i).getDownA(),
                            leftValue.decompositions.get(i).getDownA() / rightValue.decompositions.get(i).getUpA()
                    );
                    break;
            }
            decompositions.add(decomposition);
            i++;
        }
        return  new Fuzzy(leftValue.getFunMembership(), decompositions);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public int getFunMembership() {
        return funMembership;
    }

    public ArrayList<Decomposition> getDecompositions() {
        return decompositions;
    }

    public String getInfoDecomposition(){
        StringBuilder str = new StringBuilder();
        for (Decomposition d : decompositions)
            str.append(d.getInfo()).append("\n");
        return str.toString();
    }
}
