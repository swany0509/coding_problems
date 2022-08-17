//package S0803.bj_11659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 11659 구간 합 구하기 4
public class Main {
	// 배열과 배열의 크기 전역변수로 선언.
	public static int[] arr;
	public static int N,M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer in = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		// 크기 입력
		N = Integer.parseInt(in.nextToken());
		M = Integer.parseInt(in.nextToken());
		
		// 숫자를 맞추기 위해 하나 더 큰 값으로 초기화
		arr = new int[N+1];
		
		// 첫 수는 0
		arr[0] = 0;
		in = new StringTokenizer(br.readLine());
		// 하나씩 입력받고 누적 합을 저장시킨다.
		for (int i = 1; i < N+1; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(in.nextToken());
		}
		
		// 시작과 끝 인덱스를 입력받고 끝 인덱스 값 에서 시작 인덱스 전 항목 값을 빼서 출력한다.
		for (int i = 0; i < M; i++) {
			in = new StringTokenizer(br.readLine());
			int si = Integer.parseInt(in.nextToken());
			int ei = Integer.parseInt(in.nextToken());
			
			sb.append((arr[ei]-arr[si-1]) + "\n");
		}
		System.out.println(sb);
		br.close();
	}
}