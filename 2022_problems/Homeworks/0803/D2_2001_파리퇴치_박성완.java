//package s0803.d2_2001;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution{
	public static int T;
	public static void main(String args[]) throws Exception{
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer in = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		// 테스트케이스 선언
		T = Integer.parseInt(in.nextToken());
		
		for (int t = 1; t<=T;t++) {
			in = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(in.nextToken());
			int M = Integer.parseInt(in.nextToken());
			
			// 배열 생성 및 입력
			int[][] data = new int[N][N];
			
			for (int i = 0; i<N ; i++) {
				in = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					data[i][j] = Integer.parseInt(in.nextToken());
				}
			}
			
			// 넓이 선언
			int maxx = 0;
			
			// 시작부터 끝까지 탐색하여 탐색 영역을 그리고 더한다.
			for (int i = 0; i<=N-M ; i++) {
				for(int j = 0; j<=N-M; j++) {
					// 각 경우의 수를 구하여 최대값과 비교한다.
					int tmp = 0;
					for (int ii = i; ii<i+M ; ii++) {
						for(int jj = j; jj<j+M; jj++) {
							tmp += data[ii][jj];
						}
					}
					// 최대값 최신화.
					maxx = Math.max(tmp, maxx);					
				}
			}
			// 결과 더함.
			sb.append("#"+t+" "+maxx+"\n");
		}
		System.out.println(sb);
	}
}