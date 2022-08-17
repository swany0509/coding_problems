//package s0817.sw_5644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
// SWEA 5644 무선충전
public class Solution {
	// 전역변수 선언
	static int TC,M,A;
	static int[][] move;
	static User[] users = new User[2];
	static AP[] aps;
	static StringBuilder sb;
	static int[] dx = {0,-1,0,1,0};
	static int[] dy = {0,0,1,0,-1};
	// 유저 클래스
	static class User{
		int x,y,charges;
		List<Integer> visit;
		public User(int y, int x) {
			super();
			this.x = x;
			this.y = y;
			visit = new ArrayList<>();
		}
		
	}
	// ap 클래스
	static class AP implements Comparable<AP>{
		int x,y,c,p;
		// 생성시 주의 이 문제에선 ij가 반대다. x->j, y->i
		public AP(int y, int x, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
		//ap끼리는 p(performance)내림차순으로 정렬한다. 같은 경우는 의미는 없지만 범위가 내림차순.
		@Override
		public int compareTo(AP o) {
			return this.p != o.p ? o.p - this.p : o.c - this.c;
		}
//		@Override
//		public String toString() {
//			return "AP [x=" + x + ", y=" + y + ", c=" + c + ", p=" + p + "]";
//		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		// 테스트케이스 입력
		TC = Integer.parseInt(br.readLine());
				
		for (int t = 1; t <= TC; t++) {
			// 테스트케이스마다 데이터 입력받고 초기화
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			// 이동명령
			move = new int[2][M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) move[0][i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) move[1][i] = Integer.parseInt(st.nextToken());
			// AP데이터
			aps = new AP[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				aps[i] = new AP(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			// ap초기 정렬
			Arrays.sort(aps);
			
			// ap 디버깅
//			for (int i = 0; i < A; i++) {
//				System.out.println(aps[i].toString());
//			}
//			System.out.println();
			// 유저 생성
			// 다 풀고 나서 생각했는데 사실 charges를 굳이 둘 나눠서 더할 게 아니라 하나로더해도 됐었다...젠장
			users[0] = new User(1,1);
			users[1] = new User(10,10);
			
			// 0시간 세팅
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < A; j++) {
					if(isNear(users[i],aps[j])) {
						users[i].charges += aps[j].p;
						break;
					}
				}
			}
			//  초기 상태 디버깅
//			System.out.println("----0");
//			System.out.print(users[0].charges + " | " + users[0].x + " " + users[0].y + " | ");
//			for (int n : users[0].visit) System.out.print(n + " ");
//			System.out.println();
//			System.out.print(users[1].charges + " | " + users[1].x + " " + users[1].y + " | ");
//			for (int n : users[1].visit) System.out.print(n + " ");
//			System.out.println();
			
			// 이동명령에 따라 전개
			for (int m = 0; m < M; m++) {
				users[0].visit.clear(); users[1].visit.clear();
				// 이동하고 범위 내 AP 탐지
				for (int i = 0; i < 2; i++) moveState(users[i],move[i][m]);
				
				// 이동 후 디버깅
//				System.out.println("----" + (m+1));
//				System.out.print(move[0][m] + " | " + users[0].x + " " + users[0].y + " | ");
//				for (int n : users[0].visit) System.out.print(n + " ");
//				System.out.println();
//				System.out.print(move[1][m] + " | " + users[1].x + " " + users[1].y + " | ");
//				for (int n : users[1].visit) System.out.print(n + " ");
//				System.out.println();
				// 두명의 현재 접근 가능한 AP를 기반으로 최적값 계산
				findBetter(users[0].visit,users[1].visit);
				
				// 채우고 디버깅
//				System.out.println("----" + (m+1));
//				System.out.print(users[0].charges + " | " + users[0].x + " " + users[0].y + " | ");
//				for (int n : users[0].visit) System.out.print(n + " ");
//				System.out.println();
//				System.out.print(users[1].charges + " | " + users[1].x + " " + users[1].y + " | ");
//				for (int n : users[1].visit) System.out.print(n + " ");
//				System.out.println();
			}
			// 결과 합침
			sb.append("#"+t+" "+(users[0].charges + users[1].charges+"\n"));
		}
		System.out.println(sb);
		br.close();
	}
	// 최적 충전값을 가지게 탐지하는 메소드
	static void findBetter(List<Integer>li1, List<Integer>li2) {
		int alen = li1.size() ; int blen = li2.size();
		// 둘 다 없으면 리턴
		if(alen + blen == 0) return;
		// b만 있으면 b만 추가
		if(alen == 0) {
			users[1].charges += aps[li2.get(0)].p;
			return;
		}
		// a만 있으면 a만 추가
		if(blen == 0) {
			users[0].charges += aps[li1.get(0)].p;
			return;
		}
		// 만약 둘이 같은 한 곳만 들렸다면 2로 나눠서 추가
		if(alen == 1 && blen == 1 && li1.get(0) == li2.get(0)) {
			users[0].charges += aps[li1.get(0)].p/2;
			users[1].charges += aps[li2.get(0)].p/2;
			return;
		}
		// 둘이 같다면 아무나 0번, 아무나 1번을 넣으면 된다.
		if(li1.equals(li2)) {
			users[0].charges += aps[li1.get(0)].p;
			users[1].charges += aps[li2.get(1)].p;
			return;
		}
		// a가 1이면 먼저 a에 하나 추가하고 b는 나머지중 하나 추가
		if(alen == 1) {
			int ause = li1.get(0);
			users[0].charges += aps[ause].p;
			for (int i = 0; i < A; i++) {
				if(li2.contains(i) && (i != ause)) {
					users[1].charges += aps[i].p;
					break;
				}
			}
			return;
		}
		// b가 1이면 먼저 b에 하나 추가하고  a는 나머지중 하나 추가
		if(blen == 1) {
			int buse = li2.get(0);
			users[1].charges += aps[buse].p;
			for (int i = 0; i < A; i++) {
				if(li1.contains(i) && (i != buse)) {
					users[0].charges += aps[i].p;
					break;
				}
			}
			return;
		}
		// 여러개로 겹치는 경우
		
		if(li1.get(0) != li2.get(0)) { // 첫번째가 다르면 각자 더하기
			users[0].charges += aps[li1.get(0)].p;
			users[1].charges += aps[li2.get(0)].p;
			return;
		}else { //첫번째가 같으면 두번째에서 처리
			//요소가 다 같은 경우는 위에서 처리
			if(li1.get(1) < li2.get(1)) { //a가 더 크면 a에 1번, b에 0번을 넣는다
				users[0].charges += aps[li1.get(1)].p;
				users[1].charges += aps[li2.get(0)].p;
				return;
			}else if(li1.get(1) > li2.get(1)) {//b가 더 크면 b에 1번, a에 0번을 넣는다.
				users[0].charges += aps[li1.get(0)].p;
				users[1].charges += aps[li2.get(1)].p;
				return;
			}else { //2번도 같다면 아무나 0번, 아무나 1번을 넣으면 된다.
				users[0].charges += aps[li1.get(0)].p;
				users[1].charges += aps[li2.get(1)].p;
			}
		}	
		return;
	}
	// 초기에 0시간 세팅 함수
	static void moveState(User u, int idx) {
		u.x += dx[idx]; u.y += dy[idx];
		for (int i = 0; i < A; i++) {
			if(isNear(u,aps[i])) u.visit.add(i);
		}
	}
	// 범위 내 AP인지 판별 함수
	static boolean isNear(User u, AP a) {
		if( (Math.abs(u.x-a.x) + Math.abs(u.y-a.y)) <= a.c) return true;
		return false;
	}
}
