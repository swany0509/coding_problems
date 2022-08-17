import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1074 Z
public class Main {
	// 전역 변수
	static int N,r,c,start,end, block;
	// index 클래스
	static class Index{
		int x,y;
		public Index(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 입력값 저장
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		// 행열크기 선언
		int num = (int)Math.pow(2, N);
		// 시작값과 끝 값 선언
		start = 0; end = (int)Math.pow(num, 2);
		// 한 단계마다 제하는 값
		block = end/2;
		// 사각형의 왼쪽 위, 오른쪽 아래 꼭짓점 선언
		int x1 = 0 ; int y1 = 0; int x2 = num-1; int y2 = num-1;
		// 상하로 반, 죄우로 반씩 나눠서 탐색한다.
		while(true) {
			// 상하를 나눈다. half1은 위 사각형의 오른쪽 아래 점이다.
			Index half1 = new Index((x1+x2)/2, y2);
			// 기준점 이하에 있다면
			if(half1.x >= r) {
				// 같다면 출력하고 탈출
				if(half1.x == r && half1.y == c){
					System.out.println(end - block - 1);
					break;
				}
				// 미만이라면 뒷값을 날리고 오른쪽 아래 꼭짓점 최신화
				end -= block;
				x2 = half1.x;	y2 = half1.y;
			}else {
				// 초과라면 앞값을 날리고 왼쪽 위 꼭짓점 최신화
				start += block;
				x1 = (x1+x2)/2+1;
			}
			// 제하는 값 나누기
			block /= 2;
			// 좌우를 나눈다. half2는 오른쪽 사각형의 왼쪽 위 점이다.
			Index half2 = new Index(x1,(y1+y2)/2+1);
			// 기준점 이상에 있다면
			if(half2.y <= c) {
				// 같다면 출력하고 탈출
				if(half2.x == r && half2.y == c){
					System.out.println(start + block);
					break;
				}
				// 초과라면 앞값을 날리고 왼쪽 위 꼭짓점 최신화
				start += block;
				x1 = half2.x;	y1 = half2.y;
			}else {
				// 미만이라면 뒷값을 날리고 오른쪽 아래 꼭짓점 최신화
				end -= block;
				y2 = (y1+y2)/2;
			}
			// 제하는 값 나누기
			block /= 2;
			
		}
	}
}
