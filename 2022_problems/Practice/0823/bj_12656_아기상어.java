package s0823.bj_16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;
//메모리 12656 시간 100
// 백준 16236 아기상어
public class Main2 {
	// 전역변수 선언
	static int N, map[][], fishes[];
	static Shark shark;
	static int dx[] = {-1,0,0,1};
	static int dy[] = {0,-1,1,0};
	static boolean visited[][];
	// 탐색 시 용이하게 사용하기 위한 위치 클래스
	static class Block{
		int x,y,distance,value;

		public Block(int x, int y, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.distance = distance;
		}		
	}
	// 상어 클래스
	static class Shark{
		// 위치, 크기, 시간, 먹은갯수
		int x,y,size,time,feed;

		public Shark(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.size = 2;
		}
		// 실제 상어가 이동하는 함수
		public void moveTo(int x, int y,int path) {
			// 먹은 물고기를 배열에서 하나 빼준다.
			fishes[map[x][y]]--;
			// 자기 위치를 0으로 바꾸고
			map[this.x][this.y] = 0;
			// 이동하고
			this.x = x;
			this.y = y;
			// 자기 크기만큼 먹이를 먹었으면 크기 증가
			if(++feed == size) {
				size++;
				feed = 0;
			}
			// 이동한 위치 9로 변경
			map[x][y] = 9;
			// 시간 증가
			time += path;
		}
		// 먹이를 찾는 함수. BFS 구현
		public boolean searchFood() {
			// 단계적으로 큐에 넣기 위해 2개의 큐 설정
			// 단계적으로 빼는 큐
			LinkedList<Block> queue = new LinkedList<>();
			queue.offer(new Block(this.x,this.y,0));
			// 다음 탐색 후보가 들어갈 큐
			LinkedList<Block> child = new LinkedList<>();
			// 방문 배열 생성
			visited = new boolean[N][N];
			visited[this.x][this.y] = true;
			// 먹을 수 있는 물고기 찾을 때까지 반복
			while(true) {
				// 현재 탐색 큐가 비었을때까지 반복
				while(!queue.isEmpty()) {
					// 하나 빼서
					Block cur = queue.poll();
//					System.out.printf("%d %d %d\n",cur.x,cur.y,cur.distance);
					// 먹을수 있는 물고기면 바로 세팅하고 탈출
					int n = map[cur.x][cur.y];
					// 'n<=6' 이 조건 하나때문에 테케는 맞는데 4시간동안 계속 틀렸다 ㅠㅠ...
					// 상어의 크기가 10 이상이면 먹이가 없더라도 자기자신의 위치를 계속 무한호출하는 버그 발생...했었음
					if(n > 0 && n <= 6 && n < this.size) {
						// 먹는 함수 호출
						moveTo(cur.x,cur.y,cur.distance);					
						return true;
					}
					// 아니라면 다음으로 방문 가능한 위치들 추가
					for (int k = 0; k < 4; k++) {
						int ii = cur.x + dx[k];
						int jj = cur.y + dy[k];
						if(isRange(ii,jj) && !visited[ii][jj] && map[ii][jj] <= this.size) {
							child.offer(new Block(ii,jj,cur.distance+1));
							visited[ii][jj] = true;
						}
					}
				}
				// 탐색 큐가 비었는데 다음 탐색후보들이 없다면 탈출
				if(child.isEmpty()) return false;
				// 다음 탐색 후보들을 추가하고
				queue.addAll(child);
				// 다음 계산을 위해 비워주고
				child.clear();
				// 탐색 우선순위에 맞게 먼저 접근하기 위해서 탐색 큐를 comparator를 이용해 정렬한다.
				Collections.sort(queue, new Comparator<Block>() {
					@Override
					public int compare(Block o1, Block o2) {
						if(o1.distance == o2.distance) {
							if(o1.x == o2.x) return o1.y - o2.y;
							else return o1.x - o2.x;
						}
						else return o1.distance - o2.distance;
					}
				});
			}
		}
	}
	// 맞는 범위인지 판별하는 함수
	static boolean isRange(int x, int y) {
		if( x<0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	// 더 먹을수 있는 먹이가 있나 검사하는 메소드
	static boolean isThereAnyFood() {
		// 현재 크기 -1 부터 1크기의 물고기까지 검사
		// 상어 크기가 6이상이 되면 6부터 탐색하도록 설정
		for (int i = (int)Math.min(shark.size-1,6); i > 0; i--) {
			if(fishes[i] > 0) return true;
		}
		return false;
	}
	// 상어 움직임 
	static void sharkPlay() {
		while(true) {
			// 먹을 수 있는 물고기가 없으면 그만
			if(!isThereAnyFood()) break;
			// 있지만 갈수 없는 곳이어서 못먹었을 때도 그만
			if(!shark.searchFood()) break;		
			// 디버그 코드
//			System.out.println("time : " + shark.time + " ; size : " + shark.size);
//			for(int[] arr : map) {
//				for(int a : arr) System.out.print(a + " ");
//				System.out.println();
//			}
//			System.out.println();
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// N입력받고 물고기 정보 입력
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		// 물고기 현 상황을 나타내는 물고기 배열
		fishes = new int[7];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				switch(n) {
				case 0:
					break;
					// 9면 상어 생성
				case 9:
					shark = new Shark(i,j);
					break;
				// 아니면 물고기값저장하고, 물고기 갯수를 세기위해 물고기벼열도 증감
				default:
					map[i][j] = n;
					fishes[n]++;
					break;
				}
			}
		}
		// 상어 움직임 시작
		sharkPlay();
		// 끝나면 상어 객체에서 시간 반환해 출력
		System.out.println(shark.time);
		br.close();
	}

}
