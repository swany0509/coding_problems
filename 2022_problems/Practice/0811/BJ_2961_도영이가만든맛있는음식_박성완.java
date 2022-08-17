//package s0811.bj_2961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2961 도영이가 만든 맛있는 음식
public class Main {
	// 전역변수 선언
	static int N, better;
	static Ingredient[] foods;
	static boolean[] isVisited;
	// 재료 클래스 생성
	static class Ingredient{
		public int sour; // 신맛 : 곱으로 취합
		public int bitter; // 쓴맛 : 합으로 취합
		public Ingredient(int sour, int bitter) {
			super();
			this.sour = sour;
			this.bitter = bitter;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// N 입력 후 각종 변수 객수 초기화
		N = Integer.parseInt(br.readLine());
		foods = new Ingredient[N];
		isVisited = new boolean[N];
		// better는 차이의 최소값. 초기는 int의 최대값으로 둔다.
		better = Integer.MAX_VALUE; 
		// 데이터 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			foods[i] = new Ingredient(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		// 총 가짓수는 nCr 에서 nC1 ~ nCr의 합이다.
		for (int i = 1; i <= N; i++) {
			combination(0,0,i,1,0);
		}
		
		System.out.println(better);
	}
	// 조합 알고리즘
	public static void combination(int start, int count, int amount, int cur_s, int cur_b) {
		if(count == amount) {
			int temp = Math.abs(cur_s - cur_b);
			better = better > temp ? temp : better;
			return;
		}
		for (int i = start; i < N; i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				// 중간에 int의 범위를 벗어나는 계산이 있나 해서 반환하는 코드를 넣었지만 벗어나는 테케가 없었다.
//				if((double)foods[i].sour*cur_s > Integer.MAX_VALUE) return;
				combination(i+1, count+1, amount,foods[i].sour*cur_s,foods[i].bitter+cur_b);
				isVisited[i] = false;
			}
		}
	}
}
