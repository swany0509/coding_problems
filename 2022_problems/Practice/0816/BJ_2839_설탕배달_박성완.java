
//package s0816.bj_2839;
// 백준 2839 설탕배달
import java.util.Scanner;

public class Main {
	// 전역변수 선언
	static int N;
	static int [] dp;
	public static void main(String[] args) {
		//N입력
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// dp로 계산할 것이지만 초기화의 용이를 위해 최소 5개까지는 있어야 한다.
		// N과 6(5+1)중 더 큰 값으로 배열을 만든다.
		dp = new int[(5+1) < (N+1) ? N+1 : 6];
		// 배열을 초기화한다.
		// 4까지는 정수최대값으로 설정하고, 3과 5는 1로 설정한다.
		for (int i = 0; i < 5; i++) dp[i] = Integer.MAX_VALUE;
		dp[3] = 1; dp[5] = 1;
		
		// 6부터 탐색하며 3요소 전의 값과 5요소 전의 값 중 작은 값을 고른다.
		// 이 때 고른 값이 정수최대값이라면 3와5로는 만들 수 없는 조합이다.
		// 그 경우가 아니라면 고른 최소 값에 1을 더해서 저장한다.
		for (int i = 6; i <= N ; i++) {
			dp[i] = dp[i-3] < dp[i-5] ? dp[i-3] : dp[i-5];
			if(dp[i] != Integer.MAX_VALUE) dp[i]++;
		}
		
		// 구하고자 하는 값이 정수최대값이라면 3와5로는 만들 수 없는 조합이므로 -1을 출력한다.
		// 아니라면 값을 출력한다.
		if(dp[N] == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(dp[N]);

	}

}
