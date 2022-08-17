package s0802.d2_1954;

import java.util.Scanner;
// D2 1954 달팽이 숫자
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테스트 케이스 입력
		int TC = sc.nextInt();
		
		for (int t = 1; t <= TC; t++) {
			// 크기를 입력받고 배열 생성
			int N = sc.nextInt();
			int[][] data = new int[N][N];
			
			// 기본 로직은 오른쪽 > 아래 > 왼쪽 > 위쪽  방향으로 계속 돌면서 숫자를 채우는 방식이다.
			// while문 안에서 숫자를 채워나가며 목표 숫자까지 채웠다면 (N*N 만큼) 반복문을 탈출한다.
			// 탐색하며 채우는 범위를 정한다. 정확히는 한계점을 정한다.
			// ai 시작 행 
			// aj 시작 열
			// bi 끝 행
			// bj 끝 열
			// 한 방향으로 채울 때마다 이 한계점이 이동한다.
			// 오른쪽 방향 채우고 > ai 시작 행  증가
			// 아래 방향 채우고 > bj 끝 열 감소
			// 왼쪽 방향 채우고 > bi 끝 행 감소
			// 위쪽 방향 채우고 > aj 시작 열 증가
			int ai = 0; int aj = 0; int bi = N-1; int bj=  N-1;
			int num = 0;
			
			while(true) {
				//오른쪽 직진 ; 시작 행 기준으로 채운다.
				for (int i = aj; i <= bj ; i++) data[ai][i] = ++num;
				ai++;
				if (num==N*N) break;
				
				//아래쪽 직진 ; 끝 열 기준으로 채운다.
				for (int i = ai; i <= bi ; i++) data[i][bj] = ++num;
				bj--;
				
				//왼쪽 직진 ; 끝 행 기준으로 채운다.
				for (int i = bj; i >= aj ; i--) data[bi][i] = ++num;
				bi--;
				if (num==N*N) break;
				
				//위쪽 직진 ; 시작 열 기준으로 채운다.
				for (int i = bi; i >= ai ; i--) data[i][aj] = ++num;
				aj++;
			}
			
			// 양식에 맞게 출력한다.
			System.out.println( "#" + t );
			for (int[] arr : data) {
				for (int n : arr) {
					System.out.print(n + " ");
				}
				System.out.println();
			}
			
		}
		
		
	}

}
