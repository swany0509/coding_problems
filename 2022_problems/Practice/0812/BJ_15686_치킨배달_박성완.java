//package s0812.bj_15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 15686 치킨배달
public class Main2 {
	// 전역변수 선언
	static int N,M, houseCnt,chickCnt, minChickenlen;
	static int[][] map, distance;
	static List<int[]> houses,chicks;
	static boolean[] isVisited;
	// 조합 탐색 및 계산
	static void combination(int cnt) {
		// M개를 뽑았다면 바로 계산해준다.
		if(cnt==M) {
			int score = 0;
			for (int i = 0; i < houseCnt; i++) {
				int tmp = Integer.MAX_VALUE;
				for (int j = 0; j < chickCnt; j++) {
					if(isVisited[j]) {
						int dist = distance[i+1][j+1];
						tmp = tmp > dist ? dist : tmp;
					}
				}
				score += tmp;
				// 계산 중간에 최소값을 넘으면 되돌아간다.
				if (minChickenlen < score)  return;
			}
			minChickenlen = minChickenlen > score ? score : minChickenlen;
			return;
		}
		// 조합 생성
		for(int i = cnt; i< chickCnt; i++) {
			if(isVisited[i]) continue;
			isVisited[i] = true;
			combination(cnt+1);
			isVisited[i] = false;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N,M 입력
		N = Integer.parseInt(st.nextToken());	M = Integer.parseInt(st.nextToken());
		// 맵 데이터
		map = new int[N+1][N+1];
		minChickenlen = Integer.MAX_VALUE; // 계산해 나올 값
		// 집과 치킨집 리스트
		houses = new ArrayList<>();
		chicks = new ArrayList<>();
		
		// 데이터를 입력하면서 집과 치킨은 리스트에 넣고 갯수를 센다.
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				switch(n) {
				case 1:
					houses.add(new int[] {i,j});
					houseCnt++;
					break;
				case 2:
					chicks.add(new int[] {i,j});
					chickCnt++;
					break;
				default:
					break;
				}
			}
		}
		// 상대거리를 미리 계산한다.
		distance = new int[houseCnt+1][chickCnt+1];
		for (int i = 1; i <= houseCnt; i++) 
			for (int j = 1; j <= chickCnt; j++) 
				distance[i][j] = Math.abs(houses.get(i-1)[0]-chicks.get(j-1)[0]) + 
						Math.abs(houses.get(i-1)[1]-chicks.get(j-1)[1]);
		// 방문 리스트
		isVisited = new boolean[chickCnt];
		// 조합 실행. 무조건  M개일 때 값이 높으므로  M개일때만 찾아본다.
		combination(0);
		System.out.println(minChickenlen);
	}
}
