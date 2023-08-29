import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T;
    static int array[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        array = new int[T][3];

        array[0][0] = 1;
        array[0][1] = 1;
        array[0][2] = 1;

        for (int i = 1; i < T; i++) {
            array[i][0] = (array[i-1][0] + array[i-1][1] + array[i-1][2])%9901;
            array[i][1] = (array[i-1][0] + array[i-1][2])%9901;
            array[i][2] = (array[i-1][0] + array[i-1][1])%9901;
        }

        System.out.println((array[T-1][0] + array[T-1][1] + array[T-1][2])%9901);
        br.close();
    }
}
