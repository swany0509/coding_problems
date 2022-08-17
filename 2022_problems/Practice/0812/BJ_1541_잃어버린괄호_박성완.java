package s0812.bj_1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 백준 1541 잃어버린 괄호
public class Main {
	// 저장될 변수
	static int sum;
	public static void main(String[] args) throws IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(),"-");
		// 처음에는 더해줘야 하므로  true 로 선언
		boolean first = true;
		
		// - 단위로 끊어진 식을 탐색
		while(st.hasMoreTokens()) {
			int temp = 0;
			// 다시 + 를 단위로 쪼갠다
			StringTokenizer subSt = new StringTokenizer(st.nextToken(),"+");
			while(subSt.hasMoreTokens()) {
				// +로 쪼개진 숫자들을 더한다
				temp += Integer.parseInt(subSt.nextToken().trim());
			}
			// 처음이라면  합계에 더하고 아니라면 뺀다.
			if(first) {
				sum += temp;
				first = false;
			}else sum -= temp;
			
		}
		
		System.out.println(sum);
	}

}
