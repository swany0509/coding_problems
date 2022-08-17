// package s0801;
// 백준 1244 스위치 켜고 끄기
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 스위치의 번호는 1부터 시작한다. 앞으로의 계산이 편하게 N+1로 저장한다.
		int N = sc.nextInt()+1;
		
		// 스위치 선언. 계산이 편하게 boolean형으로 선언하였다.
		boolean[] swit = new boolean[N];
		
		// 첫스위치는 사용하지 않는다.
		swit[0] = false;
		
		// 숫자를 입력받고 스위치를 초기화한다.
		for (int i = 1; i < N; i++) {
			if(sc.nextInt()==1) swit[i] = true;
			else swit[i] = false;
		}
		
		// 인원 수 입력
		int M = sc.nextInt();
		
		for (int i = 0; i < M; i++) {
			// 성별과 스위치 키 입력
			int gender = sc.nextInt();
			int key = sc.nextInt();
			
			// 스위치를 사용해 남여 동작 코드 상이
			switch(gender) {
			// 남자의 경우 해당 스위치부터 배수 스위치들을 모두 변경한다.
			case 1:
				for (int j = key; j < N; j+=key) swit[j] = !swit[j];
				break;
			// 여자의 경우 해당 스위치를 기준으로 좌우로 투포인터를 두어 서로 비교한다.
			// 같으면 다음 경우로 전진하고, 아니면 while문을 탈출한다.
			case 2:
				int from = key; int to = key;
				
				while(true) {
					if (from-1 < 1 || to+1 > N-1 || swit[from-1]!=swit[to+1]) break;
					from--; to++;
				}
				for (int j = from; j <= to; j++) swit[j] = !swit[j];
				
				break;
			default:
					break;
			}
			
		}
		
		// 스위치를 출력한다. 20개 단위로 줄바꿈한다.
		for (int i = 1; i < N; i++) {
			if(swit[i]) System.out.print(1 + " ");
			else System.out.print(0 + " ");
			
			if (i%20==0) System.out.println();
		}

	}

}
