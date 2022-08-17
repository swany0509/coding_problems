package s0801;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// SWEA 1289 원재의 메모리 복구하기
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테스트 케이스 수 입력
		int TC = sc.nextInt();
		
		// 1회 변환 시 맨 뒤부터 같은 비트를 한번에 초기화한다.
		// (1) 0011 -> 0000 (1회)
		// (2) 001100 -> 001111 -> 000000 (2회)
		// (3) 100 -> 111 -> 000 (2회)
		// 따라서 연속된 비트가 몇 세트인지 갯수를 세면 된다. (1)의 경우 2, (2)의 경우 3, (3)의 경우 2
		// 이 때 0으로 시작하는 비트는 1회 덜 작업해도 되므로, 최종값에서 1을 제한다.
		for (int tc = 1; tc <= TC; tc++) {
			// 데이터 입력
			String data = sc.next();
			// 연속된 비트의 세트를 계산할 character 형 ArrayList
			List<Character> stack = new ArrayList<>();
			// 1번 비트는 무조건 넣는다.
			stack.add(data.charAt(0));
			
			// 2번 부터 진행하여 마지막 요소와 다른 비트면 리스트에 추가한다.
			for (int i = 1; i < data.length(); i++) {
				if (data.charAt(i) != stack.get(stack.size()-1)) {
					stack.add(data.charAt(i));
				}
			}
			
			// 리스트의 사이즈를 답으로 정하고, 첫 요소가 0이라면 -1한다.
			int ans = stack.size();
			if (stack.get(0)=='0') ans--;
			
			// 양식에 맞게 출력한다.
			System.out.println("#" + tc + " " + ans);
		}
	}

}
