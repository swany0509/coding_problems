package s0808.bj_1158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 백준 1158 요세푸스 문제
public class Main {
	// 전역변수 선언
	static int N,K,size,index;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// N,K 입력
		// size = 현재 리스트 크기, index = 현재 참조중인 위치
		N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
		size = N; index = 0;
		// 리스트 생성 및 초기화
		List<Integer> list = new ArrayList<>();
		for(int i=1; i<= N;i++) list.add(i);
		
		// 형식을 맞추기 위해 괄호 넣기
		sb.append('<');
		while(true) {
			// 인덱스 이동
			index = (index+(K-1))%size;
			// 요소 빼서 추가
			sb.append(list.remove(index)+", ");
			// 사이즈 감소
			size--;
			// 비었으면 탈출
			if(size==0) break;
		}
		
		// 맨 끝요소를 넣었을 때 생긴 ", " 제거
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		// 형식에 맞게 출력
		sb.append('>');
		System.out.println(sb);
	}


}
