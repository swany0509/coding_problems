package s0824.d4_3289;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SWEA 3289 서로소집합
public class Solution {
	// 전역변수 선언
	static int TC,N,M;
	static int parent[];
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		// 테스트케이스만큼 실행
		for (int t = 1; t <= TC; t++) {
			sb.append("#" + t + " ");
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			// 부모 배열 생성
			parent = new int[N];
			for (int i = 0; i < N; i++) parent[i] = i;
			// 명령어 실행
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				// 명령어 종류에 따라 분기
				switch(command) {
				case "0":
					// 0이면 합친다
					union(a,b);
					break;
				case "1":
					// 1이면 루트가 같은지의 여부만 확인한다.
					if(isSibling(a,b)) sb.append("1");
					else sb.append("0");
					break;
				default:
					break;
				}
			}
			
			
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();

	}
	// 루트를 찾는 메소드
	static int find(int a) {
		// 루트가 자기자신이면 반환
		if(parent[a] == a) return a;
		// 아니면 재귀형식으로 호출하면서 현재까지 부른 부모들도 동시에 수정
		return parent[a] = find(parent[a]);
	}
	// 합집합 메소드
	static void union(int a, int b) {
		// 각 노드의 루트를 찾고
		int aRoot = find(a);
		int bRoot = find(b);
		// 다르다면 합친다.
		if(aRoot != bRoot) parent[bRoot] = aRoot;
	}
	// 루트가 같은지 확인하는 메소드
	static boolean isSibling(int a, int b) {
		// 단순히 루트를 비교만 한다.
		if( find(a) != find(b) ) return false;
		return true;
	}
}
