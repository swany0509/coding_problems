package s1005.bj_17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 메모리 20344	시간 304
// 백준 낚시왕
public class Main {
	// 전역변수 선언
	static int R,C,M, sCnt, score, map[][], fisher;
	static Shark[] sharks;
	// 상어 클래스 선언
	static class Shark{
		// 잡히거나 먹히면  false
		boolean exist;
		int num,r,c,s,d,z;//행,열,속력,이동방향,크기

		public Shark(int num, int r, int c, int s, int d, int z) {
			super();
			this.exist = true;
			this.num = num;
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}		
	}
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 상어 갯수 변수에 복사
		sCnt = M;
		// 입력시 상어가 0마리면 바로 종료
		if(M==0) {
			System.out.println(0);
			System.exit(0);
		}
		// 맵 생성하고 맵을 -1 로 초기화
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			Arrays.fill(map[i], -1);
		}
		// 상어 데이터 입력.
		sharks = new Shark[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 행렬 -1해서  index 맞춤
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks[i] = new Shark(i,r,c,s,d,z);
			map[r][c] = i;
		}
		// 낚시왕의 위치 초기화
		fisher = -1;
		// 낚시왕이 한칸씩 옆으로 이동하면서 진행
		while(++fisher < C && sCnt > 0) {
			catchFish(fisher); // 물고기 잡고
			sharkMove(); // 상어 움직임
		}
		
		System.out.println(score);
		
	}
	// 낚시
	static void catchFish(int c) {
		int idx = -1;
		// 해당 열 탐색, 상어를 찾으면  break
		for (int i = 0; i < R; i++) {
			if(map[i][c] != -1) {
				idx = map[i][c];
				map[i][c] = -1;
				break;
			}
		}
		// 못찾으면 리턴
		if(idx == -1) return;
		// 찾으면 상어를 false 시키고 점수 더함
		score += sharks[idx].z;
		sharks[idx].exist = false;
		
	}
	// 상어 움직임 메소드
	static void sharkMove() {
		// 모든 상어에 대해
		for(Shark s : sharks) {
			// 존재하지 않는 친구면 넘어감
			if(!s.exist) continue;
			
			int div, rem, range, refac;
			
			// 원래 위치 초기화
			map[s.r][s.c] = -1;
			
			if(s.s != 0) {// 속력이 0이 아니라면 방향에 따라 분기
				switch(s.d){
				// 위아래는  R과 관계
				case 1://up
					if (R==1) break;
					range = (R-1)*2;
					refac = range + s.s + (R-1) - s.r;
					
					div = refac/(R-1);
					rem = refac%(R-1);
					if(div % 2 == 0) { // 몫이 짝수라면 방향 그대로
						s.r = R-1-rem;
					}else {// 몫이 홀수라면 방향 반대로
						s.r = rem;
						if(rem != 0) s.d = 2;
					}
					break;
				case 2://down
					if (R==1) break;
					range = (R-1)*2;
					refac = range + s.s + s.r;
					
					div = refac/(R-1);
					rem = refac%(R-1);
					if(div % 2 == 0) { // 몫이 짝수라면 방향 그대로
						s.r = rem;
					}else {// 몫이 홀수라면 방향 반대로
						s.r = R-1-rem;
						if(rem != 0) s.d = 1;
					}
					break;
				// 좌우는 C와 관계
				case 3://right
					if (C==1) break;
					range = (C-1)*2;
					refac = range + s.s + s.c;
					
					div = refac/(C-1);
					rem = refac%(C-1);
					if(div % 2 == 0) { // 몫이 짝수라면 방향 그대로
						s.c = rem;
					}else {// 몫이 홀수라면 방향 반대로
						s.c = C-1-rem;
						if(rem != 0) s.d = 4;
					}
					break;
				case 4://left
					if (C==1) break;
					range = (C-1)*2;
					refac = range + s.s + (C-1) - s.c;
					
					div = refac/(C-1);
					rem = refac%(C-1);
					if(div % 2 == 0) { // 몫이 짝수라면 방향 그대로
						s.c = C-1-rem;
					}else {// 몫이 홀수라면 방향 반대로
						s.c = rem;
						if(rem != 0) s.d = 3;
					}
					break;
				default:
					System.out.println("error");
					break;
				}
			}
		}
		// 모든 상어의 위치를 정하고 나서 위치 최신화를 한다.
		for(Shark s : sharks) {
			// 존재하지 않는 친구면 넘어감
			if(!s.exist) continue;
			// 갈 위치에 다른 친구가 있으면 비교해서 큰 놈이 작은 놈을 잡아먹음
			int sh = map[s.r][s.c];
			if(sh != -1) {
				if(sharks[sh].z > s.z) {
					map[s.r][s.c] = sh;
					s.exist = false;
				}else {
					map[s.r][s.c] = s.num;
					sharks[sh].exist = false;
				}
				sCnt--;
			}else {
				map[s.r][s.c] = s.num;
			}
		}
	}
	// 디버깅용 메소드
	static void print(int[][] array) {
		for(int[] arr: array ) {
			for(int a : arr) System.out.print(a + " ");
			System.out.println();
		}
		System.out.println();
	}
}
