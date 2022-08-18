//package s0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 3109 빵집
public class Main3 {
	// 전역변수 선언
	static int R,C,count;
	static boolean map[][], found;
	static int[] dx = {-1,0,1};
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// R,C 입력
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// 맵 정보를 boolean 배열로 선언
		map = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String buffer =  br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = buffer.charAt(j) == '.' ? false : true;
			}
		}
		// 각 행마다 dfs 실행
		for (int i = 0; i < R; i++) {
			found = false;
			dfs(i,0);
		}
		// 갯수 출력
		System.out.println(count);

	}
	// 범위가 유효하고 아직 방문하지 않은 곳인지 판별
	public static boolean isValid(int i, int j) {
		if(i<0 || i >= R || map[i][j]) return false;
		return true;
	}
	// dfs
	public static void dfs(int i, int j) {
		// 마지막 위치까지 왔다면
		if(j==C-1) {
			// 갯수를 증가시키고 찾음 변수를 바꾼다.
			map[i][j] = true;
			count++;
			found = true;
			return;
		}
		// 세 경로로 탐색한다.
		for (int k = 0; k < 3; k++) {
			int ii = i+dx[k];
			// 세 경우중 하나라도 유요하면 현재 위치를 방문으로 처리하고 다음 방문을 진행한다.
			if(isValid(ii,j+1)) {
				map[i][j] = true;
				dfs(ii,j+1);
				// 이미 찾았다면 탈출
				if(found) return;
			}
		}
	}
}
