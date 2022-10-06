package daily.bj_2636;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 메모리 12272	시간 96
// 백준 2636 치즈
public class Main {
	// 전역변수 선언
	static int N,M,bcnt,time, lastRemain;
	static Block[][] map;
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	static Queue<Point> cqueue;
	static class Block{
		short near;
		boolean type;

		public Block(boolean type) {
			super();
			this.near = 0;
			this.type = type;
		}
		
	}
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Block[N][M];
		// 데이터 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				switch(st.nextToken()) {
				case "1":
					map[i][j] = new Block(true);
					bcnt++;
					break;
				default:
					map[i][j] = new Block(false);
					break;
				}
			}
		}
		// bfs 알고리즘 실생
		bfs(0,0);
		// 치즈를 단계적으로 제거하기 위한 큐
		cqueue = new LinkedList<>();
		
		while(bcnt > 0) {
			// 이전 단계에서 남은 치즈를 저장(탈출 후 출력하기 위함)
			lastRemain = bcnt;
			// 2차원 탐색을 하면서 외부 공기와 1보다 더 많으면 제거큐에 추가
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] != null && map[i][j].near>=1) {
						cqueue.offer(new Point(i,j));
					}
				}
			}
			//하나씩 제거하면서 외부공기 최신화
			while(!cqueue.isEmpty()) {
				cheese(cqueue.poll());
				bcnt--;
			}
			// 시간 증가
			time++;
		}
		// 결과 출력
		System.out.println(time);
		System.out.println(lastRemain);
	}
	// bfs 알고리즘 (외부공기 최신화)
	static void bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(x,y));
		map[x][y] = null;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for (int k = 0; k < 4; k++) {
				int ii = cur.x + dx[k];
				int jj = cur.y + dy[k];
				
				if(!isRange(ii,jj) || map[ii][jj] == null) continue;
				// 외부공기를 처리하고 닿은 치즈의 닿은 면적을 증가시켜준다.
				if(map[ii][jj].type) {
					map[ii][jj].near++;
				}else {
					map[ii][jj] = null;
					queue.offer(new Point(ii,jj));
				}
			}
		}
		
	}
	// 치즈가 제거될 때 작동하는 메소드
	static void cheese(Point p) {
		map[p.x][p.y] = null;
		for (int k = 0; k < 4; k++) {
			int ii = p.x + dx[k];
			int jj = p.y + dy[k];
			
			if(!isRange(ii,jj) || map[ii][jj] == null) continue;
			// 4방향 중 치즈가 있으면 닿은 면적 증가
			if(map[ii][jj].type) {
				map[ii][jj].near++;
			}else {
				map[ii][jj] = null;
				bfs(ii,jj);
			}
		}
	}
	// 범위 메소드
	static boolean isRange(int x, int y) {
		if(x<0||x>=N||y<0||y>=M) return false;
		return true;
	}
}
