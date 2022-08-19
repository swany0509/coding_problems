//package s0819.bj_1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 전역변수 생성
	static int N,K;
	static boolean[] visited = new boolean[100001];
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N,K입력
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// bfs 실행
		System.out.println(bfs(N,K));
	}
	static int bfs(int n, int k) {
		// 큐 생성하고 첫 요소 집어넣음
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {n,0});
		int[] cur = null;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			int num = cur[0];
			// 정답에 도달하면 탈출
			if(num==k) break;
			// 범위를 벗어나거나 이미 방문한 곳이라면 다음으로
			if(num<0 || num>100000 || visited[num]) continue;
			// 아니라면 현재 위치를 방문처리
			visited[num] = true;
			// 세가지 경우의 수룰 모두 집어넣어본다.
			queue.offer(new int[] {num*2,cur[1]+1});
			queue.offer(new int[] {num-1,cur[1]+1});
			queue.offer(new int[] {num+1,cur[1]+1});
		}
		return cur[1];
	}
}
