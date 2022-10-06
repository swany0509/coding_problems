
//package s0930.bj_1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//  전역변수 생성
	static int K,W,H;
	static boolean map[][];
	static boolean visited[][][];
	static int dx[] = {1,-1,0,0,-1,-2,-2,-1,1,2,2,1};
	static int dy[] = {0,0,1,-1,-2,-1,1,2,-2,-1,1,2};
	// 큐에 넣을 블록 클래스
	static class Block{
		int x, y, time, kmove;

		public Block(int x, int y, int time, int kmove) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.kmove = kmove;
		}		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new boolean[H][W];
		visited = new boolean[H][W][K+1];
		// 벽일 경우를 체킹하기 위해  map에 표시
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				switch(st.nextToken()) {
				case "1":
					map[i][j] = true;
					break;
				default:
					break;
				}
			}
		}
		
		System.out.println(bfs());
		
	}
	// bfs 알고리즘
	static int bfs() {
		Queue<Block> queue = new LinkedList<>();
		queue.offer(new Block(0,0,0,0));
		visited[0][0][0] = true;
		while(!queue.isEmpty()) {
			Block cur = queue.poll();
			
			// 도착지점이라면 현재 시간 반환
			if(cur.x == H-1 && cur.y == W-1) {
				return cur.time;
			}
			//12방 탐색
			for (int k = 0; k < 12; k++) {
				int ii = cur.x + dx[k];
				int jj = cur.y + dy[k];
				// 범위가 벗어나거나, 벽이거나, 갈 곳이 벽이라면 넘어감
				if(!isRange(ii,jj) || map[ii][jj] ) continue;
				
				// 4방인 경우 바로 방문처리하고 이동
				if(k<4 && !visited[ii][jj][cur.kmove]) {
					queue.offer(new Block(ii,jj,cur.time+1,cur.kmove));
					visited[ii][jj][cur.kmove] = true;
				}
				// 말처럼 이동하는 경우 이동할 경우의 수 위치가 미방문인 경우 이동
				else if (cur.kmove < K && !visited[ii][jj][cur.kmove+1]) {
					queue.offer(new Block(ii,jj,cur.time+1,cur.kmove+1));
					visited[ii][jj][cur.kmove+1] = true;
				}
			}
			
			
		}
		// 큐가 빌 때까지 결과가 안나온다면 결과가 없다는 거다.
		return -1;
	}
	static boolean isRange(int x, int y) {
		if(x<0||x>=H||y<0||y>=W) return false;
		return true;
	}
}
