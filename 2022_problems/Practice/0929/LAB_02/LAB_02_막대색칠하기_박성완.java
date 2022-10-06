package s0929.a1802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 알고리즘 실습 18_2번 막대 색칠하기
// 잘못 풀었었네 ...? ㅎㅎㅎ...
public class Main {
	static int N;
	static int dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new int[N];
		
		dp[0] = 2;
		dp[1] = 5;
		
		for (int i = 2; i < N; i++) {
			dp[i] = dp[i-1]*2 + dp[i-2];
		}
		
		System.out.println(dp[N-1]);
		

	}
}

