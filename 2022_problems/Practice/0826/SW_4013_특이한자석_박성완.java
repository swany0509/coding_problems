package s0826.sw_4013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
// N극 0 false S극 1 true
// 시계방향  1 true 반시계방향 -1 false;
// 메모리 18372 시간 112
// SWEA 4013 특이한 자석
public class Solution {
	// 전역변수 선언
	static int scores[] = {1,2,4,8};
	static int TC,K;
	static Wheel[] wheels;
	static StringBuilder sb;
	// 톱니바퀴 클래스 선언
	// 톱니는 시계방향으로 12시부터 세며, 0,1,2,3,4,5,6,7까지 있다.
	static class Wheel{
		// 휠 정보 LinkedList 를 사용하면 Deque처럼 사용하면서 ArrayList처럼 사용할 수 있다.
		LinkedList<Boolean> data;
		// 생성자 내에 한 줄 전체를 넣어서 생성해주는 방식을 취한다.
		public Wheel(String buffer) {
			super();
			data = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(buffer);
			for (int i = 0; i < 8; i++) {
				if(st.nextToken().equals("1")) data.offer(true);
				else data.offer(false);
			}
		}
		// 명령에 따라 왼쪽 혹은 오른쪽으로 넣는다.
		public void roll(boolean clockwise) {
			// 시계방향은 맨 마지막요소를 맨 앞으로 넣으면 된다.
			if(clockwise) {
				data.offerFirst(data.pollLast());
			// 반시계방향은 맨 첫 요소를 맨 마지막으로 넣으면 된다.
			}else {
				data.offerLast(data.pollFirst());
			}
		}
		// 왼쪽이 다른지 확인하는 함수
		public boolean isLeftDifferent(Wheel target) {
			if(this.data.get(6).equals(target.data.get(2))) return false;
			return true;
		}
		// 오른쪽이 다른지 확인하는 함수
		public boolean isRightDifferent(Wheel target) {
			if(this.data.get(2).equals(target.data.get(6))) return false;
			return true;
		}
	}
	// 회전 명령 함수
	static void rollCommand(int index, boolean clockwise) {
		// 톱니는 동시에 회전하므로 회전 명령 배열을 만든다.
		int[] bookedCommand = new int[4];
		// 우선 0으로 채우고 (0은 회전하지 않음을 말함)
		Arrays.fill(bookedCommand,0);
		// 시계망향이라면 1, 반시계방향이라면 -1을 넣어준다.
		if(clockwise) bookedCommand[index] = 1;
		else bookedCommand[index] = -1;
		
		// 0번이 아니라면 왼쪽으로 비교
		if(index != 0) {
			int current = index;
			// 맨 왼쪽으로 갈때까지 비교
			// 왼쪽이 다른 극이면 반대방향으로 회전한다고 저장
			while(current != 0) {
				if(wheels[current].isLeftDifferent(wheels[current-1])) {
					bookedCommand[current-1] = bookedCommand[current] * (-1);
				}
				// 같은 극이면 회전하지 않으므로 바로 탈출
				else break;				
				current--;
			}
		}
		
		// 3번이 아니라면 오른쪽으로 비교
		if(index != 3) {
			int current = index;
			// 맨 오른쪽으로 갈때까지 비교
			// 오른쪽이 다른 극이면 반대방향으로 회전한다고 저장
			while(current != 3) {
				if(wheels[current].isRightDifferent(wheels[current+1])) {
					bookedCommand[current+1] = bookedCommand[current] * (-1);
				}
				// 같은 극이면 회전하지 않으므로 바로 탈출
				else break;				
				current++;
			}
		}
		// 예약되었던 회전 명령을 실행
		for (int i = 0; i < 4; i++) {
			switch(bookedCommand[i]) {
			case 1:
				wheels[i].roll(true);
				break;
			case -1:
				wheels[i].roll(false);
				break;
			default:
				break;
			}
		}
		
	}
	// 메인 함수
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		// 테스트케이스 입력
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			// 회전수 입력 및 톱니 배열 생성
			K = Integer.parseInt(br.readLine());
			wheels = new Wheel[4];
			for (int i = 0; i < 4; i++) {
				wheels[i] = new Wheel(br.readLine());
			}
			
			
			// 받아서 돌리기
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int index = Integer.parseInt(st.nextToken())-1;
				boolean clockwise = st.nextToken().equals("1") ? true : false;
				
				rollCommand(index,clockwise);
			}
			// 결과 탐색 및 점수 저장
			// 맨 첫요소가 맨 위 요소이므로 peek 함수를 사용한다.
			int result = 0;
			for (int i = 0; i < 4; i++) {
				result += wheels[i].data.peekFirst() ? scores[i] : 0;
			}
			
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
		
		br.close();
	}

}
