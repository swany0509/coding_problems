import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static long K, map[], increase[], decrease[];
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        map = new long[N+1];
        increase = new long[N+1];
        decrease = new long[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            map[i] = Long.parseLong(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            increase[i] = increase[i-1] + map[i];
            decrease[i] = decrease[i-1] + map[N-i+1];
        }

        if (K < increase[N]) {
            for (int i = 0; i < N ; i++) {
                if (K >= increase[i] && K < increase[i+1]) {
                    System.out.println((i+1));
                    break;
                }
            }
        } else {
            K -= increase[N];
            for (int i = 0; i < N ; i++) {
                if (K >= decrease[i] && K < decrease[i+1]) {
                    System.out.println(N-i);
                    break;
                }
            }
        }
        br.close();
    }
}
