//package s0810.bj_17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 17406 배열돌리기 4
public class Main {
	// 전역변수 선언
	static int N,M,K,minValue;
	static int[][] map, command;
	static boolean[] isVisited;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N,M,K 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// map 선언 및 입력
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// command 선언 및 입력
		command = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			command[i][0] = Integer.parseInt(st.nextToken())-1;
			command[i][1] = Integer.parseInt(st.nextToken())-1;
			command[i][2] = Integer.parseInt(st.nextToken());
		}	
		// 순열을 생성하기 위해 방문했는지 판별하는 배열
		isVisited = new boolean[K];
		minValue = Integer.MAX_VALUE;
		// dfs로 완전탐색을 한다.
		dfs(0,0);
		
		System.out.println(minValue);
	}
	// 한칸씩 앞으로 이동하는 회전 알고리즘.
	static int[][] rotate(int r, int c , int s) {
		while (s > 0) {
			int first = map[r-s][c-s];
			for (int x = r-s; x < r+s; x++) map[x][c-s] = map[x+1][c-s];
			for (int y = c-s; y < c+s; y++) map[r+s][y] = map[r+s][y+1];
			for (int x = r+s; x > r-s; x--) map[x][c+s] = map[x-1][c+s];
			for (int y = c+s; y > c-s+1; y--) map[r-s][y] = map[r-s][y-1];
			map[r-s][c-s+1] = first;
			s--;
		}
		return map;
	}
	
	//회전을 되돌리는 알고리즘.
	static int[][] rotateUndo(int r, int c , int s) {
		while (s > 0) {
			int first = map[r-s][c-s];
			for (int y = c-s; y < c+s; y++) map[r-s][y] = map[r-s][y+1];
			for (int x = r-s; x < r+s; x++) map[x][c+s] = map[x+1][c+s];
			for (int y = c+s; y > c-s; y--) map[r+s][y] = map[r+s][y-1];
			for (int x = r+s; x > r-s+1; x--) map[x][c-s] = map[x-1][c-s];
			map[r-s+1][c-s] = first;
			s--;
		}
		return map;
	}
		
	// 배열 연산 계산 함수
	static int arrayCalc(int[][] arr) {
		int minn = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for(int j : arr[i]) sum += j;
			minn = minn > sum ? sum : minn;
		}
		return minn;
	}
	// dfs 알고리즘
	static void dfs(int start,int count) {
		// K번째로 연산을 했으면 값을 계산하고 최신화한다.
		if(count==K) {
			int value = arrayCalc(map);
			minValue  = minValue > value ? value : minValue;
		}else {
			// 그 경우가 아니면 순열 방식으로 탐색한다.
			for (int i = 0; i < K; i++) {
				if(!isVisited[i]) {
					isVisited[i] = true;
					// 아직 방문하지 않은 곳이라면 배열을 돌려서 다음  dfs로 넘긴다.
					rotate(command[i][0],command[i][1],command[i][2]);
					dfs(i+1,count+1);
					// 계산을 마치고 돌아오면 다음 연산을 위해 배열을 되돌린다.
					rotateUndo(command[i][0],command[i][1],command[i][2]);
					isVisited[i] = false;
				}
			}
		}
	}
}
