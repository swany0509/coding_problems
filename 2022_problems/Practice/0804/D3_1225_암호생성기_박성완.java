package s0804.d3_1225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// SWEA D3 1225 [S/W 문제해결 기본] 7일차 - 암호생성기
public class Solution {
	// 전역 변수 선언
	static int TC = 10;
	static int size = 8;
	static int[] numbers = new int[size];
	public static void main(String[] args) throws IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 10회반복
		for (int t = 0; t < TC; t++) {
			int N = Integer.parseInt(br.readLine());
			// 숫자를 입력받아 배열에 저장한다.
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < size; i++) numbers[i] = Integer.parseInt(st.nextToken());
			// cnt : 더해지는 숫자 1~5반복 ; idx : 인덱스 탐색 0~7반복
			int cnt = 1; int idx = 0;
			
			// 1~5로 반복되는 cnt를 numbers을 탐색하며 계속 뺀다. (0~7반복)
			// 이때 0 이하값이 나오면 0으로 저장하고 반복문을 탈출한다.
			// cnt가 6이되면 1로 바꿔준다.
			while(true) {
				if (cnt==6) cnt=1;
				numbers[idx] -= cnt++;
				if (numbers[idx] <= 0) {
					numbers[idx] = 0;
					break;
				}
				idx = (idx+1)%size;
			}
			
			// idx값을 기준으로 뒤를 먼저 stringbuilder에 넣고, 다시 앞부터 idx까지 추가한다.
			sb.append("#"+N+" ");
			for(int i = idx+1; i < size; i++) sb.append(numbers[i]+" ");
			for(int i = 0; i <= idx ; i++) sb.append(numbers[i]+" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
