//package S0810.bj_1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 백준 1010 다리 놓기
public class Main {
	static int TC,N,M,cnt;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		//  테스트케이스 입력
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < TC; t++) {
			st = new StringTokenizer(br.readLine());
			// N,M 입력
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// nCr = nCr-n과 같다. 최소한의 계산을 위해 작은값으로 계산 목표를 둔다.
			N = N < M-N ? N : M-N;
			cnt=0;
			// DP 생성
			dp = new int[N+1];
			dp[0] = 1;
			// 이전 수를 이용하여 다음 순열을 구한다.
			for (int i = 1; i < N+1; i++) {
				dp[i] = dp[i-1]*(M+1-i)/i;
			}
			
			sb.append(dp[N]+ "\n");
		}
		System.out.println(sb);
		br.close();
	}

}
