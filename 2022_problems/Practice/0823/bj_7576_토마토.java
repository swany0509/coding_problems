package s0823.bj_7576;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 백준 7576 토마토
public class Main {
	// 전역변수 선언
	static int N,M,unfresh,days;
	static int map[][];
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static Queue<Point> queue,near;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 행 열이 반대다. 잘 입력받자.
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		// 선언을 반대로 해주어야 한다.
		map = new int[N][M];
		// 큐와 근처 큐 를 선언한다.
		queue = new LinkedList<>();
		near = new LinkedList<>();
		// 데이터를 입력받는다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				switch(Integer.parseInt(st.nextToken())) {
				// 1이면 먼저 탐색해주어야 하므로 큐에 먼저 넣는다.
				case 1:	
					queue.offer(new Point(i,j));
					map[i][j] = 1;
					break;
				// -1이면 없다고 세팅해준다.
				case -1:
					map[i][j] = -1;
					break;
				// 0이면 갯수를 증가시킨다.
				case 0:
					unfresh++;
					break;
				default:
					break;
				}
			}
		}
		// 처음부터  다 익었으면 바로 0 출력
		if(unfresh == 0) System.out.println(0);
		// 아니라면 bfs로 돌고 남은 아직 안익은 토마토가 있다면 -1출력
		else {
			bfs();
			if(unfresh != 0) System.out.println(-1);
			else System.out.println(days);
		}

	}
	/**
	 * bfs 알고리즘
	 */
	static void bfs() {
		// 큐와 초기 값들은 이미 생성, 초기화 필요 x
		// 기본 로직은 큐 안에 있는 요소의 근처 토마토를 near 큐에 넣고
		// 큐가 비었으면 그 이후에 근처 리스트를 다시 큐에 넣고 근처 리스트는 비우고 진행
		// 한 단계씩 (하루씩) 계산한다는 의미
		while(true) {
			// 현재 큐가 빌 때까지 근처 토마토를 near 큐에 추가
			while(!queue.isEmpty()) {
				Point cur = queue.poll();
				
				for (int k = 0; k < 4; k++) {
					int ii = cur.x + dx[k];
					int jj = cur.y + dy[k];
					// 범위 안이면서 아직 안익은토마토라면
					if(isRange(ii,jj) && map[ii][jj] == 0) {
						// 이제 익을거니까 안익은거 -1 해주고
						unfresh--;
						// 근처 큐에 넣고
						near.offer(new Point(ii,jj));
						// 익었다고 표시
						map[ii][jj] = 1;
					}
				}
				
			}
			// 근처 큐가 비었다면 더이상 탐색할 게 없으므로 종료
			if(near.isEmpty()) break;
			// 아니라면 하루를 증가시키고
			days++;
			// 근처 큐를 전부 큐에 넣고
			queue.addAll(near);
			// 근처 큐는 비운다.
			near.clear();
		}
	}
	/**
	 * 범위내인이 판별하는 메소드
	 * @param i
	 * @param j
	 * @return
	 */
	static boolean isRange(int i, int j) {
		if( i < 0 || i >= N || j < 0 || j >= M) return false;
		return true;
	}
	
}
