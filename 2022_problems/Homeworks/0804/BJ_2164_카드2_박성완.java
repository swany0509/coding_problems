package s0804.bj_2164;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
// BJ 2164 카드 2
public class Main {
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// 출력값 선언
		int last;
		// deque를 선언한다.
		Deque<Integer> queue = new ArrayDeque<>();
		// 초기 덱을 구성한다.
		for (int i = 1; i <= N; i++) queue.add(i);
		// 첫 시작은 무조건 1개 이상이다.
		// 먼저 첫 요소를 빼고, 그 이후에 큐가 비어있다면 break한다.
		// 다음으로 첫요소를 빼서 마지막으로 넣는다.
		// 이를 반복한다.
		while(true){
			last = queue.pollFirst();
			if(queue.isEmpty()) break;
			queue.add(queue.pollFirst());
		}
		System.out.println(last);
	}
}