//package s0824.bj_10026;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 백준 10026 적록색약
// R 1 G 2 B 3 RG 4
public class Main {
	// 전역변수 선언
	static int N,count,RGcount;
	static int[][] map,RGmap;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String buffer = "";
		N = Integer.parseInt(br.readLine());
		// 기본 맵과 적록색약의 맵 생성
		map = new int[N][N];
		RGmap = new int[N][N];
		// 맵 입력
		for (int i = 0; i < N; i++) {
			buffer = br.readLine().trim();
			for (int j = 0; j < N; j++) {
				switch(buffer.charAt(j)) {
				// 색약의 경우는 RG가 같아보이므로 같은 값 4를 넣어준다.
				case'R':
					map[i][j] = 1;
					RGmap[i][j] = 4;
					break;
				case'G':
					map[i][j] = 2;
					RGmap[i][j] = 4;
					break;
				case'B':
					map[i][j]= RGmap[i][j] = 3;
					break;
				default:
					break;
				}
			}
		}
		// 하나씩 탐색하면서 0이 아닌 값이 나올때마다 bfs로 같은 구역을 모두 0으로 바꿔주고
		// BFS 호출 횟수만큼 증가시킨다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] != 0) {
					bfs(map, i, j, map[i][j]);
					count++;
				}
				if(RGmap[i][j] != 0) {
					bfs(RGmap, i, j, RGmap[i][j]);
					RGcount++;
				}
			}
		}
		
		System.out.println(count + " " + RGcount);
		
	}
	// 범위 내인지 판별하는 함수
	static boolean isRange(int i, int j) {
		if(i<0||i>=N||j<0||j>=N) return false;
		return true;
	}
	// bfs 알고리즘
	static void bfs(int[][] arr, int si, int sj, int key) {
		// 방문배열 선언, 큐 생성, 루트노트 생성
		visited = new boolean[N][N];
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(si,sj));
		
		while(!queue.isEmpty()) {
			// 하나 빼서 방문처리하고 0으로 세팅
			Point cur = queue.poll();
			visited[cur.x][cur.y] = true;
			arr[cur.x][cur.y] = 0;
			
			// 4방탐색
			for (int k = 0; k < 4; k++) {
				int ii = cur.x + dx[k];
				int jj = cur.y + dy[k];
				
				// 같은 값이면서 방문가능한 노드면 추가
				if(isRange(ii,jj) && arr[ii][jj] == key && !visited[ii][jj]) {
					queue.offer(new Point(ii,jj));
					visited[ii][jj] = true;
				}
			}
		}
	}
}
