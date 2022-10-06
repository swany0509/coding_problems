//package daily.a3_sw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 86,180kb  시간322ms
//A형 연습문제 3번 Shuffle-O-Matic
public class Solution {
	// 전역 변수 선언
	static int TC,N,least,cards[];
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			// N입력받고 변수 초기화
			least = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			
			// 카드 입력
			cards = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) cards[i] = Integer.parseInt(st.nextToken());
			// 순열 알고리즘 실행
			perm(0,cards);
			
			// 결과 추가
			if(least == Integer.MAX_VALUE) least = -1;
			sb.append("#" + t + " " + least + "\n");
			
		}
		System.out.println(sb);
		br.close();
	}
	// 순열 알고리즘
	public static void perm(int cnt, int[] current) {
		// 오름차순이거나 내림차순이면 최솟값 갱신
		if(isAscend(current) || isDescend(current)) {
			least = Math.min(least, cnt);
			return;
		}
		// 다음 cnt가 최솟값을 넘거나 6이 되면 계산하지 않고 돌아간다.
		if( least < cnt+1 || cnt == 5) return;
		
		for (int i = 0; i < N; i++) {
			// 복사된 배열 객체를 이용하여 바로 다음 순열로 넘긴다.
			perm(cnt+1,shuffle(current.clone(),i));
		}
		
	}
	// 섞어주는 메소드
	// 6장인 경우
	//0;;  1  2 (3  4) 5  6
	//1;;  1 (2  4)(3  5) 6
	//2;; (1  4)(2  5)(3  6)
	//3;;  4 (1  5)(2  6) 3
	//4;;  4  5 (1  6) 2  3
	//5;;  4  5  6  1  2  3
	// 이 순서이다. 
	// 괄호가 그려진 짝 까리 교환하면 다음 경우로 이동한다.
	// 입력 명령에 써진 숫자만큼 반복문으로 실행하여 교환을 진행한다.
	public static int[] shuffle(int[] arr, int command) {
		// 첫 시작 위치는 가운데이다.
		int start = N/2-1;
		// 증감식을 바꿔줄 중간변수
		boolean swit = true;
		// 교환하는 횟수
		int k = 1;
		for (int i = 0; i < command; i++) {
			// 교환
			for (int j = start,jj = 0; jj<k; j+=2,jj++) {
				int temp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = temp;
			}
			// 처음에는 시작위치 감소, 횟수 증가
			if(swit) {
				start--;
				k++;
			}
			// 나중엔 시작위치 증가, 횟수 감소
			else {
				start++;
				k--;
			}
			// start가 0번위치면 다음부턴 증감 연산을 달리 하기 위해 중간변수 변화
			if(start==0) swit = false;
		}
		
		return arr;
	}
	// 배열이 오름차순인지 확인
	static boolean isAscend(int[] arr) {
		for (int i = 0, size = arr.length; i < size-1; i++) {
			if(arr[i] > arr[i+1]) return false;
		}
		return true;
	}
	// 배열이 내림차순인지 확인
	static boolean isDescend(int[] arr) {
		for (int i = 0, size = arr.length; i < size-1; i++) {
			if(arr[i] < arr[i+1]) return false;
		}
		return true;
	}
}
