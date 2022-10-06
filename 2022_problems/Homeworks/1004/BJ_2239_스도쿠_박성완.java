package s1004.bj_2239;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
// 백준 2239 스도쿠 메모리 18660	시간932
public class Main {
	// 전역변수 선언
	static char[] sudoku;
	static boolean[] visited;
	static char[] data = {'0','1','2','3','4','5','6','7','8','9'};
	static List<Integer>[] areas;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sudoku = new char[81]; // 스도쿠 데이터
		visited = new boolean[81]; // 이미 입력된 수는 변경하지 않기 위한 방문 배열
		areas = new List[9]; // 구역을 나누기 위한 리스트 배열
		
		// 데이터 입력
		int cnt = 0;
		for (int i = 0; i < 9; i++) {
			areas[i] = new ArrayList<>();
			String buffer = br.readLine().trim();
			for (int j = 0; j < 9; j++) {
				char c = buffer.charAt(j);
				sudoku[cnt] = c;
				if(c != '0') visited[cnt] = true;
				cnt++;
			}
		}
				
		// 구역을 나누는 알고리즘
		for (int i = 0; i < 81; i++) {
			areas[i/9/3*3 + i%9/3].add(i);
		}
		
		perm(0);
				
	}
	// 순열 알고리즘
	static void perm(int cnt) {
		// 결과가 나왔다면 출력하고 종료해버린다.
		if(cnt==81) {
			print(sudoku);
			System.exit(0);
		}
		// 불변값이면 다음 순열로 넘기고 돌아감
		if(visited[cnt]) {
			perm(cnt+1);
			return;
		}
		// 1~9까지 넣어보면서 유효한 값일때만 다음 순열로 넘김
		for (int i = 1; i <= 9; i++) {
			if(valid(cnt, cnt/9/3*3 + cnt%9/3 , data[i])){
				sudoku[cnt] = data[i];
				perm(cnt+1);
				// 다음 탐색을 위해 초기화해야 한다. 아니면 답이 안나옴
				sudoku[cnt] = '0';
			}
		}
		
	}
	// 숫자가 유효한지 검증하는 함수
	static boolean valid(int idx, int area, char num) {
		// 구역 검증
		for(int i : areas[area]) {
			if(sudoku[i] == num) return false;
		}
		// 가로 검증
		for (int i = idx/9*9; i < idx/9*9 + 9; i++) {
			if(sudoku[i] == num) return false;
		}
		
		// 세로 검증
		for (int i = idx%9; i < 81; i+=9) {
			if(sudoku[i] == num) return false;
		}
		
		return true;
	}
	// 배열 출력
	static void print(char[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0, size = arr.length; i < size; i++) {
			sb.append(arr[i]);
			if(i%9==8) sb.append("\n");
		}
		System.out.println(sb);
	}
}
