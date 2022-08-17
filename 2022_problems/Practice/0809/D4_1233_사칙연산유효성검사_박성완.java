package S0809.d4_1233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SWEA D4 1233 [S/W 문제해결 기본] 9일차 - 사칙연산 유효성 검사
public class Solution {
	// 전역변수 선언
	static int TC,N,center;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 테스트케이스
		TC = 10;
		// 조건을 만족하려면  leaf node 만 유효성 검사를 하면 된다.
		// N이 1이 아닌 홀수이고, leaf node만 숫자라면 조건이 유효하다.
		// 따라서 입력만 검사하면 된다.
		// N/2+1 을 기준점으로 이 이상이면 무조건 숫자여야 한다.
		for (int i = 1; i <= TC; i++) {
			boolean isCorrect = true;
			// N입력 및 검사
			N = Integer.parseInt(br.readLine());
			if(N%2==0 || N==1) isCorrect = false;
			center = N/2 + 1;
			// 앞 두 토큰만 입력받음 (사실상 두번째 토큰만 사용)
			for (int j = 1; j <= N; j++) {
				st = new StringTokenizer(br.readLine());
				String n = st.nextToken();
				char c = st.nextToken().charAt(0);
				// 기준점 미만이라면 무조건 연산자
				if(j < center) {
					// 숫자라면 false
					if(c - '0' >= 0) isCorrect = false;
				}
				// 기준점 이상이라면 무조건 숫자
				else {
					// 연산자라면 false
					if(c - '0' < 0) isCorrect = false;
				}
				
			}
			// 결과 추가
			sb.append("#"+i+" ");
			if(isCorrect) sb.append(1 + "\n");
			else sb.append(0 + "\n");
		}
		// 출력
		System.out.println(sb);
		br.close();
	}

}
