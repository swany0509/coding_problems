
//package S0802.d3_2805;

import java.util.Scanner;
// D3 2805 농작물 수확하기
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테스트 케이스 입력
		int TC = sc.nextInt();
		
		for (int t = 1; t <= TC; t++) {
			int N = sc.nextInt();
						
			// 결과값
			int value = 0;
			// 투 포인터 사용 -> 증가했다가 감소하는 로직
			int start = N/2;
			int ended = N/2;
			// 증가 -> 감소로 방향을 바꾸는 스위치
			boolean swit = true;
			
			for (int i = 0; i < N; i++) {
				// 한 단어씩 받아서 작업
				String data = sc.next();
				for (int j=start; j<=ended;j++) {
					// character 클래스의 함수를 이용해 접근 후 int로 변환
					value+= Character.getNumericValue(data.charAt(j));
				}
				
				// 끝에 도달했으면 스위치 변환
				if (start==0) swit=false;
				
				// 스위치에 따라 증감문 변화
				if (swit) {
					start--;ended++;
				}else {
					start++;ended--;
				}
				
			}
			// 결과 출력
			System.out.println("#"+t+" "+value);
		}

	}

}
