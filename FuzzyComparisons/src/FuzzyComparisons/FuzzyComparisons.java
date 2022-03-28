package FuzzyComparisons;

public class FuzzyComparisons {

    private String firstString;
    private String secondString;
    private double[][] comparisonMatrix;
    private double[][] comparisonMatrixTR;
    private int rank = 0;

    public FuzzyComparisons(String firstString, String secondString){
        this.firstString = firstString;
        this.secondString = secondString;
        comparisonMatrix = new double[firstString.length()][secondString.length()];
        comparisonMatrixTR = new double[firstString.length()][secondString.length()];
        calComparisonMatrix();
        rank = rankOfMatrix(comparisonMatrix);
        calComparisonMatrixTR(comparisonMatrix,rank);
    }
    private double compareString(int row, int column){
        // Возвращает соотношение совпадений от кол-ва всех вариантов
        //кол-во совпадений
        int numCompare = 0;
        // Кол-во всех вариантов
        int numOfOptions = 0;
        for (int i = 0; i <= firstString.length();i++){
            if(i+row > firstString.length()) break;
            for (int j = 0; j <= secondString.length(); j++){
                if(j+column > secondString.length()) break;
                if (column <= row){
                    if(firstString.substring(i,i+row).contains(secondString.substring(j,j+column)))
                        numCompare++;
                }
                else {
                    if(secondString.substring(j,j+column).contains(firstString.substring(i,i+row)))
                        numCompare++;
                }
                numOfOptions++;
            }
        }
        return numCompare/(double)numOfOptions;
    }
    private void swap(double mat[][],int row1, int row2, int col){
        // свап специально для нахождение ранга матрицы
        for (int i = 0; i < col; i++){
            double temp = mat[row1][i];
            mat[row1][i] = mat[row2][i];
            mat[row2][i] = temp;
        }
    }
    private int rankOfMatrix(double matrix[][]) {
        // нахождение ранга матрицы
        double[][] mat = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length;i++)
            for (int j = 0; j < matrix[i].length; j++)
                mat[i][j] = matrix[i][j];
        int R = mat.length;
        int C = mat[0].length;
        int rank = C;
        for (int row = 0; row < rank; row++) {
            if (mat[row][row] != 0) {
                for (int col = 0; col < R; col++) {
                    if (col != row) {
                        double mult =(double) mat[col][row] / mat[row][row];
                        for (int i = 0; i < rank; i++)
                            mat[col][i] -= mult*mat[row][i];
                    }
                }
            }
            else {
                boolean reduce = true;
                for (int i = row + 1; i < R; i++) {
                    if (mat[i][row] != 0) {
                        swap(mat, row, i, rank);
                        reduce = false;
                        break;
                    }
                }
                if (reduce) {
                    rank--;
                    for (int i = 0; i < R; i++)
                        mat[i][row] = mat[i][rank];
                }
                row--;
            }
        }
        return rank;
    }
    private double minMax(double[][] R, double[][] R2, int row, int column){
        // минмакс операция
        double[] min = new double[R2[row].length];
        for(int i = 0; i < R2[row].length; i++){
            min[i] = Math.min(R[i][column], R2[row][i]);
        }
        double max = min[0];
        for (int i = 0; i < min.length; i++){
            if(max < min[i])
                max = min[i];
        }
        return max;
    }

    private double[][] composition(double[][] R, double[][] R2){
        // Minmax композиция
        double[][] R3 = new double[R.length][R[0].length];
        for(int i = 0; i < R3.length; i++){
            for(int j = 0; j < R3[i].length; j++){
                R3[i][j] = minMax(R,R2,i,j);
            }
        }
        return R3;
    }
    private void calComparisonMatrixTR(double[][] R,int rank){
        double[][] R3 = null;
        for (int k = 1; k < rank;k++){
            if(k == 1)
                R3 = composition(R,R);
            else
                R3 = composition(R,R3);
            for (int i = 0; i < comparisonMatrixTR.length; i++)
                for (int j= 0; j < comparisonMatrixTR[i].length;j++)
                    comparisonMatrixTR[i][j] = Math.max(comparisonMatrixTR[i][j],R3[i][j]);
        }
    }
    private void calComparisonMatrix(){
        // Построение матрицы сравнения
        // Заполнение транзективной матрицы
        for (int i = 1; i <= comparisonMatrix.length; i++)
            for (int j = 1; j <= comparisonMatrix[i-1].length; j++){
                double comStr = compareString(i,j);
                comparisonMatrix[i-1][j-1] = comStr;
                comparisonMatrixTR[i-1][j-1] = comStr;
            }
    }
    public String getResult(){
        String str = null;
        double[] numFound = {0,0,0,0,0,0};
        // кол-во на разных уровнях
        for (int i = 0; i < comparisonMatrixTR.length;i++)
            for (int j = 0; j < comparisonMatrixTR[i].length;j++){
                if (comparisonMatrixTR[i][j] > 0 && comparisonMatrixTR[i][j] < 0.25)
                    numFound[0]++;
                if (comparisonMatrixTR[i][j] >= 0.25 && comparisonMatrixTR[i][j] < 0.5)
                    numFound[1]++;
                if (comparisonMatrixTR[i][j] >= 0.5 && comparisonMatrixTR[i][j] < 0.75)
                    numFound[2]++;
                if (comparisonMatrixTR[i][j] >= 0.75 && comparisonMatrixTR[i][j] < 1)
                    numFound[3]++;
                if(comparisonMatrixTR[i][j] == 1)
                    numFound[4]++;
                if(comparisonMatrixTR[i][j] == 0)
                    numFound[5]++;
            }
        //среднее
        for (int i = 0; i < numFound.length;i++)
            numFound[i] = numFound[i]/(double)(comparisonMatrixTR.length*comparisonMatrixTR[0].length);
        int index = 0;
        // находим максимальный индекс
        for (int i = 0; i < numFound.length;i++){
            if(numFound[index] < numFound[i]) index = i;
        }
        switch (index){
            case 0:
                str = "Очень мальнекое сходство строк!";
                break;
            case 1:
                str = "Маленькое сходство строк!";
                break;
            case 2:
                str = "Большое сходство строк!";
                break;
            case 3:
                str = "Почти одинаковые строки!";
                break;
            case 4:
                str = "Одинаковые строки!";
                break;
            case 5:
                str = "Сходств нет!";
        }
        return str;
    }
    public int getRank() {
        return rank;
    }
    public String getInfoComparisonMatrix(){
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= comparisonMatrix.length; i++){
            for (int j = 1; j <= comparisonMatrix[i-1].length; j++){
                str.append(String.format("%5.4s\t", comparisonMatrix[i - 1][j - 1]));
            }
            str.append("\n");
        }
        return str.toString();
    }
    public String getInfoComparisonMatrixTR(){
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= comparisonMatrixTR.length; i++){
            for (int j = 1; j <= comparisonMatrixTR[i-1].length; j++){
                str.append(String.format("%5.4s\t", comparisonMatrixTR[i - 1][j - 1]));
            }
            str.append("\n");
        }
        return str.toString();
    }

}
