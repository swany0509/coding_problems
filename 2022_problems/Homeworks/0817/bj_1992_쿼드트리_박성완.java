package s0817.bj_1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1992 쿼드트리
public class Main {
	// 전역변수 선언
	static int N;
	static String[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N 입력받고 map 생성 및 입력
		N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		// 입력을 split으로 쪼개서 string 배열로 만들어 저장한다.
		for (int i = 0; i < N; i++) map[i] = br.readLine().split("");
		// 분할정복 및 재귀 방법으로 쿼트트리 실행
		System.out.println(quad(0,0,N-1,N-1));
	}
	// 같은지 판별하는 함수
	static boolean isSame(int x1, int y1, int x2, int y2) {
		// 첫요소 저장
		String first = map[x1][y1];
		// 하나라도 다르면 false 반환
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				if(!first.equals(map[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
	static String quad(int x1, int y1, int x2, int y2) {
		// 요소가 하나밖에 없다면 그 요소 반환
		if(x1==x2) return map[x1][y1];
		// 안의 요소가 다 같다면 첫 요소만 반환
		if(isSame(x1,y1,x2,y2)) return map[x1][y1];
		// 그게 아니면 4개로 쪼개서 재귀식 반환
		else return "(" + 
			quad(x1,			y1,			(x1+x2)/2,	(y1+y2)/2) +
			quad(x1,			(y1+y2)/2+1,(x1+x2)/2,	y2) +
			quad((x1+x2)/2+1,	y1,			x2,			(y1+y2)/2) +
			quad((x1+x2)/2+1,	(y1+y2)/2+1,x2,			y2) +
			")";
	}
}
