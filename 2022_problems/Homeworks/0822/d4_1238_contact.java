package s0822.d4_1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 백준 D4 1238 contact
public class Solution {
	// 전역변수 선언
	static int TC = 10;
	static int N,rootNo,last;
	static boolean map[][], isVisited[];
	static StringBuilder sb;
	static LinkedList<Integer> parents,childs;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		// 테스트케이스 별로 실행
		for (int t = 1; t <= TC; t++) {
			// 연결정보를 담을 2차원 배열 (최대 100개이므로 그냥 100X100으로 선언. 단, 1번부터 시작이므로 101개로 만든다.
			map = new boolean[101][101];
			// 방문 배열
			isVisited = new boolean[101];
			// 기본정보 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			rootNo = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N/2; i++) 
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			// bfs 알고리즘 실행
			bfs();
			// 결과 추가
			sb.append("#" + t + " " + last+ "\n");
		}
		// 결과 출력
		System.out.println(sb);
		br.close();
	}
	static void bfs() {
		// 부모 리스트와 자식 리스트 생성
		parents = new LinkedList<>();
		childs = new LinkedList<>();
		// 루트노드 설정
		parents.offer(rootNo);
		isVisited[rootNo] = true;
		while(true) {
			// 부모 리스트가 빌 때까지 진행
			while(!parents.isEmpty()) {
				// 부모를 하나 빼서
				last = parents.poll();
				// 연결된 자식을 하나씩 넣는다.
				for (int i = 1; i < 101; i++) {
					if(map[last][i] && !isVisited[i]) {
						childs.offer(i);
						isVisited[i] = true;
					}
				}
			}
			// 부모 큐가 비었는데 다음으로 가져올 자식노드들이 없다면 탐색이 끝났다는 말임.
			if(childs.isEmpty()) {
				break;
			}
			// 자식 노드들이 있다면, 이를 전부 부모 노드에 추가하고 자식 노드는 초기화한다.
			// 이 때, 부모노드를 정렬한다. (마지막으로 방문하는 노드는 그들 중 큰 수여야 하므로)
			else {
				parents.addAll(childs);
				Collections.sort(parents);
				childs.clear();
			}
			
		}
	}
}
