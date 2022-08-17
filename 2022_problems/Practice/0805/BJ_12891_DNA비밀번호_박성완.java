//package s0805.bj_12891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 백준 12981 DNA 비밀번호
public class Main {
	// 전역변수 선언
	static int S,P,ans;
	static String data;
	static int[] wanted = new int[4];
	public static void main(String[] args) throws IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 데이터 입력
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); P = Integer.parseInt(st.nextToken());
		data = br.readLine();
		st = new StringTokenizer(br.readLine());
		
		// 필요갯수 배열 입력
		for (int i = 0; i < 4; i++) wanted[i] = Integer.parseInt(st.nextToken());
		
		// P길이만큼의 단어에서 문자별 갯수를 필요갯수에서 뺀다. 그 후 유효성 검사를 한다.
		// 유효성 검사를 통과하면 갯수를 증가한다.
		// 다음 한칸 전진하면 전진한 문자를 필요갯수에서 빼고, 범위를 벗어난 숫자를 필요갯수에 다시 더한다.
		// 이를 반복한다.
		for (int i = 0; i < S; i++) {
			progress(data.charAt(i));
			if(i >= P) back(data.charAt(i-P));
			if(i >= P-1 && isValid()) ans++;
		}

		System.out.println(ans);
	}
	// 탐색 바깥쪽 경우를 스위치문으로 계산해 필요한 수에서 뺀다.
	static void progress(char c) {
		switch(c) {
		case 'A':
			wanted[0]--;
			break;
		case 'C':
			wanted[1]--;
			break;
		case 'G':
			wanted[2]--;
			break;
		case 'T':
			wanted[3]--;
			break;
		default:
			break;
		}
	}
	// 탐색 범위를 벗어난 문자를 스위치문으로 계산해 필요한 수에 더한다.
	static void back(char c) {
		switch(c) {
		case 'A':
			wanted[0]++;
			break;
		case 'C':
			wanted[1]++;
			break;
		case 'G':
			wanted[2]++;
			break;
		case 'T':
			wanted[3]++;
			break;
		default:
			break;
		}
	}
	
	// 필요한 수 배열을 문자별로 보아 0 초과면 필요 문자가 아직 남은 것이므로 유효하지 않다.
	static boolean isValid() {
		for (int i = 0; i < 4; i++) if(wanted[i] > 0) return false;
		return true;
	}
}