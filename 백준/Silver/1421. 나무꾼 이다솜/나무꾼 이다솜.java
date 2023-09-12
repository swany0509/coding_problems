import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long N,C,W, woods[], least, costs, most;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());
        W = Long.parseLong(st.nextToken());

        woods = new long[(int) N];
        least = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            woods[i] = Long.parseLong(br.readLine());
            least = Math.min(least, woods[i]);
            most = Math.max(most, woods[i]);
        }

        for (int unit = 1; unit <= most; unit++) {
            int count = 0;
            int cuts = 0;
            for (int i = 0; i < N; i++) {
                if (unit > woods[i]) continue;
                Long curcount = woods[i] / unit;
                Long curcuts = woods[i] / unit;
                if (woods[i] % unit == 0) curcuts--;
                if (curcuts != 0 && W * curcount * unit - (curcuts * C) < 0){
                    continue;
                }
                count += curcount;
                cuts += curcuts;
            }
            costs = Math.max(costs, W*count*unit - (cuts * C));
        }
        System.out.println(costs);
    }
}
