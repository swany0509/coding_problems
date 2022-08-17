//package S0811.bj_3040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 백준 3040 백설공주와일곱난장이
public class Main {
	// 전역변수 선언
	static int sum;
	static int[] minion;
	static StringBuilder sb;
	static void print() {
		// 0이 아닌 값만 sb에 추가한다.
		for (int i = 0; i < 9; i++) {
			if(minion[i] != 0) sb.append(minion[i]+"\n");
		}
		System.out.println(sb);
		System.exit(0);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 초기 합을 -100으로 두면 다 더했을 때 난장이가 아닌 두 친구만 더해진 값이 나온다.
		minion = new int[9];
		sum = -100;
		// 난장이 키 입력
		for (int i = 0; i < 9; i++) {
			int num = Integer.parseInt(br.readLine());
			minion[i] = num;
			sum += num;
		}
		// 경우의 수가 작으므로 2중포문을 사용한다.
		for (int i = 0; i < 8; i++) {
			for (int j = i+1; j < 9; j++) {
				if(minion[i]+minion[j] == sum) {
					minion[i] = 0; minion[j] = 0;
					// 값을 찾으면 바로 출력하고 종료시킨다.
					print();
				}
			}
		}

	}
}
