package debug;

import FuzzyComparisons.FuzzyComparisons;

public class Main {
    public static void main(String[] args) {
        String str1 = "Хрупкий снег изломан весь";
        String str2 = "изломан снег весь хрупкий";
        FuzzyComparisons fc = new FuzzyComparisons(str1,str2);
        System.out.println("Строка 1: " + str1);
        System.out.println("Строка 2: " + str2);
        //System.out.println("Матрица парных сравнений:");
        //System.out.println(fc.getInfoComparisonMatrix());
        System.out.println("Ранг матрицы: ");
        System.out.println(fc.getRank());
        //System.out.println("Транзитивное замыкание:");
        //System.out.println(fc.getInfoComparisonMatrixTR());
        System.out.println(fc.getResult());
    }
}
