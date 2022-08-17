//package S0809.d4_1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SWEA D4 정사각형 방
public class Solution {
	// 전역 변수 선언
	static int TC,N,curBest;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 테스트케이스 입력
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC ; t++) {
			// N입력, map 선언
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			// 매 테케마다 최소숫자, 최대길이 선언
			int leastNum = N*N+1;
			int longest = 0;
			// 맵 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 모든 노드를 탐색하여 경우의 수를 계산한다. 계산 방식은  dfs다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 현재의 최대 길이
					curBest = 0;
					// dfs 계산
					dfs(i,j,map[i][j],0);
					// longest 최신화
					if(longest < curBest) {
						longest = curBest;
						leastNum = map[i][j];
					// 길이가 같다면 leaseNum 비교
					} else if (longest == curBest) {
						leastNum = leastNum > map[i][j] ? map[i][j] : leastNum;
					}
				}
			}
			// 값 추가
			sb.append("#" + t + " " + leastNum + " " + longest + "\n");
		}
		System.out.println(sb);
		br.close();
	}
	static void dfs(int i,int j,int prev,int cnt) {
		// 첫 계산이라면 cnt만 증가
		if(cnt==0) cnt++;
		// 탐색 위치가 1 증가한 값이 아니면 계산을 마무리하고 curbest 최신화 후 리턴
		else if(prev + 1 != map[i][j]) {
			curBest = curBest < cnt-1 ? cnt-1 : curBest;
			return;
		}
		// 범위를 벗어나지 않는 선에서 4방탐색
		for (int k = 0; k < 4; k++) {
			if(i+dx[k] >= 0 && i+dx[k] < N && j+dy[k] >=0 && j+dy[k] < N)
				dfs(i+dx[k],j+dy[k],map[i][j],cnt+1);
		}
		
	}
}
