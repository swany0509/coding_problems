package s0929.bj_1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 12356	시간 96
// 백준 1149 RGB 거리
public class Main {
	// 전역변수
	static int N, dp[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		// dp 보드 생성
		dp = new int[N][3];
		// 첫 요소는 그냥 입력받는다.
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < 3; j++) {
			dp[0][j] = Integer.parseInt(st.nextToken());
		}
		// 다음번 요소부터는 입력을 받으면서 최적화 값을 구한다.
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// 이전 집 중 고른 색이 아닌 집 두개 중 낮은 값을 더해서 저장한다.
			dp[i][0] = Integer.parseInt(st.nextToken()) + Math.min(dp[i-1][1], dp[i-1][2]);
			dp[i][1] = Integer.parseInt(st.nextToken()) + Math.min(dp[i-1][0], dp[i-1][2]);
			dp[i][2] = Integer.parseInt(st.nextToken()) + Math.min(dp[i-1][0], dp[i-1][1]);
			
		}
		// 마지막 값 중 최솟값을 출력한다.
		System.out.println(Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])));

	}

}
