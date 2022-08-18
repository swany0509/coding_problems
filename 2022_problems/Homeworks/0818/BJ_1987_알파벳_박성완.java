package s0818.bj_1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1987 알파벳
public class Main {
	// 전역변수 선언
	static int R,C, maxx,x,y;
	static char[][] map;
	static boolean isVisited[];
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// R,C 입력받고 각종 변소 초기화
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		maxx = 0;
		map = new char[R][C];
		// 알파벳 방문 배열
		isVisited = new boolean[26];
		// map 입력
		for (int i = 0; i < R; i++)	map[i] = br.readLine().toCharArray();
		// 초기위치
		x = 0; y = 0;
		// 백트래킹 알고리즘
		backTracking(0,0,1);
		// 최대값 출력
		System.out.println(maxx);
		
	}
	// 가능한 경우인지 판별하는 함수
	static boolean isValid(int i, int j) {
		// 해당 위치가 맵 밖이거나, 이미 탐색한 알파벳이면 false
		if( i < 0 || i >= R || j < 0 || j >= C || isVisited[map[i][j] - 'A']) return false;
		return true;
	}
	// 백트래킹 알고리즘
	static void backTracking(int i, int j, int cnt) {
		// 방문으로 처리
		isVisited[map[i][j] - 'A'] = true;
		// 26개를 다 돌았다면 더 계산할 필요가 없다.
		if(cnt == 26) {
			maxx = 26;
			return;
		}
		// 앞으로 갈 길이 있는지 판별할 변수
		boolean able = false;
		// 4방향으로 갈 수 있는 곳은 간다.
		for (int k = 0; k < 4; k++) {
			int ix = i + dx[k]; int jy = j + dy[k];
			if(isValid(ix,jy)) {
				// 가능한 경우면 백트래킹 호출
				able = true;
				backTracking(ix,jy,cnt+1);
				// 사용 후 반환
				isVisited[map[ix][jy] - 'A'] = false;
			}
		}
		// 한번도 간 곳이 없다면 계산처리
		if(!able) {
			maxx = maxx < cnt ? cnt : maxx;
		}
	}
}
