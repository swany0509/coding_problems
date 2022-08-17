//package S0802.a01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// SWEA 1208 [S/W 문제해결 기본] 1일차 - Flatten
public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 다량의 입력을 받으므로 bufferedReader 를 선언한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = 100;
		
		for (int t = 1; t <= 10; t++) {
			// 덤프 횟수 입력
			int N = Integer.parseInt(br.readLine());
			
			// String 형으로 입력받은 후 이를 가로가 아닌 세로(높이) 기준으로 갯수를 저장한다.
			String[] raw = br.readLine().split(" ");
			int [] height = new int[size+1];
			// string 형으로 받은  rawdata를 int 형 높이 데이터로 변경한다.
			for (int i = 0; i < size; i++) {
				height[Integer.parseInt(raw[i])]++;
			}
			
			// 최대/최소 높이를 설정하고 초기화한다.
			int minHeight = 1;
			int maxHeight = 100;
			// 최소 높이 초기화
			for (int i = 1; i < size; i++) {
				if (height[i] != 0) {
					minHeight = i;
					break;
				}
			}
			//최대 높이 초기화
			for (int i = 100; i > 0; i--) {
				if (height[i] != 0) {
					maxHeight = i;
					break;
				}
			}
			
			// 덤프 횟수만큼 반복
			for (int i = 0; i < N; i++) {
				// 현재 최대높이를 1 깎고, 현재 최대 높이 갯수가 0이라면 최대 높이를 최신화한다.
				height[maxHeight]--;
				height[maxHeight-1]++;
				if (height[maxHeight]==0) {
					// 0이 아닌 수를 만날때까지 감소
					do maxHeight--;
					while(maxHeight > 0 && height[maxHeight]==0);
				}
				// 현재 최소높이를 1 깎고, 현재 최소 높이 갯수가 0이라면 최소 높이를 최신화한다.
				height[minHeight]--;
				height[minHeight+1]++;
				if (height[minHeight]==0) {
					// 0이 아닌 수를 만날때까지 증가
					do minHeight++;
					while(maxHeight <= size && height[minHeight]==0);
				}
			}
			
			// 최대 높이와 최소 높이의 차이를 출력한다.
			System.out.println("#" + t + " " + (maxHeight-minHeight));
		}
	}
}
