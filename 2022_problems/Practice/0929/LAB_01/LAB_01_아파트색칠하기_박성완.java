//package s0929.a1801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 데일리 실습 18_1번 아파트 색칠하기
public class Main {
	static int N;
	static int dp[][];
	
	static void doDP(int n) {
		for (int i = 1; i < N; i++) {
			dp[i][0] = dp[i-1][0] + dp[i-1][1];
			dp[i][1] = dp[i-1][0];
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new int[N][2];
		
		dp[0][0] = dp[0][1] = 1;
		
		doDP(N);
		
		System.out.println(dp[N-1][0] + dp[N-1][1]);
		

	}

}

// 입력 8일때 결과
// 55
