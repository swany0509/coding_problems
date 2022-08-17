package S0809.bj_2563;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BJ 2563 색종이
public class Main {
	static int N,cnt;
	static boolean[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		cnt = 0;
		map = new boolean[101][101];
		
		for (int t = 0; t < N; t++) {
			st = new StringTokenizer(br.readLine());
			pack(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(cnt);
	}
	static void pack(int i, int j) {
		for (int ii = i; ii < i+10; ii++) {
			for (int jj = j; jj < j+10; jj++) {
				if(!map[ii][jj]) {
					map[ii][jj] = true;
					cnt++;
				}
			}
		}
	}
}
