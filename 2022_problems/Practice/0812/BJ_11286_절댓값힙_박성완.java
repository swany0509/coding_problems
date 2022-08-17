//package s0812.bj_11286;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

// 백준 11286 절댓값 힘
public class Main {
	// 전역변수 선언
	static int N;
	static PriorityQueue<Integer> queue; // 우선순위 큐
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 갯수 입력
		N = Integer.parseInt(br.readLine());
		// 우선순위 큐 생성
		// 사용자 지정 정렬 적용 : 절댓값 오름차순 우선 : 같은 경우 작은 수 우선
		queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1) == Math.abs(o2)) return o1-o2;
				else return Math.abs(o1) - Math.abs(o2);
			}
		});
		// N개만큼 명령 실행
		for (int i = 0; i < N; i++) {
			int command = Integer.parseInt(br.readLine());
			switch(command) {
			case 0:
				if(queue.isEmpty()) sb.append("0\n");
				else sb.append(queue.poll() + "\n");
				break;
			default:
				queue.add(command);
				break;
			}
		}
		
		System.out.println(sb);
		br.close();
	}
}
