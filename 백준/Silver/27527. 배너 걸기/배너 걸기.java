import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M,map[],heights[],T;
    static boolean ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = (9*M)/10;
        if ((9*M)%10 != 0) T++;

        ans = false;
        map = new int[N];
        heights = new int[1000001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        if(ans) System.out.println("YES");
        else System.out.println("NO");
        br.close();
    }

    static void solve() {
        for (int i = 0; i < M; i++) {
            heights[map[i]]++;
            if (heights[map[i]] == T) {
                ans = true;
                return;
            }
        }

        for (int i = M; i < N; i++) {
            heights[map[i-M]]--;
            heights[map[i]] ++;
            if (heights[map[i]] == T) {
                ans = true;
                return;
            }
        }
    }
}
