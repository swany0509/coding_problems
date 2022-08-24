package s0824.bj_1251;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// SWEA 1251 하나로
public class Solution {
	// 전역변수 설정
	static int TC,N,xdata[];
	static double E, value;
	static ArrayList<Point> island;
	static StringBuilder sb;
	static ArrayList<Edge> edges;
	static int[] parents;
	// 간선 클래스
	static public class Edge implements Comparable<Edge>{
		int from,to;
		double weight;
		public Edge(int from, int to) {
			super();
			this.from = from;
			this.to = to;
			// 비용은 생성할 때 간선을 참조해서 만든다.
			this.weight = E*distance(island.get(from),island.get(to));
		}
		// 비교방식은 비용. 단, 비용이 1미만으로 차이나는 경우가 있으므로 대충 1억곱해줘서 비교해준다.
		@Override
		public int compareTo(Edge o) {
			return (int)(10000000*(this.weight - o.weight));
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 설정
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		// 테스트 케이스 입력
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			// 각종 변수 초기화
			N = Integer.parseInt(br.readLine());
			island = new ArrayList<>();
			edges = new ArrayList<>();
			// 출력할 값
			value = 0;
			// 부모 배열 초기화
			parents = new int[N];
			for (int i = 0; i < N; i++) {
				parents[i] = i;
			}
			// x,y정보 입력받고 점 좌표와 간선 데이터 생성
			xdata = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				xdata[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int ydata = Integer.parseInt(st.nextToken());
				island.add(new Point(xdata[i],ydata));
			}
			E = Double.parseDouble(br.readLine());

			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					edges.add(new Edge(i,j));
				}
			}
			// 간선을 비용을 기준으로 정렬
			Collections.sort(edges);
			// 크루스칼 알고리즘 실행
			value = krusKal();
			// 결과 추가
			sb.append("#" + t + " " + (long)Math.round(value) + "\n");
		}
		// 결과 출력
		System.out.println(sb);
		br.close();

	}
	// 크루스칼 알고리즘
	static double krusKal() {
		int count = 0;
		for(Edge edge : edges) {
			if(union(edge.from,edge.to)) {
				value += edge.weight;
				if(++count == N-1) return value;
			}
		}
		return value;
	}
	// 부모를 찾는 메소드
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	// 서로소인지 판별하고 아니라면 합치는 메소드
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot==bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	// 거리 계산 메소드
	static double distance(Point a, Point b) {
		return Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y,2);
	}
}
