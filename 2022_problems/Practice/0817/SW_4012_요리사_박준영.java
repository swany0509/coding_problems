package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_4012 {

    private static int N,min;
    private static int[][] s;
    private static int[] isSelect;


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        for(int t = 1; t <= T; t++) {

            N = Integer.parseInt(in.readLine());    // 식재료의 갯수
            min = Integer.MAX_VALUE;
            s = new int[N][N];
            isSelect = new int[N];

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for(int j = 0; j < N; j++) {
                    s[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = N - 1; i >= N/2; i--) {
                isSelect[i] = 1;
            }
            do {
                calc();
            } while(np());

            sb.append("#"+t).append(" "+min+"\n");
        }
        System.out.println(sb);
        in.close();
    }

    private static boolean np() {

        int i = N - 1;
        while (i > 0 && isSelect[i-1] >= isSelect[i]) --i;

        if ( i == 0) return false;

        int j = N - 1;
        while( isSelect[i-1] >= isSelect[j]) --j;

        swap(i-1,j);

        int k = N - 1;
        while ( i < k ) swap(i++,k--);

        return true;
    }

    private static void swap(int i , int j) {
        int temp = isSelect[i];
        isSelect[i] = isSelect[j];
        isSelect[j] = temp;
    }

    private static void calc() {
        int idxA = 0;
        int idxB = 0;

        int[] sumA = new int[N/2];
        int[] sumB = new int[N/2];
        for(int i = 0; i < N; i++) {
            if( isSelect[i] == 1 ) sumA[idxA++] = i;
            else sumB[idxB++] = i;
        }
        int total = 0;
        for(int i = 0; i < N/2; i++) {
            for(int j = 0; j < N/2; j++) {
                if( i == j) continue;
                total += s[sumA[i]][sumA[j]] - s[sumB[i]][sumB[j]];
            }
        }

        min = Math.min(min,Math.abs(total));
    }
}
