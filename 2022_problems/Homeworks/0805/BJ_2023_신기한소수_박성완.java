package s0805.bj_2023;
// 백준 2023 신기한소수
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// 전역변수 선언
	static int N;
	static int size,initial;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 설정
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 갯수 입력
		N = Integer.parseInt(br.readLine());
		// 최대수
		size = (int)Math.pow(10, N);
		// 최소 수
		initial = size/10;
		
		// 1자리 수는 재귀문으로 구현하기에 번거로워 1자리수는 수기로 넣고 2자리수부터 재귀로 계산한다.
		findNum(2);
		findNum(3);
		findNum(5);
		findNum(7);
		
		System.out.println(sb);
		br.close();
	
	}
	// 소수 판별 함수
	static boolean isPrime(int n) {
		// 2부터 sqrt(n) 까지 나눠지면 소수가 아니다.
		for (int i = 2; i <= (int)Math.sqrt(n); i++) if(n%i==0) return false;
		return true;
	}
	// 실제 계산 함수
	static void findNum(int n) {
		// 숫자 범위 안이고 소수라면, 추가한다.
		if(n >= initial && isPrime(n)) sb.append(n+"\n");
		// 아니라면 현재 수가 소수인지 판별하고 10을 곱한 뒤 0~9를 더한 수를 재귀로 호출한다.
		else {
			if(isPrime(n)) 
				for (int i = 0; i < 10; i++) 
					findNum(10*n+i);
			else return;
		}
		
	}
}
