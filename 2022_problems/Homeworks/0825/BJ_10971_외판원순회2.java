package s0824.bj_10971;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 10971 외판원 순회 2
public class Main {
	// 전역변수 설정
	static int N, adjMatrix[][],leastValue,current[];
	static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 설정
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		// 각종 행렬 설정
		adjMatrix = new int[N][N];
		current = new int[N];
		visited = new boolean[N];
		// 인접행렬 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 최솟값을 int 최댓값으로 초기화
		leastValue = Integer.MAX_VALUE;
		// 순열 알고리즘 실행
		perm(0,0);
		// 결과 출력
		System.out.println(leastValue);
		br.close();
		
	}
	// 순열 알고리즘
	static void perm(int cnt,int value) {
		// 투트가 완성되면 마지막 길을 이어준다.
		if(cnt==N) {
			int last = adjMatrix[current[N-1]][current[0]];
			// 마지막으로 돌아오는 길이 없다면 계산하지 않는다.
			if(last == 0) return;
			leastValue = Math.min(leastValue, value + last);
			return;
		}
		// 순열 선택
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			current[cnt] = i;
			// 첫 정점이라면 그냥 넘겨준다.
			if(cnt==0) perm(cnt+1,0);
			else {
				// 선택한 순열이 이어진 길이 아니라면 계산하지 않는다.
				int num = adjMatrix[current[cnt-1]][current[cnt]];
				// 이어진 길이라면 계산해서 다음 순열로 넘긴다.
				if(num != 0 && num < leastValue) perm(cnt+1,value + num);
			}
			visited[i] = false;
		}
	}
}
