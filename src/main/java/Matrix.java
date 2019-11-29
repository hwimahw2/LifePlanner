import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Matrix {

        public int n;
        public int m;
        public char[][] arrayMatrix;

        Matrix(int n, int m){
            arrayMatrix = new char[n][m];
        }

        public int getN() {
            return n;
        }

        public int getM() {
            return m;
        }

        public char[][] getArrayMatrix() {
            return arrayMatrix;
        }

        public String toString(){
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    sb.append(arrayMatrix[i][j]);
                    if(j == (m - 1)){
                        //sb.append('\n');
                    }
                }
            }
            return sb.toString();
        }

        public char[] toOneDimensionalArray(char[][] arrayMatrix){
            char[] arr = new char[n*m];
            int k = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    arr[k] = arrayMatrix[i][j];
                    k++;
                }
            }
            return arr;
        }

    public void matrixInitialize(InputStream fileStream, Matrix matrix) throws InputMismatchException, IOException {
        Scanner sc = new Scanner(fileStream);
        matrix.n = sc.nextInt();
        matrix.m = sc.nextInt();
        sc.nextLine();
        matrix.arrayMatrix = new char[matrix.n][matrix.m];
        for (int i = 0; i < matrix.n; i++) {
            String str = sc.nextLine();
            char[] arr = str.toCharArray();
            System.arraycopy(arr, 0, matrix.arrayMatrix[i], 0, arr.length);
        }
    }

}
