//package s0804.d4_1218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// 괄호문자들 '()', '[]', '{}', '<>' 
// SWEA D4 1218 괄호 짝짓기
public class Solution {
	// 전역변수 선언
	public static int TC = 10;
	public static List<Character> stack = new ArrayList<>();
	// 페어를 금방 찾기 위해 HashMap 생성
	public static HashMap<Character,Character> pair = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 페어 입력
		pair.put(')', '('); pair.put(']', '['); pair.put('}', '{'); pair.put('>', '<');
		
		for (int t = 1; t <= TC; t++) {
			int N = Integer.parseInt(br.readLine());
			int cnt = 0;
			// 데이터를 입력받고 char형 배열로 반환
			char[] data = br.readLine().toCharArray();

			boolean wrong = false;
			
			// switch문 이용
			for(char c : data) {
				switch(c) {
				// 열린 괄호라면 그냥 넣는다.
				case '(' : case '[': case '{': case '<':
					stack.add(c);
					cnt++;
					break;
				// 닫힌 괄호라면 가장 상위 요소가 페어와 맞는지 본다.
				case ')' : case ']': case '}': case '>':
					if ((cnt > 0)  && (stack.get(cnt-1) == pair.get(c))) {
						stack.remove(--cnt);
					} 
					// 아니라면 중간변수를 통해 탈출한다.
					else wrong = true;
					break;
				default:
					break;
				}
				
				if(wrong) break;
			}
			// 반복문을 다 돌았을 때 요소가 남아있다면 0이다.
			if (wrong || cnt > 0) sb.append("#" + t +" 0" + "\n");
			else sb.append("#" + t +" 1" + "\n");
			
			stack.clear();
		}
		
		System.out.println(sb);
		br.close();
	}

}
