import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K, M, N, least, problems[];
    static String answer;
    static StringBuilder sb;

    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        K = Integer.parseInt(br.readLine());

        for (int t = 1; t <= K; t++) {
            least = Integer.MAX_VALUE;
            answer = "ZZZZZZZZZZZZZZZZZZZZ";

            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            makelist();
            bitmasking();

            sb.append("Data Set ").append(t).append(": ").append(answer).append("\n\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static void bitmasking() {
        for (int flag = 1, size = (int)Math.pow(2,N), fullAlgo = (int)Math.pow(2,M) - 1; flag < size; flag++) {
            int has = 0;
            for (int i = 0; i < N; i++) {
                if ( (flag & (1 << i) ) != 0 ) has = has | problems[i];
            }

            if ( has == fullAlgo ) {
                String cur = getCode(flag);
//                System.out.println("fully " + cur + " " + getSize(cur));
                if ( least > getSize(cur) || ( least == getSize(cur) && cur.compareTo(answer) < 0)){
                    answer = cur;
                    least = getSize(answer);
                }
            }
        }
    }

    private static int getSize(String answer) {
        return (answer.length() + 1 ) / 2;
    }

    private static String getCode(int flag) {
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < N; i++) {
            if ( (flag & (1 << i) ) != 0 ) ret.append((char)('A' + i)).append(" ");
        }

        return ret.toString();
    }

    private static void makelist() throws IOException {
        problems = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int bit = 0;

            while(st.hasMoreTokens()){
                int type = Integer.parseInt(st.nextToken()) - 1 ;
                bit = bit | (int)Math.pow(2,type);
            }

            problems[i] = bit;
        }

    }
}
