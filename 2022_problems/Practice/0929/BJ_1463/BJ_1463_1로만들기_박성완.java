package s0929.bj_1463;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 1463 1로 만들기
// 세가지 풀이 모두 적용 Ver
public class Main {
	static int N, answer,dp[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		//bfs
//		System.out.println(bfs());
		
		
		//dfs
		answer = Integer.MAX_VALUE;
		dfs(N,0);
		System.out.println(answer);
		
		//dp
//		System.out.println(doDp());

	}
	//	메모리 210896	시간 308
	static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {N,0});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			if(cur[0] == 1) return cur[1];
			
			if(cur[0]%3==0) queue.offer(new int[] {cur[0]/3, cur[1]+1});
			if(cur[0]%2==0) queue.offer(new int[] {cur[0]/2, cur[1]+1});
			queue.offer(new int[] {cur[0]-1, cur[1]+1});
		}
		
		return -1;
	}
	// 메모리 12960	시간 132
	static void dfs(int cur, int time) {
		if(cur == 1) {
			answer = Math.min(answer, time);
			return;
		}
		if(time > answer) return;
		
		if(cur%3==0) dfs(cur/3,time+1);
		if(cur%2==0) dfs(cur/2,time+1);
		dfs(cur-1,time+1);
		
	}
	//메모리 17128  시간	148
	static int doDp() {
		dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 0;
		
		for (int i = 2; i < N+1; i++) {
			if(i%3==0) dp[i] = Math.min(dp[i], dp[i/3]+1);
			if(i%2==0) dp[i] = Math.min(dp[i], dp[i/2]+1);
			dp[i] = Math.min(dp[i], dp[i-1]+1);
		}
		
		return dp[N];
	}
}
