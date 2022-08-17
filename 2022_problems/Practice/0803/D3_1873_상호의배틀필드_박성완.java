//package S0803.d3_1873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// SWEA D3 1873 상호의 배틀필드
/*
 * 문자	의미
 * .	평지(전차가 들어갈 수 있다.)
 * *	벽돌로 만들어진 벽
 * #	강철로 만들어진 벽
 * -	물(전차는 들어갈 수 없다.)
 * ^	위쪽을 바라보는 전차(아래는 평지이다.)
 * v	아래쪽을 바라보는 전차(아래는 평지이다.)
 * <	왼쪽을 바라보는 전차(아래는 평지이다.)
 * >	오른쪽을 바라보는 전차(아래는 평지이다.)
 * 다음 표는 사용자가 넣을 수 있는 입력의 종류를 나타낸다.
 
 * 문자	동작
 * U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
 * D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
 * L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
 * R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
 * S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
 * 
 * 부가설명
 * 전차가 이동을 하려고 할 때, 만약 게임 맵 밖이라면 전차는 당연히 이동하지 않는다.
 * 전차가 포탄을 발사하면, 포탄은 벽돌로 만들어진 벽 또는 강철로 만들어진 벽에 충돌하거나 게임 맵 밖으로 나갈 때까지 직진한다.
 * 만약 포탄이 벽에 부딪히면 포탄은 소멸하고, 부딪힌 벽이 벽돌로 만들어진 벽이라면 이 벽은 파괴되어 칸은 평지가 된다.
 * 강철로 만들어진 벽에 포탄이 부딪히면 아무 일도 일어나지 않는다.
 * 게임 맵 밖으로 포탄이 나가면 아무런 일도 일어나지 않는다.
 */

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력은 bufferedReader로 받는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 테스트 케이스 입력
		int TC = Integer.parseInt(br.readLine());
		// 너비, 높이를 받을 때에만 stringTokenizer 를 사용한다.
		StringTokenizer st = null;
		
		// 중간 계산을 편리하게 하기 위해 만든 HashMap.
		// dire는 방향문자 배열과 증감 배열을 접근하기 위해 현재 방향을 입력받아 숫자로 반환한다.
		HashMap<Character,Integer> dire = new HashMap<>();
		dire.put('^', 0); dire.put('>', 1); dire.put('v', 2); dire.put('<', 3);
		// com_dire 는 명령을 입력받아 맞는 방향문자로 반환해준다.
		HashMap<Character, Character> com_dire = new HashMap<>();
		com_dire.put('U', '^');com_dire.put('R', '>');com_dire.put('D', 'v');com_dire.put('L', '<');
		
		// 숫자로 다시 방향을 찾기 위한 방향문자 배열
		char[] dire_i = {'^','>','v','<'};
		// 한번에 처리하기 위한 증감 배열
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		// 헤드의 방향. 실제 방향문자나 증감 배열에 접근하는 숫자이다.
		int head = 0; // ^ 0 ; > 1 ; v 2 ; < 3 
		// 현재 탱크의 방향문자.
		char currentHead = ' ';
		
		for (int t = 1; t <= TC; t++) {
			// 너비와 높이 입력.
			int H,W;
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			// 맵을 입력받는다.
			char [][] map = new char[H][W];
			int ii = -1; int jj = -1;
			// 초기위치를 찾았다는 여부를 판별하기 위한 중간 스위치 변수.
			boolean locationFound = false;
			
			// 맵을 한 줄씩 입력받으면서, 동시에 초기 탱크의 위치를 파악해 저장한다.
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					if (locationFound) break;
					switch(map[i][j]) {
					// 탱크를 찾으면, 방향, 현재위치, 초기위치 를 세팅하고, 찾았다고 선언하기 위해 스위치 변수를 바꾼다.
					case '^':	case '>':	case 'v':	case '<':
						head = dire.get(map[i][j]);
						currentHead = map[i][j];
						ii = i; jj = j;
						locationFound = true;
						break;
					default:
						break;
					}
					
					}
				}
			
			// 명령 갯수 입력
			int N = Integer.parseInt(br.readLine());
			// 명령 입력
			String command = br.readLine();
			// 명령 수만큼 실행
			for (int i = 0; i < N; i++) {
				// 스위치 문을 이용해 명령별로 실행
				switch(command.charAt(i)) {
				// 이동문자를 만나면 해당 방향으로 바로 이동한다.
				case 'U': case 'R': case 'D': case 'L': // 해당 방향으로 이동
					// 먼저 상태를 최신화한다.
					currentHead = com_dire.get(command.charAt(i)); // 해당 방향으로 방향 문자 저장
					head = dire.get(currentHead); // 방향 숫자 저장;
					// 현 위치를 먼저 바꾼다.
					map[ii][jj] = currentHead;
					// 범위를 벗어나지 않았다면
					if(ii+dx[head] >= 0 && ii + dx[head] < H && jj+dy[head] >= 0 && jj+dy[head] < W ) {
						// 스위치문으로 던진다.
						switch(map[ii+dx[head]][jj+dy[head]]) {
						// 물/벽을 만났다면 이동하지 못한다.
						case '-': case '#': case '*':
							break;
						// 그 외의 경우라면 윈래 위치를 평지로 바꾸고 다음 위치에 탱크를 이동시킨다.
						default:
							map[ii][jj] = '.';
							map[ii+dx[head]][jj+dy[head]] = currentHead;
							ii += dx[head];
							jj += dy[head];
							break;
						}
					}
					break;
				//  shoot 명령에는 이동은 없고 포탄만 발사한다.
				case 'S': // shoot
					// 포탄의 위치 변수.
					int curi = ii;
					int curj = jj;
					// 범위를 벗어나지 않았다면
					while(curi+dx[head] >= 0 && curi + dx[head] < H && curj+dy[head] >= 0 && curj+dy[head] < W ) {
						// 위치를 바꾸고
						curi += dx[head];
						curj += dy[head];
						// 강철 벽이면 아무일도 일어나지 않음
						if( map[curi][curj] == '#'){
							break;
						// 벽돌 벽이면 벽이 부서지고 끝남.
						}else if (map[curi][curj] == '*') {
							map[curi][curj] = '.';
							break;
						// 그외의 경우는 다음 경우를 던진다.
						}else continue;
					}
					break;
				default:
					break;
				}
				
//				디버깅
//				System.out.println("move " + (i+1) + " ; Command : " + command.charAt(i));
//				for (char[] cs : map) {
//					for (char c : cs) {
//						System.out.print(c + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
			}
			//양식에 맞게 출력한다.
			System.out.print("#"+t + " ");
			for (char[] cs : map) {
				for (char c : cs) {
					System.out.print(c);
				}
				System.out.println();
			}
		}		
	}
}
