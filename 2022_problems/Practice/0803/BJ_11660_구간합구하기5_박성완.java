//package S0803.bj_11660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11660  구간 합 구하기 5
public class Main {
	// 배열과 배열 크기, 중간변수 전역변수로 선언
	public static int[][] arr;
	public static int N,M;
	public static int x1,y1,x2,y2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer in = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		// N,M 입력
		N = Integer.parseInt(in.nextToken());
		M = Integer.parseInt(in.nextToken());
		
		// 배열은 한 단계 크게 선언
		arr = new int[N+1][N+1];
		
		// 입력받음과 동시에 누적합 입력
		for (int i = 0; i < arr.length; i++) {
			// 0행이 아니면 입력받음
			if (i != 0) in = new StringTokenizer(br.readLine());
			// 0행이거나 0열이면 0으로 고정하고, 아닐 경우엔 입력값 누적으로 저장
			for (int j = 0; j < arr.length; j++) {
				if(i==0 || j==0) arr[i][j] = 0;
				else arr[i][j] = arr[i][j-1] + arr[i-1][j] - arr[i-1][j-1] + Integer.parseInt(in.nextToken());
			}
		}
		
		// 시작과 끝 값을 입력받아 조건에 맞게 출력.
		for (int i = 0; i < M; i++) {
			in = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(in.nextToken()); 
			y1 = Integer.parseInt(in.nextToken());
			x2 = Integer.parseInt(in.nextToken());
			y2 = Integer.parseInt(in.nextToken());
			
			sb.append((arr[x2][y2]-arr[x2][y1-1]-arr[x1-1][y2]+arr[x1-1][y1-1])+ "\n");
		}
		System.out.println(sb);
		br.close();
	}
}
