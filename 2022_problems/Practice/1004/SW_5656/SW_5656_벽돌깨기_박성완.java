import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
//99,296 kb 244 ms 
//범위가 넓을 것을 예상하여 ArrayList로 풀었지만... 메모리가 낭비다
// 나중에 다시 풀어보자.
public class Solution {
	// 전역변수 선언
	static int TC, N, W, H, bricks, maxim;
	static List<Integer> map[];
	static StringBuilder sb;
	static boolean visited[][];
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static class Block implements Comparable<Block>{
		int x, y;


		public Block(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}


		@Override
		public int compareTo(Block o) {
			return this.x == o.x ? o.y-this.y : o.x - this.x;
		}
		
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			// 배열을 90도 돌려서 arrayList로 관리한다. w,h의 순서가 바뀐다. 바닥이 0(w),0(h)이다.
			map = new List[W]; 
			// 리스트를 선언하고
			for (int i = 0, size = W; i < size; i++) {
				map[i] = new ArrayList<>();
			}
			
			bricks = 0;
			// 한줄씩 리스트에 넣는다.
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0, size = W; j < size; j++) {
					int n = Integer.parseInt(st.nextToken());
					if(n != 0) {
						// 0이 아닐 경우에만 앞으로 넣는다.
						map[j].add(0,n);
						bricks++;
					}
				}
			}
			
			maxim = Integer.MIN_VALUE;
			
			// 중복 순열로 계산
			perm(0,map,0);
			
			sb.append("#" + t + " " + (bricks - maxim) + "\n");
			
		}
		System.out.println(sb);
		br.close();
	}
	// 중복 순열 알고리즘
	static void perm(int cnt, List<Integer>[] list, int score) {
		// 결과값 갱신
		if(cnt == N) {
			maxim = Math.max(maxim, score);
			return;
		}
		
		for (int i = 0; i < W; i++) {
			// 이미 최댓값이면 계산x
			if(score == bricks) {
				maxim = score;
				return;
			}
			// 처음 부딪힌 위치가 비었다면 넘어감
			if(list[i].isEmpty()) continue;
			// 리스트 배열을 복사한다 (메모리 원흉 1)
			List<Integer>[] temp = copyOf(list);
			// 부분 시뮬레이션으로 점수를 계산한다.
			int value = score + breakBrick(temp,i);
			// 다음 순열로 넘긴다.
			perm(cnt+1,temp, value);			
		}
	}
	// 점수 계산 알고리즘
	static int breakBrick(List<Integer>[] list, int idx) {
		int score = 0;
		// 중복 방문을 막기 위해 방문 배열을 선언한다.
		visited = new boolean[W][H];
		// 탐색용 큐와 결과 제거용 큐를 만든다. (메모리의 원흉 2)
		Queue<Block> queue1 = new LinkedList<>();
		// 맨 위 위치부터 빼기 위해 우선순위 큐로 선언
		PriorityQueue<Block> queue2 = new PriorityQueue<>();
		int l = list[idx].size()-1;
		queue1.offer(new Block(idx,l));
		queue2.offer(new Block(idx,l));
		visited[idx][l] = true;
		while(!queue1.isEmpty()) {
			Block cur = queue1.poll();
			score++;
			if(list[cur.x].get(cur.y) == 1) continue;
			
			// 4가지 방향에서 1이 아니면 상하좌우로 확장하여 넣는다.
			for (int k = 0; k < 4; k++) {
				int cnt = list[cur.x].get(cur.y);
				int nn = 1;
				while(nn < cnt) {
					int ii = cur.x + dx[k]*nn;
					int jj = cur.y + dy[k]*nn;
					nn++;
					
					// 조건 작성 시 조심. 0 때문에 size의 길이가 다르다.
					if(ii<0||ii>=W||jj<0||jj>=list[ii].size()||visited[ii][jj]) continue;
					
					queue1.offer(new Block(ii,jj));
					queue2.offer(new Block(ii,jj));
					visited[ii][jj] = true;
					
				}
			}
			
		}
		
		int size = queue2.size();
		// priorityqueue에 저장되있던 값을 하나씩 빼면서 리스트의 블럭 위치를 제거한다.
		while(size-->0) {
			Block cur = queue2.poll();
			list[cur.x].remove(cur.y);
		}
		return score;
	}
	// 리스트 복사 알고리즘
	static List<Integer>[] copyOf(List<Integer>[] list){
		@SuppressWarnings("unchecked")
		List<Integer>[] temp = new List[W]; 
		
		for (int i = 0; i < W; i++) {
			temp[i] = new ArrayList<>();
			for(Integer n : list[i]) temp[i].add(n);
		}
		
		return temp;
	}

}
