package s0824.d4_3124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// SWEA d4 3124 스패닝 트리
public class Solution {
	// 전역변수 선언
	static int TC,V,E,parent[];
	static PriorityQueue<Edge> queue;
	static StringBuilder sb;
	// 간선 클래스 선언
	static class Edge implements Comparable<Edge>{
		int from, to;
		// 가중치가  int를 넘을 수도 있다. long 으로 선언한다.
		long weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// long 값을 비교하므로 일부러 0/1/-1로 분기하게 한다.
			if(this.weight == o.weight) return 0;
			else if (this.weight > o.weight) return 1;
			else return -1;
		}
		
	}
	// 부모를 찾는 메소드
	static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	// 서로소 집합인지 판단하며, 서로소라면 합친다.
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot != bRoot) {
			parent[bRoot] = aRoot;
			return true;
		}
		return false;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		// 테스트케이스만큼 실행
		for (int t = 1; t <= TC; t++) {
			// 가중치가  int 범위를 넘을 수 있으므로 long으로 선언
			long value = 0;
			// v,e입력
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			// 부모배열 초기화
			parent = new int[V];
			for (int i = 0; i < V; i++) parent[i] = i;
			// 우선순위 큐를 선어나고 간선을 하나씩 입력
			queue = new PriorityQueue<>();
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				// 정점이 1~N이므로 -1해서 조정
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				int w = Integer.parseInt(st.nextToken());
				queue.offer(new Edge(a,b,w));
			}
			// 간선을 하나씩 빼서 kruskal 알고리즘 실행
			int count = 0;
			while(!queue.isEmpty()) {
				Edge cur = queue.poll();
				if(union(cur.from,cur.to)) {
					value += cur.weight;
					// 0에서 시작햇으므로 종료조건은 V-1이다.
					if(++count == V-1) break;
				}
			}
			// 결과 추가
			sb.append("#" + t + " " + value + "\n");
		}
		System.out.println(sb);
		br.close();
	}

}
