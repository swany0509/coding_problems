package s0823.bj_13023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// 백준 13023 ABCDE
// 메모리 16916 시간 208
public class Main {
	// 전역변수 선언
	static int N,M, ans;
	static boolean visited[], end;
	static ArrayList<Integer>[] list;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N,M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// arraylist 배열 선언 및 생성
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 관계 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		// 0~n-1까지 dfs 실행
		for (int i = 0; i < N; i++) {
			if(end) break;
			visited = new boolean[N];
			visited[i] = true;
			dfs(0,i);
		}
		
		System.out.println(ans);
	}
	/**
	 * dfs 알고리즘
	 * @param cnt
	 * @param cur
	 * 6
	 */
	static void dfs(int cnt,int cur) {
		// 탐색이 완료되었으면 탈출
		if(end) return;
		// 4개면 간선을 4개를 내려갔다는 말이므로 조건을 만족
		if(cnt==4) {
			end = true;
			ans = 1;
			return;
		}
		// 현재 노드의 관계를 탐색하면서 dfs 진행
		for (int i = 0,size = list[cur].size(); i < size; i++) {
			int num = list[cur].get(i);
			if(!visited[num]) {
				visited[num] = true;
				dfs(cnt+1,num);
				visited[num] = false;
			}
		}
	}
}
