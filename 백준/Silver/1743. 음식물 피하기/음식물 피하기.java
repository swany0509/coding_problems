import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class Main {
    static int N,M,K, longest, count;
    static int map[][], res[];
    static boolean visited[][];

    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) -1;
            int j = Integer.parseInt(st.nextToken()) -1;

            map[i][j] = -1;
        }

        count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == -1) {
                    bfs(i,j);
                    count++;
                }
            }
        }
        res = new int[count];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != -1) res[map[i][j]]++;
            }
        }

        for (int i = 1; i < count; i++) {
            longest = Math.max(longest,res[i]);
        }

        System.out.println(longest);
    }

    private static void bfs(int i, int j) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(i,j));
        map[i][j] = count;
        visited[i][j] = true;
        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for (int k = 0; k < 4; k++) {
                int ii = cur.x + dx[k];
                int jj = cur.y + dy[k];
                if(isRange(ii,jj) && !visited[ii][jj] && map[ii][jj] == -1){
                    queue.offer(new Point(ii,jj));
                    visited[ii][jj] = true;
                    map[ii][jj] = count;
                }
            }
        }
    }
    private static boolean isRange(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
