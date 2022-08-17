package s0817.sw_4012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 22,704 kb	실행시간 176 ms
// SWEA 4012 요리사
public class Solution {
	// 전역변수 생성
	static int TC, N, minValue;
	static boolean[] isUsed;
	static int[][] data;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		// 테스트 케이스 입력
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			// N입력받고 변수 초기화
			N = Integer.parseInt(br.readLine());
			data = new int[N][N];
			isUsed = new boolean[N];
			minValue = Integer.MAX_VALUE;
			// 데이터 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) data[i][j] = Integer.parseInt(st.nextToken());
			}
			// 조합 생성
			comb(0,0);
			// 결과 추가
			sb.append("#"+t+ " " + minValue + "\n");
		}
		// 결과 출력
		System.out.println(sb);
		br.close();
	}
	// 값 계산 메소드
	static int calculate() {
		// 선택한 것끼리 배열의 값을 더하고
		int score1 = 0;
		int score2 = 0;
		for (int i = 0; i < N; i++) {
			if(isUsed[i]) {
				for (int j = 0; j < N; j++) {
					if(isUsed[j]) score1 += data[i][j];
				}
			}
			// 선택하지 않은 것 들끼리 더한다.
			else {
				for (int j = 0; j < N; j++) {
					if(!isUsed[j]) score2 += data[i][j];
				}
			}
		}
		// 계산값을 반환한다.
		return (int)Math.abs(score2-score1);
	}
	// 조합 생성 알고리즘
	static void comb(int cnt, int start) {
		// 중간에 값이 0이 나오면 추가 계산을 하지 않는다.
		if(minValue == 0) return;
		// 반틈을 골랐다면 계산 시작
		if(cnt == N/2) {
			// 조합이 반틈이 계산이 완료되도 첫요소가 사용되지 않으면 더이상 조합 계산을 하지 않는다.
			// A요리를 고르면 나머지는  B요리이므로 조합의 경우를 반틈만 계산하면 된다.
			if(!isUsed[0]) return;
			int value = calculate();
			minValue = minValue > value ? value : minValue;
			return;
		}
		// 조합 선택
		for(int i=start; i< N ; i++) {
			if(isUsed[i]) continue;
			isUsed[i] = true;
			comb(cnt+1, i+1);
			isUsed[i] = false;
		}
	}
}
