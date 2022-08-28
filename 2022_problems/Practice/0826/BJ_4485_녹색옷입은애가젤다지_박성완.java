//package s0826.bj_4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 메모리 18288	시간 192
// 백준 4485 녹색옷입은애가젤다지
public class Main {
	// 전역변수 선언
	static int N,count,least,D[][], map[][];
	static boolean visited[][];
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	static StringBuilder sb;
	// 2차원 정보를 담고 있는 정점 정보 클래스
	static class Vertex implements Comparable<Vertex>{
		int x, y, weight;

		public Vertex(int x, int y, int weight) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weight - o.weight;
		}
		
	}
	public static void main(String[] args) throws IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		// 출력할 때 씌여질 숫자
		count = 1;
		// 0이 나올때까지 실행
		while(true) {
			// N을 입력받고 각종 변수 초기화
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			least = Integer.MAX_VALUE;
			
			map = new int[N][N];
			visited = new boolean[N][N];
			D = new int[N][N];
			// 입력을 2차원 Vertex 배열이라고 생각하고 받는다.
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 2차원 탐색을 다익스트라로 실행한다.
			dijkstra();
			// 결과를 추가한다.
			sb.append("Problem "+(count++)+": "+least + "\n");
		}
		
		
		System.out.println(sb);
		br.close();
	}
	// 범위 판별 함수
	static boolean isRange(int x, int y) {
		if(x<0||x>=N||y<0||y>=N) return false;
		return true;
	}
	// 2차원 다익스트라 탐색 알고리즘
	static void dijkstra() {
		// D배열을 초기화해준다.
		for (int i = 0; i < N; i++) {
			Arrays.fill(D[i], Integer.MAX_VALUE);
		}
		// 첫번째 요소 초기화
		D[0][0] = map[0][0];
		// 우선순위 큐 생성
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		// 첫 요소를 넣어준다.
		queue.offer(new Vertex(0,0,D[0][0]));
		
		while(!queue.isEmpty()) {
			// 하나를 빼서
			Vertex cur = queue.poll();
			// 이미 방문한 정점이면 넘어감
			if(visited[cur.x][cur.y]) continue;
			// 도착점이라면 비용을 반환하고 넘어감
			if(cur.x == N-1 && cur.y == N-1) {
				least = cur.weight;
				break;
			}
			// 방문 처리
			visited[cur.x][cur.y] = true;
			// 4방향 탐색
			for (int k = 0; k < 4; k++) {
				int ii = cur.x + dx[k];
				int jj = cur.y + dy[k];
				// 범위 밖이거나 이미 방문했다면 넘어감
				if( !isRange(ii,jj) || visited[ii][jj]) continue;
				
				// 현재 해당 위치의 비용이, 현재 정점까지의 비용에 인접한 정점의 방문 비용보다 싸다면 갱신하고
				// 해당 정점 위치를 큐에 넣음
				if(D[ii][jj] > D[cur.x][cur.y] + map[ii][jj]) {
					D[ii][jj] = D[cur.x][cur.y] + map[ii][jj];
					queue.offer(new Vertex(ii,jj,D[ii][jj]));
				}
			}
			
			
		}
	}
}
