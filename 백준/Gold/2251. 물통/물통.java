import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int A,B,C;
    static Set<Integer> availables;

    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        availables = new HashSet<>();
        visited = new boolean[A+1][B+1][C+1];

        permutations(0,0,C);

        for (int j = 0; j <= B; j++) {
            for (int k = 0; k <= C; k++) {
                if(visited[0][j][k]) availables.add(k);
            }
        }

        List<Integer> li = new ArrayList<>(availables);
        Collections.sort(li);

        for(int i : li) System.out.print(i + " ");

        br.close();
    }

    static void permutations(int a, int b, int c) {
        if(visited[a][b][c]) return;

        visited[a][b][c] = true;

        if(a != 0) {
            if(b != B) {
                if (a < B-b) permutations(0,a+b,c);
                else permutations(a+b-B, B,c);
            }
            if(c != C) {
                if (a < C-c) permutations(0,b,a+c);
                else permutations(a+c-C, b,C);
            }
        }

        if(b != 0) {
            if(a != A) {
                if (b < A-a) permutations(a+b, 0, c);
                else permutations(A,a+b-A,c);
            }
            if(c != C) {
                if (b < C-c) permutations(a,0,b+c);
                else permutations(a,b+c-C,C);
            }
        }

        if(c != 0) {
            if(a != A) {
                if (c < A-a) permutations(a+c,b,0);
                else permutations(A,b,a+c-A);
            }
            if(b != B) {
                if (c < B-b) permutations(a,b+c,0);
                else permutations(a,B,b+c-B);
            }
        }
    }
}