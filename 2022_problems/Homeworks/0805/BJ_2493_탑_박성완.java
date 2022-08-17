package s0805.bj_2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 2493 탑
public class Main {
	// 전역변수 선언
	static int N;
	static Stack<int[]> tower;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식 설정
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 타워 갯수 입력
		N = Integer.parseInt(br.readLine());
		// 스택 생성
		tower = new Stack<>();
		// 타워 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		// cnt 초기 생성
		int cnt = 1;
		
		// 토큰마다 비교
		while(cnt <= N) {
			// 수를 먼저 입력받고
			int current = Integer.parseInt(st.nextToken());
			// 스택이 안비었다면
			while(!tower.isEmpty()) {
				// 현재 수가 top 이하일때
				if(tower.peek()[1] >= current) {
					// 현재 타워 최상단의 인덱스를 출력한다.
					System.out.print(tower.peek()[0] + " ");
					break;
				}
				// 아니면 계속 스택을 하나씩 뺀다.
				tower.pop();
			}
			// 비었다면 0을 추가한다.
			if(tower.isEmpty()) System.out.print(0 + " ");	
			// 타워를 추가한다.
			tower.push(new int[] {cnt++,current});
		}
	}
}