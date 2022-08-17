package s0808.d3_9229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// SWEA 9229 한빈이와 spot mart
public class Solution {
	// 전역변수 선언
	static int TC,N,M;
	static int[] snacks;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 테스트케이스 입력
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			// N,M 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
			// 데이터 받아서 int 배열로 변경
			st = new StringTokenizer(br.readLine());
			snacks = new int[N];
			for (int i = 0; i < N; i++) snacks[i] = Integer.parseInt(st.nextToken());
			
			// sum을 초기화하고 완전탐색을 진행한다.
			int sum = -1;
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					int tmp = snacks[i] + snacks[j];
					// 더한 값이 M이하라면 sum 갱신
					if(tmp <= M) sum = sum < tmp ? tmp : sum;
				}
			}
			// 값을 추가한다.
			sb.append("#" + t + " " + sum + "\n");
			
		}
		System.out.println(sb);
	}

}
