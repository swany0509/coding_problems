import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int minValue = Integer.MAX_VALUE;
    static int maxValue = Integer.MIN_VALUE;

    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }
        perm(0,0, map[0][0] - '0');

        System.out.println(maxValue + " " + minValue);
        br.close();
    }

    static void perm(int x, int y, int value) {
        if(x == N-1 && y == N-1) {
            minValue = Math.min(minValue, value);
            maxValue = Math.max(maxValue, value);
        }

        if(x < N-2) {
            perm(x+2, y, doCalc(value, map[x+2][y] - '0', map[x+1][y]));
        }

        if(y < N-2) {
            perm(x, y+2, doCalc(value, map[x][y+2] - '0', map[x][y+1]));
        }

        if(x < N-1 && y < N-1) {
            perm(x+1, y+1, doCalc(value, map[x+1][y+1] - '0', map[x+1][y]));
            perm(x+1, y+1, doCalc(value, map[x+1][y+1] - '0', map[x][y+1]));
        }
    }

    static int doCalc(int op1, int op2, char operand) {
        switch(operand){
            case '*':
                return op1*op2;
            case '-':
                return op1-op2;
            case '+':
                return op1+op2;
            default:
                System.out.println("error");
                break;
        }
        return 0;
    }

    static boolean isDigit(char c) {
        return c >= '0' && c < '9';
    }
}