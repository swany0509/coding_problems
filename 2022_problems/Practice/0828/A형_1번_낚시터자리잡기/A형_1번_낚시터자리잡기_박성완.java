package daily.a1_sw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 풀이 시간 51분 6초 + 주석 시간 10분
// 메모리 26,208kb 시간 119ms
// A형 연습문제 1번 낚시터 자리잡기
public class Solution {
	
	// 전역변수 설정
	static int TC,N,least,order[]; // 테스트케이스, N, 최솟값, 게이트 순서
	static boolean visited[],orderVisited[]; // 낚시터 방문 배열, 게이트 순서 방문배열(순열 생성 위함)
	static StringBuilder sb; // 스트링빌더
	static Gate[] gates; // 게이트 배열
	
	// 게이트 클래스
	static class Gate{
		// 위치와 사람 수
		int idx,man;
		// 낚시터에 자리잡은 사람들의 위치 큐
		Queue<Integer> where;
		public Gate(int idx, int man) {
			super();
			this.idx = idx;
			this.man = man;
			where = new LinkedList<>();
		}
		
		// 왼쪽 우선 탐색으로 넣는 경우
		public int leftFirstSearch() {
			int value = 0; // 반환값
			int count = 0; // 중간변수
			int amount = this.man; // 총 넣을 횟수
			// 탐색 순서 예시는 게이트위치가 4라고 햇을 때 4,3,5,2,6,1,7..이다.
			while(amount != 0) {
				// 왼쪽부터 보아서
				int left = this.idx-count;
				// 범위 안이면서 아직 방문하지 않았다면
				if(left >= 0 && !visited[left]) {
					visited[left] = true; // 방문처리
					amount--; // 넣을 횟수 감소
					value += count+1; // 인덱스 탐색할 때 게이트와의 거리 +1만큼이 낚시꾼이 이동한 거리이다.
					where.offer(left); // 나중에 되돌리기 위해 큐에 위치 정보를 넣는다.
				}
				
				// 중간에 다 넣었다면 탈출
				if(amount == 0) break;
				
				// 오른쪽은 방향만 반대지 똑같다.
				int right = this.idx+count;
				if(right < N && !visited[right]) {
					visited[right] = true;
					amount--;
					value += count+1;
					where.offer(right);
				}
				// 다음 탐색때는 더 넓은 범위를 보기 위해 중간변수 값 증가
				count++;
				
			}
			return value;
		}
		
		// 오른쪽 우선 탐색으로 넣는 경우
		// 바로 위 함수와 순서만 반대지 내용은 같다.
		public int rightFirstSearch() {
			int value = 0;
			int count = 0;
			int amount = this.man;
			// 탐색 순서 예시는 게이트위치가 4라고 햇을 때 4,5,3,6,2,7,1..이다.
			while(amount != 0) {
				// 오른쪽부터 넣는다.
				int right = this.idx+count;
				if(right < N && !visited[right]) {
					visited[right] = true;
					amount--;
					value += count+1;
					where.offer(right);
				}
				
				if(amount == 0) break;
				// 그 다음 왼쪽을 본다.
				int left = this.idx-count;
				if(left >= 0 && !visited[left]) {
					visited[left] = true;
					amount--;
					value += count+1;
					where.offer(left);
				}
				
				count++;
				
			}
			return value;
		}
		
		// 순열 계산을 용이하게 하기 위해서 방금 게이트 계산을 되돌리는 함수
		// 큐 요소를 빼서 그 위치를 false로 바꾼다.
		public void unDo() {
			while(!where.isEmpty()) {
				visited[where.poll()] = false;
			}
		}
		
	}
	
	// 게이트 순서와 시뮬레이션을 할 순열 알고리즘
	static void perm(int cnt,int value) {
		// 3번까지 정했다면 최솟값을 갱신한다.
		if(cnt == 3) {
			least = Math.min(least, value);
			return;
		}
		// 순열 선택
		for (int i = 0; i < 3; i++) {
			if(orderVisited[i]) continue;
			// 순서를 선택하고
			orderVisited[i] = true;
			
			order[cnt] = i;
			// 홀수인 경우 왼쪽부터 채울지 오른쪽 부터 채울지 상관이 없다. 둘 중 한군데만 한다.
				// 왼쪽 먼저 탐색 방법으로 채우고 그 비용을 반환한다.
				int leftFirst = gates[i].leftFirstSearch();
				// 그 값과 더한 값이 최솟값을 넘으면 되돌린다.
				if( least < value + leftFirst) {
					gates[i].unDo();
				// 아니면 다음 순열로 돌린다.
				}else {
					perm(cnt+1,value + leftFirst);
				}
			// 다녀왔으면 다음 계산이 있을수도 있으므로 되돌리기 연산을 한다. 안했을 경우도 어차피 큐가 비어서 계산이 중단된다.
			gates[i].unDo();
			
			// 하지만 짝수인 경우 둘 다보아야 한다.
			if(gates[i].man %2 == 0) {
				// 오른쪽 먼저 탐색 방법으로 채우고 그 비용을 반환한다.
				int rightFirst = gates[i].rightFirstSearch();
				// 그 값과 더한 값이 최솟값을 넘으면 되돌린다.
				if( least < value + rightFirst) {
					gates[i].unDo();
				// 아니면 다음 순열로 돌린다.
				}else {
					perm(cnt+1,value + rightFirst);
				}
			}
			// 다녀왔으면 초기화를 위해 되돌리기 연산을 한다. 안했을 경우도 어차피 큐가 비어서 계산이 중단된다.
			gates[i].unDo();
			// 순열 계산을 마쳤으니 반환한다.
			orderVisited[i] = false;
			
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		// 테스트 케이스 입력
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			// N을 입력받고 각종 변수 초기화
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N];
			least = Integer.MAX_VALUE; // 최솟값을 int최댓값으로 초기화
			gates = new Gate[3];
			order = new int[3];
			orderVisited = new boolean[3];
			// gate 값 입력
			for (int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				
				int index = Integer.parseInt(st.nextToken())-1; // 1~N -> 0~N-1 로 바꾼다.
				int num = Integer.parseInt(st.nextToken());
				
				gates[i] = new Gate(index,num);
			}
			// 순열 알고리즘 실행
			perm(0,0);
			// 결과 추가
			sb.append("#" + t + " " + least + "\n");
		}
		// 결과 출력
		System.out.println(sb);
		br.close();
	}
}
