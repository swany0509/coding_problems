//package s0819.bj_17135;
//메모리 13584 kb 시간 100ms
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 17135 캐슬디펜스
public class Main {
	// 전역변수 선언
	static int N,M,D,archer[],maxScore,curScore,maxEnemy;
	static boolean[][] map,simul;
	static boolean end;
	static Queue<Point> queue;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N,M,D 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		// 궁수 생성
		archer = new int[3];
		// map 생성 및 입력
		map = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if(st.nextToken().equals("1")) {
					maxEnemy++;
					map[i][j] = true;
				}
			}
		}
		// 조합 시작
		comb(0,0);
		
		System.out.println(maxScore);
		
	}
	// map 객체 복사 함수
	static void arrayCopy(){
		simul = map.clone();
		// 2차원 배열이므로 속까지 복사해줘야 한다.
		for (int i = 0; i < N; i++)	simul[i] = map[i].clone();
	}
	// 적 탐지 함수
	static boolean findEnemy(boolean[][] arr, int i,int j,int d) {
		//거리는 0부터 거리까지 넓혀가며
		for (int k = 0; k <= d; k++) {
			boolean swit = true;
			// i와 j를 세팅해주고
			int ii = i;
			int jj = j-k;
			// 시작위치가 0,0, 현재 거리가 2라면 
			// ii 0	-1	2	-1	0
			// jj -2-1	0	1	2
			// 식으로 가야한다. ii 는 i행 기준 i+d까지 갔다면 감소하게 만들고, j는 계속증가하게 만든다.
			// j가 j+k까지 갔다면 거기까지만 하고 종료
			while(jj <= j+k ) {
				if( ii >= 0 && jj >= 0 && jj < M && arr[ii][jj]) {
					// 가까운 적을 탐지했다면 큐에 추가
					queue.offer(new Point(ii,jj));
					return true;
				}
				if(swit) ii--;
				else ii++;
				
				if(ii== i-k) swit = false;
				
				jj++;
			}
		}
		return false;
	}
	// 시뮬레이션 함수
	static void simulation(boolean[][] arr) {
		// 점수 초기화해주고
		curScore = 0;
		// 행을 실제로 이동하지 않아도 궁수 위치를 한 칸씩 위로 당기면 적이 가까워지는 것과 같다.
		for (int i = N-1; i >= 0 ; i--) {
			// 적 큐 리스트 생성
			queue = new LinkedList<>();
			// 거리를 담아 넘길 때 M행이 아닌 M-1행부터 시작하므로 거리를 -1하여 계산
			for (int k = 0; k < 3; k++) findEnemy(arr,i,archer[k],D-1);
			while(!queue.isEmpty()) {
				// 담아온 적들 중 중복이 안되게 점수 추가
				Point p = queue.poll();
				if(arr[p.x][p.y]) {
					curScore++;
					arr[p.x][p.y] = false;
				}
			}
		}
	}
	// 조합 알고리즘
	static void comb(int cnt, int start) {
		// 이미 적 최댓값만큼 잡았으면 더 탐지하지 않음
		if(end) return;
		// 세 궁수를 골랐으면 시뮬레이션 시작
		if(cnt==3) {
			arrayCopy();
			simulation(simul);
			// 중간에 적 최댓값만큼 잡앗으면 더이상 탐지하지 않게 한다.
			if(curScore == maxEnemy) {
				end = true;
				maxScore = curScore;
			}
			else maxScore = maxScore < curScore ? curScore : maxScore ;
			return;
		}
		// 조합 선택 알고리즘
		for (int i = start; i < M; i++) {
			archer[cnt] = i;
			comb(cnt+1,i+1);
		}
	}
}
