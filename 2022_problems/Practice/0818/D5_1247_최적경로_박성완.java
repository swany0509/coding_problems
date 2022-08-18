//package s0818.d5_1247;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// SWEA 1247 최적경로
public class Solution {
	// 전역변수 생성
	static int TC,N, least;
	static Point firm, home, list[];
	static boolean isVisited[];
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		// 테스트 케이스 입력
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			// 최소값을 int최대 값으로 설정
			least = Integer.MAX_VALUE;
			// N입력받고 각종 변수 초기화 및 입력
			N = Integer.parseInt(br.readLine());
			list = new Point[N];
			isVisited = new boolean[N];
			// 회사와 집 먼저 입력
			st = new StringTokenizer(br.readLine());
			firm = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			home = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			// 집 정보 입력
			for (int i = 0; i < N; i++) 
				list[i] = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			// 백트래킹 이용
			backTracking(firm,0,0);
			// 결과 추가
			sb.append("#" + t + " " + least + "\n");
		}
		// 출력
		System.out.println(sb);
		br.close();
	}
	// 맨하튼거리 계산
	static int manhatten(Point a, Point b) {
		return (int)(Math.abs(a.x-b.x) + Math.abs(a.y-b.y));
	}
	// 백트래킹을 이용한 순열 알고리즘
	static void backTracking(Point cur, int cnt, int sum) {
		// 계산값이 최솟값을 넘으면 더이상 계산하지 않는다.
		if(sum > least) return;
		// N개가 채워지면 집과의 거리를 계산하고 최솟값을 최신화한다.
		if(cnt == N) {
			int res = sum + manhatten(cur,home);
			least = least > res ? res : least;
			return;
		}
		// 순열 방식으로 다음 집을 선택한다.
		for (int i = 0; i < N; i++) {
			if(isVisited[i]) continue;
			isVisited[i] = true;
			backTracking(list[i], cnt+1, sum + manhatten(cur,list[i]));
			isVisited[i] = false;
		}
	}
}
