package s1005.bj_1194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 백준 1194 달이 차오른다
// 메모리 15424	시간 108
public class Main {
	// 전역변수 생성
	static int N,M;
	static char[][] map;
	static boolean[][][] visited;
	static final char blank = '.';
	static final char wall = '#';
	static final char curr = '0';
	static final char exit = '1';
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	// 플레이어 위치 클래스
	static class Player{
		int x, y, time, keys;

		public Player(int x, int y, int time, int keys) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.keys = keys;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		// 키를 가지고 있는 상태별로 방문 배열 생성(3차원)
		visited = new boolean[N][M][64];
		// 시작위치 얻어오기 위함
		int si = 0;
		int sj = 0;
		for (int i = 0; i < N; i++) {
			String buffer = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = buffer.charAt(j);
				if(map[i][j] == curr) {
					si = i; sj = j;
					map[i][j] = blank;
				}
			}
		}
		// 시작위치를 기준으로 bfs 탐색하고 결과 출력
		System.out.println(bfs(si,sj));
	}
	// bfs 탐색 알고리즘
	static int bfs(int x, int y) {
		// 시작위치 및 큐 초기화
		Queue<Player> queue = new LinkedList<>();
		queue.offer(new Player(x,y,0,0));
		visited[x][y][0] = true;
		while(!queue.isEmpty()) {
			Player cur = queue.poll();
			// 뽑은 위치가 도착점이면 시간 반환
			if(map[cur.x][cur.y] == exit) return cur.time;
			// 4방 탐색
			for (int k = 0; k < 4; k++) {
				int ii = cur.x + dx[k];
				int jj = cur.y + dy[k];
				
				// 범위 밖이면 다음 경우로
				if(!isRange(ii,jj)) continue;
				
				char c = map[ii][jj];
				// 무엇이 있는지에 따라 분기
				switch(c) {
				// 벽이면 아무것도 안함
				case wall:
					break;
				// 빈곳이거나 탈출구면 방문 여부만 체킹하고 방문 안했으면  큐에 넣음
				case blank : case exit: 
					if(!visited[ii][jj][cur.keys]) {
						visited[ii][jj][cur.keys] = true;
						queue.offer(new Player(ii,jj,cur.time+1,cur.keys));
					}
					break;
				// 열쇠위치면 이미 얻은 상태로 방문한 적이 있는지 판별하고 방문 안했다면 큐에 넣음
				case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
					// 해당 키와  or 연산을 하여 그 키를 가지고 있는 비트값 계산
					int key = cur.keys | getBitMask(c);
					if(!visited[ii][jj][key]) {
						visited[ii][jj][key] = true;
						queue.offer(new Player(ii,jj,cur.time+1,key));
					}
					break;
				// 문 위치면 필요한 키가 있는지 검사하고 있으면 큐에 넣음
				case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
					int needed = getBitMask(c);
					if( (cur.keys & needed) != 0  && !visited[ii][jj][cur.keys]) {
						visited[ii][jj][cur.keys] = true;
						queue.offer(new Player(ii,jj,cur.time+1,cur.keys));
					}
					break;
				}
			}
		}
		return -1;
	}
	// 해당 키/문에따라 가져와야 할 비트마스크 값 얻어오기
	static int getBitMask(char c) {
		switch(c) {
		case 'a': case 'A':
			return 1; // 1
		case 'b': case 'B':
			return 2; // 10
		case 'c': case 'C':
			return 4; // 100
		case 'd': case 'D':
			return 8; // 1000
		case 'e': case 'E':
			return 16;// 10000
		case 'f': case 'F':
			return 32;// 100000
		default:
			break;
		}
		return 0;
	}
	// 범위 내인지 판별
	static boolean isRange(int x, int y) {
		if(x<0||x>=N||y<0||y>=M) return false;
		return true;
	}
}
