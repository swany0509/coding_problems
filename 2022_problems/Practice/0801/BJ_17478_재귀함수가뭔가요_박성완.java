package s0801;
//벡준 17478 재귀함수가 뭔가요?
import java.util.Scanner;

public class Main {
	
	/**
	 * 재귀함수 정의
	 * @param n 반복할 횟수
	 * @param count "____"를 출력할 횟수
	 */
	public static void recur(int n,int count) {
		// n이 0이면 마지막 대화를 출력하고 반환한다.
		if (n == 0) {
			for (int i = 0; i < count; i++) System.out.print("____");
			System.out.println("\"재귀함수가 뭔가요?\"");
			for (int i = 0; i < count; i++) System.out.print("____");
			System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			for (int i = 0; i < count; i++) System.out.print("____");
			System.out.println("라고 답변하였지.");
		}
		// 그 경우가 아니라면 초기 대회를 출력하고 recur(n-1,count+1) 하여 재귀호출한다.
		else {
			for (int i = 0; i < count; i++) System.out.print("____");
			System.out.println("\"재귀함수가 뭔가요?\"");
			for (int i = 0; i < count; i++) System.out.print("____");
			System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
			for (int i = 0; i < count; i++) System.out.print("____");
			System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
			for (int i = 0; i < count; i++) System.out.print("____");
			System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
			recur(n-1,count+1);
			for (int i = 0; i < count; i++) System.out.print("____");
			// 재귀호출이 끝나면 끝 대화를 출력한다.
			System.out.println("라고 답변하였지.");
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 반복되지 않는 1번 대화 출력
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		recur(n,0);
	}
}
