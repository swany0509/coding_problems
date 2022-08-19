//package s0819.d4_3234;
// 메모리 20316kb 시간 715ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//  SWEA 3234준환이의 양팔저울
public class Solution {
	// 전역변수 선언
	static int TC,N,data[],current[],ans , permCalc[], sums;
	static boolean isUsed[];
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		// N의 최대는 9이다. 경우의 수 리스트를 먼저 생성해준다.
		permCalc = new int[10];
		permCalc[0] = 1;
		for (int i = 1; i < 10; i++) permCalc[i] = 2* i * permCalc[i-1];
		// 테스트 케이스 입력
		TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			// 각종 변수 초기화
			N = Integer.parseInt(br.readLine());
			ans = 0; sums = 0;
			data = new int [N];
			current = new int [N];
			isUsed = new boolean[N];
			// 데이터 입력 및 합 저장
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				data[i] = Integer.parseInt(st.nextToken()); 
				sums += data[i];
			}
			// 순열 시작
			perm(0,0,0);
			
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
		br.close();
		
	}
	// 순열 + 백트래킹 알고리즘
	static void perm(int cnt, int left, int right) {
		// 만약 현재 위치에서 남은 합계가 왼쪽에 있는 무게보다 같거나 적다면, 나머지의 모든 경우의 수도 참이다.
		// 이를 미리 구한 경우의 수 배열에서 구해 더한다.
		if(left >= sums-left) {
			ans += permCalc[N-cnt];
			return;
		}
		// 갯수가 N개이면 조건을 확인하고 더한다.
		if(cnt==N) {
			if(left >= right) ans++;
			return;
		}
		// 순얼
		for (int i = 0; i < N; i++) {
			if(isUsed[i]) continue;
			int num = data[i];
			current[cnt] = num;
			// 왼쪽에 이번 수롤 더했을 때 조건을 만족할 경우에만 다음 조건으로 넘긴다.
			if(left + num >= right){
				isUsed[i] = true;
				perm(cnt+1, left+num , right);
				isUsed[i] = false;
			}
			// 오른쪽에 이번 수롤 더했을 때 조건을 만족할 경우에만 다음 조건으로 넘긴다.
			if(left >= right + num){
				isUsed[i] = true;
				perm(cnt+1, left , right+num);
				isUsed[i] = false;
			}			
		}
	}
}
