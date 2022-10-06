
//package s1006.sw_3307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	// 전역변수 선언
	static int TC,N, nums[], LIS[],max;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			// 입력 및 초기화
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			LIS = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			max = 0;
			
			// LIS 알고리즘
			for (int i = 0; i < N; i++) {
				LIS[i] = 1;
				for (int j = 0; j < i; j++) {
					if( nums[j] <= nums[i] && LIS[i] < LIS[j] + 1) LIS[i] = LIS[j] + 1;
				}
				max = Math.max(max, LIS[i]);
			}
			
			
			sb.append("#" + t + " " + max + "\n");
			
		}
		System.out.println(sb);
		br.close();
	}
}
