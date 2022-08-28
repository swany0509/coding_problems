package s0826.bj_3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 메모리 11956 시간 92
// 백준 3055 탈출
public class Main {
	// 전역변수
	static int R,C,least;
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	static Block[][] map;
	static Hogway hog;
	static ArrayList<Block> waterlist;
	// 블록 클래스
	static class Block{
		int x,y; // 좌표
		boolean visited;
		boolean goal; // 비버 굴
		boolean isWater; // 물인지 여부
		boolean isBlocked; // 막혔는지 여부
		public Block(int x, int y,char c) {
			// 생성하면서 물이라면 물 리스트에 넣는다.
			super();
			this.x = x;
			this.y = y;
			switch(c) {
			case '*':
				this.isWater = true;
				this.isBlocked = true;
				waterlist.add(this);
				break;
			case 'X':
				this.isBlocked = true;
				break;
			case 'D':
				this.goal = true;
				break;
			default:
				break;
			}
		}
	}
	// 고슴도치 현재 위치 및 시간 클래스
	static class Hogway{
		int x,y,path;

		public Hogway(int x, int y, int path) {
			super();
			this.x = x;
			this.y = y;
			this.path = path;
		}
		
	}
	// 범위 체킹 함수
	static boolean isRange(int x, int y) {
		if(x<0||x>=R||y<0||y>=C) return false;
		return true;
	}
	// bfs 알고리즘
	static void bfs() {
		// 단계별로 탐색하기 위해 큐와 다음탐색 큐를 따로 둔다.
		Queue<Hogway> queue = new LinkedList<>();
		queue.offer(hog);
		Queue<Hogway> child = new LinkedList<>();
		boolean isgoal = false;
		while(true) {
			// 물이 찬다
			ArrayList<Block> nextlist = new ArrayList<>();
			// 현재 물 리스트에서 확장가능한 곳을 찾는다.
			for(Block b : waterlist) {
				for (int i = 0; i < 4; i++) {
					int ii = b.x+dx[i];
					int jj = b.y+dy[i];
					
					if(!isRange(ii,jj)) continue;
					Block next = map[ii][jj];
					// 조건에 맞게 찾았다면 해당 위치에 변수를 바꿔주고 다음 물 리스트에 넣는다.
					if(!next.goal && !next.isWater && !next.isBlocked) {
						next.isWater = true;
						next.isBlocked = true;
						nextlist.add(next);
					}
				}
			}
			// 이미 한번 탐색한 물 리스트는 다음엔 확장하지 않는다. 이번에 탐색한 물리스트로 갱신해준다.
			waterlist = nextlist;
			
			// 고슴도치가 다음 움직일 곳을  child에 넣는다.
			while(!queue.isEmpty()) {
				// 고슴도치 위치를 뽑고 도착점이라면 탈출
				Hogway current = queue.poll();
				map[current.x][current.y].visited = true;
				
				if(map[current.x][current.y].goal) {
					isgoal = true;
					least = current.path;
					break;
				}
				
				// 아니라면 다음 갈 곳을 정해서 간다.
				for (int i = 0; i < 4; i++) {
					int ii = current.x+dx[i];
					int jj = current.y+dy[i];
					
					if(!isRange(ii,jj)) continue;
					Block point = map[ii][jj];
					// 범위 내라면 방문처리하고 다음 큐에 넣는다.
					if(!point.visited && !point.isWater && !point.isBlocked) {
						map[ii][jj].visited = true;
						child.offer(new Hogway(ii,jj,current.path+1));
					}
				}
				
			}
			// 다음 갈 곳이 없거나 도착했다면 탈출한다.
			if(child.isEmpty() || isgoal) break;
			
			// 다음 갈 곳이 있다면 큐로 옮긴다.
			queue.addAll(child);
			child.clear();
			
		}
	}
	// 메인 함수
	public static void main(String[] args) throws IOException {
		// 입력 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// R,C 입력
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new Block[R][C];
		waterlist = new ArrayList<>();
		// 입력을 받으면서 s면 시작 위치를 정한다.
		for (int i = 0; i < R; i++) {
			String buffer = br.readLine();
			for (int j = 0; j < C; j++) {
				char c = buffer.charAt(j);
				if(c=='S') hog = new Hogway(i,j,0);
				map[i][j] = new Block(i,j,c);
			}
		}
		least = Integer.MAX_VALUE;
		
		bfs();
		// 결과 출력
		if(least == Integer.MAX_VALUE) System.out.println("KAKTUS");
		else System.out.println(least);
	}

}
