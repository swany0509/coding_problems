package s0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	// 전역변수 선언
	static int N,TC,COM_N;
	static List<String> nlist;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 갯수 선언
		TC = 10;
		
		for (int i = 1; i <= TC; i++) {
			// 데이터 입력
			nlist = new ArrayList<String>();
			//원문 암호문의 길이
			N = Integer.parseInt(br.readLine());
			// 초기 리스트 입력
			nlist = new ArrayList<String>(Arrays.asList(br.readLine().split(" ")));
			// 명령어 갯수 입력
			COM_N = Integer.parseInt(br.readLine());
			// 데이터 입력
			st = new StringTokenizer(br.readLine());
			// 명령어 갯수만큼 실행
			for (int j = 0; j < COM_N; j++) {
				// I 는 입력만 받고 넘어간다.
				String comm = st.nextToken();
				// x,y 입력
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				// x 위치에 하나씩 삽입한다.
				for (int k = 0; k < y; k++) {
					nlist.add(x+k,st.nextToken());
				}
			}
			
			// 한 테스트케이스가 끝나면 결과를 추가한다.
			sb.append("#" + i + " ");
			for (int j = 0; j < 10; j++) sb.append(nlist.get(j)+ " ");
			sb.append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
}
