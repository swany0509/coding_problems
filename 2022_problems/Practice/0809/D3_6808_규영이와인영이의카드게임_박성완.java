package S0809.d3_6808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SWEA D3 규영이와 인영이의 카드게임
public class Solution {
	// 전역변수 선언
	static int TC , winNum; //테스트케이스, 이긴 숫자
	static int[] facts, kyu; // 팩토리얼배열, 규영이가 고른 숫자
	static boolean[] isVisit; // 방문판별
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 테스트케이스 입력
		TC = Integer.parseInt(br.readLine());
		
		// 팩토리얼 배열 생성
		facts = new int[10];
		facts[0] = 1;
		for (int i = 1; i < 10; i++) facts[i] = i*facts[i-1];
		// 규영이 배열 크기 선언
		kyu = new int[10];
		
		for (int i = 1; i <= TC; i++) {
			// isVisit 초기화
			isVisit = new boolean[19];
			winNum = 0;
			// 규영이가 고른 숫자 받아서 저장사고, isVisit 에 대입
			// 대입 수 false 중에 인덱스 0을 제외하곤 인영이가 고른 숫자다.
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				kyu[j] = Integer.parseInt(st.nextToken());
				isVisit[kyu[j]] = true;
			}
			
			// 재귀 실행
			match(1,0);
			// 결과 저장
			sb.append("#"+i+" " + winNum + " "  + (facts[9]-winNum) + "\n");
		}
		System.out.println(sb);
		br.close();
	}
	static void match(int round,int curScore) {
		// 1~18까지 더하면 171이다. 따라서, 85가 넘으면 이겼다고 취급하고
		// 남은 경우의 수를 더한다.
		if(curScore>85) {
			// 5라운드에 이겼다면 다음 라운드로 6이 넘어오므로 남은 경우의 수는 4! = 24. 즉 10-(넘어온 round) 이다.
			winNum += facts[10-round];
			return;
		}
		// 방문했는지를 판별해 인영이의 숫자 중 하나로 계산하고 다음 라운드로 넘긴다.
		for (int i = 1; i < 19; i++) {
		if(!isVisit[i]) {
			isVisit[i] = true;
			if(kyu[round] > i) match(round+1, curScore + kyu[round] + i);
			else match(round+1,curScore);
			isVisit[i] = false;
			}
		}
	}
}
