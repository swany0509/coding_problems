//package S0802.a02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// SWEA 1210 [S/W 문제해결 기본] 2일차 - Ladder1
public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 다량의 입력을 받으므로 bufferedReader 를 선언한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = 100;
		
		for (int t = 1; t <= 10; t++) {
			int tc = Integer.parseInt(br.readLine());
			// 데이터를 입력받는다.
			String[][] data = new String[size][size];
			
			for (int i = 0; i < size; i++) {
				data[i] = br.readLine().split(" ");
			}
			
			int ii = size-1; int jj = 0;
			// 맨 아랫줄을 탐색하여 목적지를 설정한다.
			for (int j = 0; j < size; j++) {
				if(data[99][j].equals("2")) {
					jj = j;
					break;
				}
			}
			
			// ii=0에 도달할때까지 반복한다.
			while(true) {
				// 왼쪽이 1이라면
				if((jj-1) >= 0 && data[ii][jj-1].equals("1")) {
					jj--;
					// 1회 이동 후 무한반복에 걸리지 않게 해당 방향으로 갈 수 있을 때까지 직진한다.
					//왼쪽 길 끝까지 직진
					while((jj-1) >= 0 && data[ii][jj-1].equals("1")) jj--;
					if (ii != 0) ii--;
				}
				// 오른쪽이 1이라면
				else if((jj+1) < size && data[ii][jj+1].equals("1")) {
					jj++;
					// 1회 이동 후 무한반복에 걸리지 않게 해당 방향으로 갈 수 있을 때까지 직진한다.
					//오른쪽 길 끝까지 직진
					while((jj+1) < size && data[ii][jj+1].equals("1")) jj++;
					if (ii != 0) ii--;
				}
				
				// 도착했다면 탈출한다.
				else if(ii==0) {
					break;
				}
				// 아니라면 위로 간다.
				else {
					ii--;
				}
								
			}
			// 도달했다면 jj좌표를 출력한다.
			System.out.println("#" + t + " " + jj);
		}
	}

}

