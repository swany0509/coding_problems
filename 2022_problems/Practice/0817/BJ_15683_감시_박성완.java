//package s0817.bj_15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 백준 15683 감시
public class Main {
	// 계산의 편의성을 위해 '#' -> 7로 변환
	// 전역변수 선언
	static int N,M,spot,cctvCnt, minSpot,score;
	static int[][] map;
	// 탐색 배열
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int[] command;
	// cctv 리스트
	static List<CCTV> cctvs;
	static List<CCTV> cctv5;
	// CCTV 객체 선언
	static class CCTV{
		int type,i,j;

		public CCTV(int type, int i, int j) {
			super();
			this.type = type;
			this.i = i;
			this.j = j;
		}
		
	}
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N,M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 변수 생성
		map = new int[N][M];
		minSpot = Integer.MAX_VALUE;
		cctvs = new ArrayList<>();
		cctv5 = new ArrayList<>();
		// 데이터를 입력받고, CCTV의 경우는 1~4/5를 나눠서 받음
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				switch(num) {
				case 0: spot++;
					break;
				case 1:	case 2:	case 3:	case 4:
					cctvs.add(new CCTV(num,i,j));
					cctvCnt++;
					break;
				case 5:
					cctv5.add(new CCTV(num,i,j));
					break;
				default:
					break;
				}
			}
		}
		// 5를 제외한 CCTV 명령 배열을 생성
		command = new int[cctvCnt];
		// 5는 답이 정해져있음. 먼저 채운다.
		fillinit();
		// 순열로  명령 배열 생성
		permutations(0);
		// 최솟값 출력
		System.out.println(minSpot);
		
	}
	// 범위를 벗어낫는지 검사 함수
	static boolean isRange(int i, int j) {
		if( i >= 0 && i < N && j >= 0 && j < M) return true;
		return false;
	}
	// 2차원 배열 객체 복사 함수
	static int[][] arrayCopy(int[][] source){
		int[][] arr = new int[N][];
		for (int i = 0; i < N; i++) {
			arr[i] = source[i].clone();
		}
		return arr;
	}
	// 명령 배열이 완성되엇으면 값을 찾는다.
	static int simulations() {
		int[][] simul = arrayCopy(map);
		score = spot;
		// 명령 숫자에 따라 각 CCTV에 맞게 배열을 채워본다.
		for (int i = 0 ,size = command.length; i < size; i++) {
			int com = command[i];
			CCTV c = cctvs.get(i);
			switch(c.type) {
			case 1:
				fills(simul,c.i,c.j,com);
				break;
			case 2:
				fills(simul,c.i,c.j,com);
				fills(simul,c.i,c.j,com+2);
				break;
			case 3:
				fills(simul,c.i,c.j,com);
				fills(simul,c.i,c.j,com+1);
				break;
			case 4:
				fills(simul,c.i,c.j,com);
				fills(simul,c.i,c.j,com+1);
				fills(simul,c.i,c.j,com+2);
				break;
			}
		}
//		테스트 출력
//		for(int[] arr : simul) {
//			for(int a : arr) System.out.print(a + " ");
//			System.out.println();
//		}
//		System.out.println(score);
//		System.out.println();
		return score;
	}
	// 실체 채우는 함수
	static void fills(int[][] arr, int ii, int jj, int k) {
		// 4로 나머지계산을 해서 방향을 정한다.
		k %= 4;
		while(true) {
			if(!isRange(ii+dx[k],jj+dy[k])) return;
			int target =  arr[ii+dx[k]][jj+dy[k]];
			if(target == 6) return;
			else if (target == 0) {
				arr[ii+dx[k]][jj+dy[k]] = 7;
				score--;
				// 중간에 0이되면 출력하고 프로그램을 종료시킨다.
				if(score == 0) {
					System.out.println(0);
					System.exit(0);
				}
			}
			ii += dx[k]; jj += dy[k];
		}
	}
	// 명령 배열 생성
	static void permutations(int cnt) {
		if(cnt == cctvCnt) {
			int result = simulations();
			minSpot = minSpot > result ? result : minSpot;
			return;
		}
		for (int i = 0; i < 4; i++) {
			command[cnt] = i;
			permutations(cnt+1);
		}
	}
	// CCTV 5번 은 먼저 채운다.
	static void fillinit() {
		for (int t = 0; t < cctv5.size(); t++) {
			CCTV c = cctv5.get(t);
			for (int k = 0; k < 4; k++) {
				int ii = c.i ;int jj = c.j;
				while(true) {
					if(!isRange(ii+dx[k],jj+dy[k])) break;
					int target =  map[ii+dx[k]][jj+dy[k]];
					if(target == 6) break;
					else if (target == 0) {
						map[ii+dx[k]][jj+dy[k]] = 7;
						spot--;
					}
					// 중간에 0이되면 출력하고 프로그램을 종료시킨다.
					if(spot == 0) {
						System.out.println(0);
						System.exit(0);
					}
					ii += dx[k]; jj += dy[k];
				}
			}
		}
	}
}
