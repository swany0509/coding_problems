package S0810.bj_16935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 백준 16935 배열돌리기 3
public class Main {
	// 전역변수 선언
	static int N,M,R;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N,M,R 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		// map 선언 및 입력
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		// R만큼 연산을 실행
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			switch(Integer.parseInt(st.nextToken())) {
			case 1:
				upDown();
				break;
			case 2:
				leftRight();
				break;
			case 3:
				rotateRight();
				break;
			case 4:
				rotateLeft();
				break;
			case 5:
				partitionRight();
				break;
			case 6:
				partitionLeft();
				break;
			default:
				break;
			}
		}
		
		for(int[] arr:map) {
			for(int a:arr) System.out.print(a+" ");
			System.out.println();
		}
		
	}
	// 행의 반틈만 접근하여 반대편 수와 교체한다.
	static void upDown() {
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M; j++) {
				int tmp = map[i][j];
				map[i][j] = map[N-1-i][j];
				map[N-1-i][j] = tmp;
			}
		}
	}
	// 열의 반큼만 접근하여 반대편 수와 교체한다.
	static void leftRight() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M/2; j++) {
				int tmp = map[i][j];
				map[i][j] = map[i][M-1-j];
				map[i][M-1-j] = tmp;
			}
		}
	}
	// 행과 열이 역전된 배열을 생성하여 인덱스 탐색을 활용해 돌리고 대입한다.
	static void rotateRight() {
		int[][] tmp = new int[M][N];
		for (int i1 = 0, i2 = 0; i1 < M; i1++,i2++) {
			for (int j1 = 0, j2 = N-1; j1 < N; j1++,j2--) {
				tmp[i1][j1] = map[j2][i2];
			}
		}
		map = tmp.clone();
		int min = M;
		M = N;N = min;
	}
	// 행과 열이 역전된 배열을 생성하여 인덱스 탐색을 활용해 돌리고 대입한다.
	static void rotateLeft() {
		int[][] tmp = new int[M][N];
		for (int i1 = 0, i2 = M-1; i1 < M; i1++,i2--) {
			for (int j1 = 0, j2 = 0; j1 < N; j1++,j2++) {
				tmp[i1][j1] = map[j2][i2];
			}
		}
		map = tmp.clone();
		int min = M;
		M = N;N = min;
	}
	// 전체의 1/4만 탐색하여 4요소를 특정하고 돌린다.
	static void partitionRight() {
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				int tmp = map[i][j];
				map[i][j] = map[i+N/2][j];
				map[i+N/2][j] = map[i+N/2][j+M/2];
				map[i+N/2][j+M/2] = map[i][j+M/2];
				map[i][j+M/2] = tmp;
			}
		}
	}
	// 전체의 1/4만 탐색하여 4요소를 특정하고 돌린다.
	static void partitionLeft() {
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				int tmp = map[i][j];
				map[i][j] = map[i][j+M/2];
				map[i][j+M/2] = map[i+N/2][j+M/2];
				map[i+N/2][j+M/2] = map[i+N/2][j];
				map[i+N/2][j] = tmp;
			}
		}
	}
}
