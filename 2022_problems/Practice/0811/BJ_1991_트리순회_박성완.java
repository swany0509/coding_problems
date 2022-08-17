package s0811.bj_1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 백준 1991 트리 순회
public class Main {
	// 전역변수 생성
	static int N, noChild; // 갯수, 자식노드없음변수
	static Node[] tree;
	static StringBuilder sb;
	// 노드 클래스 생성
	static class Node{
		public int data;
		public int left;
		public int right;
		public Node(int data, int left, int right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		// N 입력받고 트리 생성
		N = Integer.parseInt(br.readLine());
		tree = new Node[N];
		// char형 입력들을 (A~Z) int형 인덱스로 바꿔준다 (0~25)
		// 번호를 임의로 생성했기 때문에 0번부터 그대로 채워준다.
		// 모든 입력에서 'A'만큼 뺄거기 대문에 자식노드가 없는 경우도 아래와 같이 선언해준다.
		noChild = (int)('.' - 'A');
		// 노드를 입력받는다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			tree[i] = new Node(st.nextToken().charAt(0)-'A',st.nextToken().charAt(0)-'A',
					st.nextToken().charAt(0)-'A');
		}
		// 인덱스와 데이터 값이 같아지도록 정렬한다.
		Arrays.sort(tree,(n1,n2)->n1.data-n2.data);
		
		// 세 경우를 나눠서 탐색한다.
		preOrder(0);
		sb.append("\n");
		inOrder(0);
		sb.append("\n");
		postOrder(0);
		
		System.out.println(sb);
		
	}
	// 전위 순회
	public static void preOrder(int i) {
		sb.append((char)(tree[i].data+'A'));
		if(tree[i].left != noChild) preOrder(tree[i].left);
		if(tree[i].right != noChild) preOrder(tree[i].right);
	}
	// 중위 순회
	public static void inOrder(int i) {
		if(tree[i].left != noChild) inOrder(tree[i].left);
		sb.append((char)(tree[i].data+'A'));
		if(tree[i].right != noChild) inOrder(tree[i].right);
	}
	// 후위 순회
	public static void postOrder(int i) {
		if(tree[i].left != noChild) postOrder(tree[i].left);
		if(tree[i].right != noChild) postOrder(tree[i].right);
		sb.append((char)(tree[i].data+'A'));
	}
}
