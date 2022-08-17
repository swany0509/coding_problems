
//package S0810.bj_16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 백준 16926 배열돌리기1
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
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 최 상단과 최 하단 초기화
		int x1 = 0;	int y1 = 0; int x2 = N-1;	int y2 = M-1;
		// 이 알고리즘은 최 상단과 최 하단을 정하고 두 점을 기준으로 테두리를 회전하는 알고리즘이다.
		// 한 테두리의 회전을 마치면 안쪽 테두리로 한 겹 씩 이동하고
		// 가운데서 만나면 회전을 종료한다.
		while(x1<x2 && y1<y2) {
			// 각 테두리마다 회전 주기의 나머지를 구하고 그 만큼만 회전한다.
			for (int i = 0; i < R%(2*((x2+y2)-(x1+y1))); i++) ratate(x1, y1, x2, y2);
			x1++;y1++;x2--;y2--;
		}
		
		for(int[] arr:map) {
			for(int a:arr) System.out.print(a+" ");
			System.out.println();
		}
		
		
	}
	// 한칸씩 이동하는 회전 알고리즘.
	static void ratate(int x1,int y1, int x2, int y2) {
		int first = map[x1][y1];
		for (int y = y1+1; y <= y2; y++) map[x1][y-1] = map[x1][y];
		for (int x = x1+1; x <= x2; x++) map[x-1][y2] = map[x][y2];
		for (int y = y2-1; y >= y1; y--) map[x2][y+1] = map[x2][y];
		for (int x = x2-1; x > x1; x--) map[x+1][y1] = map[x][y1];
		map[x1+1][y1] = first;
	}
}
