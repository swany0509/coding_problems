//package s0826.bj_17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 14456 시간 292
// 백준 17144 미세먼지 안녕!
public class Main {
	// 전역 변수 정의
	static int R,C,T,amount;
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	static Block[][] map;
	static Vacuum vacuum;
	// 블록 칸을 나타내는 클래스 정의
	static class Block{
		int x,y,dust,added;
	
		public Block(int x, int y, int dust) {
			super();
			this.x = x;
			this.y = y;
			this.dust = dust;
		}
		// 나눠진 값 가져오기
		public int getDivide() {
			return dust/5;
		}
		// 더해지기
		public void spreaded() {
			dust += added;
		}
	}
	// 청소기 클래스
	static class Vacuum{
		int tx,ty,bx,by;

		public Vacuum(int x, int y) {
			super();
			this.tx = x;
			this.ty = y;
			this.bx = x+1;
			this.by = y;
		}
		
		// 위쪽 청소
		public void rollUp() {
			for(int x=tx;x>1;x--) map[x][1] = map[x-1][1];
			for(int y=1;y<C;y++) map[1][y] = map[1][y+1];
			for(int x=1;x<tx;x++) map[x][C] = map[x+1][C];
			for(int y=C;y>1;y--) map[tx][y] = map[tx][y-1];
			
			map[vacuum.tx][vacuum.ty].dust = 0;
		}
		// 아래쪽 청소
		public void rollDown() {
			for(int x=bx;x<R;x++) map[x][1] = map[x+1][1];
			for(int y=1;y<C;y++) map[R][y] = map[R][y+1];		
			for(int x=R;x>bx;x--) map[x][C] = map[x-1][C];
			for(int y=C;y>1;y--) map[bx][y] = map[bx][y-1];
			
			map[vacuum.bx][vacuum.by].dust = 0;
		}
	}
	// 범위 밖이거나 공기청정기 위치면 false
	static public boolean isRange(int x, int y) {
		if(x<=0||x>R||y<=0||y>C||
				(x==vacuum.tx && y==vacuum.ty)||
				(x==vacuum.bx && y==vacuum.by)) return false;
		return true;
	}

	static public void spread() {
		// 각 구역별로 확산
		// 공기청정기 위치는 dust가 항상 0이므로 확산되지 않음
		for (int i = 1; i <=R; i++) {
			for (int j = 1; j <=C; j++) {
				if(map[i][j].dust==0) continue;
				
				int spreaded = 0;
				int divided = map[i][j].getDivide();
				// 4방향으로 탐색하여 유효한 범위면 확산을 진행
				// 단 실시간으로 확산하면 계산이 꼬이므로 block의  added에 임시로 저장
				for (int k = 0; k < 4; k++) {
					int ii = i+dx[k];
					int jj = j+dy[k];
					
					if(isRange(ii,jj)) {
						map[ii][jj].added += divided;
						spreaded += divided;
					}
				}
				// 확산된 만큼 뺀다.
				map[i][j].dust -= spreaded;
			}
		}
		// 확산된 block 들을  최신화
		for (int i = 1; i <=R; i++) {
			for (int j = 1; j <=C; j++) {
				map[i][j].spreaded();
				// 확산된 값을 초기화 해준다.
				map[i][j].added = 0;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력 방식 정의
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 데이터 입력
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new Block[R+1][C+1];
		
		boolean vacuumFound = false;
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <=C ; j++) {
				int num = Integer.parseInt(st.nextToken());
				// -1이면 청소기 정의하고 변수로 다음 줄에선 생성 안하게 설정
				if(num == -1) {
					if(!vacuumFound) {
						vacuumFound = true;
						vacuum = new Vacuum(i,j);
						
					}
					// 청소기 위치는 0
					map[i][j] = new Block(i,j,0);
				}
				else map[i][j] = new Block(i,j,num);
			}
		}
		//  T 초 만큼 진행
		for (int t = 0; t < T; t++) {
			spread();
			vacuum.rollUp();
			vacuum.rollDown();	
		}
		
		// 값 더하기
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				amount += map[i][j].dust;
			}
		}
		// 출력
		System.out.println(amount);
	}
	// 디버깅용
	static void printMap() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				System.out.print(map[i][j].dust + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
}
