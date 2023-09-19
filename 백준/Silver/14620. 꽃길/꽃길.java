import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, map[][],NN,ix,iy,jx,jy,kx,ky, least,value;
    static int dx[] = {1,-1,0,0};
    static int dy[] = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        NN = N*N;
        map = new int[N][N];
        least = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < NN; i++) {
            ix = i/N; iy = i%N;
            if(isSide(ix,iy)) continue;
            addflower(ix,iy);
            for (int j = i+1; j < NN; j++) {
                jx = j/N; jy = j%N;
                if(isSide(jx,jy)) continue;
                if (Math.abs(jx-ix) + Math.abs(jy-iy) <= 2) continue;

                addflower(jx,jy);
                for (int k = j+1; k < NN; k++) {
                    kx = k/N; ky = k%N;
                    if(isSide(kx,ky)) continue;
                    if (Math.abs(kx-ix) + Math.abs(ky-iy) <= 2) continue;
                    if (Math.abs(kx-jx) + Math.abs(ky-jy) <= 2) continue;

                    addflower(kx,ky);
                    least = Math.min(least,value);
                    minusflower(kx,ky);
                }
                minusflower(jx,jy);
            }
            minusflower(ix,iy);
        }

        System.out.println(least);
        br.close();
    }
    static boolean isSide(int x, int y){
        return x == 0 || y == 0 || x == N-1 || y == N-1;
    }
    static void addflower(int x, int y){
        for (int i = 0; i < 4; i++) {
            value += map[x + dx[i]][y + dy[i]];
        }
        value += map[x][y];
    }
    static void minusflower(int x, int y){
        for (int i = 0; i < 4; i++) {
            value -= map[x + dx[i]][y + dy[i]];
        }
        value -= map[x][y];
    }
}
