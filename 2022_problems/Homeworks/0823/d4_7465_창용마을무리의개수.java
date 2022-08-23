package s0823.d4_7465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// SWEA 7465 창용 마을 무리의 개수
public class Solution {
	// 전역변수 선언
	static int TC,N,M,circle;
	static ArrayList<Integer>[] list;
	static StringBuilder sb;
	static boolean visited[];
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		// 테스트 케이스 입력
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			// 무리 수 0으로 초기화
			circle = 0;
			st = new StringTokenizer(br.readLine());
			//n,m 입력하고 각종 변수 초기화
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N+1];
			list = new ArrayList[N+1];
			// 리스트형 배열을 만들고 초기화 (인접 리스트)
			for (int i = 0; i <= N; i++) {
				list[i] = new ArrayList<>();
			}
			// 인접리스트 채우기
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				list[b].add(a);
			}
			// bfs 탐색
			bfs();
			// 결과 추가
			sb.append("#" + t + " " + circle + "\n");
		}
		System.out.println(sb);
		br.close();
	}
	// bfs 알고리즘
	static void bfs() {
		// 1번부터 N번까지 무리를 검색한다.
		for (int i = 1; i <= N; i++) {
			// 먼저 한명을 넣고
			if(visited[i]) continue;
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(i);
			visited[i] = true;
			// 연결된 친구들을 탐색하여 방문하고 방문처리 한다.
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				for(int j = 0, size = list[cur].size(); j < size ; j++) {
					int num = list[cur].get(j);
					if(visited[num])continue;
					queue.offer(num);
					visited[num] = true;
				}
			}
			// 무리를 검색하면 무리수를 1 증가한다.
			circle++;
		}
	}
}
