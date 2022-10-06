import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// SWEA D4 보급로
public class Solution {
	// 전역변수 선언
	static int TC,N,map[][],visited[][];
	static StringBuilder sb;
	static String buffer;
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	// 큐에 들어갈 블록 변수 선언
	static class Block{
		int x, y, value;

		public Block(int x, int y, int value) {
			super();
			this.x = x;
			this.y = y;
			this.value = value;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			visited = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], Integer.MAX_VALUE);
				buffer = br.readLine().trim();
				for (int j = 0; j < N; j++) {
					map[i][j] = buffer.charAt(j) - '0';
				}
			}
			// bfs로 결과 추가
			sb.append("#" + t + " " + bfs() + "\n");
			
		}
		
		System.out.println(sb);
		
		br.close();
	}
	// 보급로를 탐색하는 bfs 탐색 알고리즘
	// 모든 경우를 탐색한다.
	static int bfs() {
		int least = Integer.MAX_VALUE;
		Queue<Block> queue = new LinkedList<>();
		queue.offer(new Block(0,0,0));
		visited[0][0] = 0;
		while(!queue.isEmpty()) {
			Block cur = queue.poll();
			
			if(cur.value > least) continue;
			if(cur.x == N-1 && cur.y == N-1) {
				least = Math.min(least, cur.value);
				continue;
			}
			
			for (int k = 0; k < 4; k++) {
				int ii = cur.x + dx[k];
				int jj = cur.y + dy[k];
				
				if(ii<0 || ii >= N || jj < 0 || jj >= N) continue;
				
				// 가고자 하는 곳의 값이 현재 탐색한 값보다 크다면 최신화 하고 큐에 추가한다.
				int target = cur.value + map[ii][jj];
				if(target < visited[ii][jj]) {
					visited[ii][jj] = target;
					queue.offer(new Block(ii,jj,target));
				}
			}
			
		}
		// 최솟값을 반환한다.
		return least;
	}
	
}
