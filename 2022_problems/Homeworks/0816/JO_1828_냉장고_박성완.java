//package s0816.JO_1828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 정올 1828 냉장고
public class Main {
	// 전역변수 선언
	static int N,cnt;
	static List<Material> things;
	static List<Refrigerator> refrigerator;
	// 냉장고 클래스
	static class Refrigerator{
		int left, right;
		public Refrigerator(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}	
	}
	// 재료 클래스
	static class Material implements Comparable<Material>{
		int minimum, maximum, range;
		public Material(int minimum, int maximum) {
			super();
			this.minimum = minimum;
			this.maximum = maximum;
			this.range = maximum - minimum;
		}
		@Override
		public int compareTo(Material o) {
			return (this.maximum != o.maximum) ? o.maximum - this.maximum : o.range - this.range;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 갯수 입력받고 리스트 선언
		N = Integer.parseInt(br.readLine());
		things = new ArrayList<Material>();
		refrigerator = new ArrayList<Refrigerator>();
		// 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			things.add(new Material(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		// 재료를 최댓값 내림차순으로 정렬
		Collections.sort(things);
		// 첫 요소를 냉장고에 넣고
		refrigerator.add(new Refrigerator(things.get(0).minimum,things.get(0).maximum));
		cnt = 1;
		// 그 다음요소부터 탐색한다. 겹치는 부분이 있으면 해당 냉장고를 최신화하고,
		// 겹치는 범위가 없다면 새 냉장고를 추가한다.
		for (int i = 1; i < N; i++) {
			Material mat = things.get(i);
			int idx = -1;
			for (int j = 0; j < cnt; j++) {
				Refrigerator ref = refrigerator.get(j);
				
				if( !(ref.left > mat.maximum) && !(ref.right < mat.minimum) ) {
					ref.left = ref.left < mat.minimum ? mat.minimum: ref.left;
					ref.right = ref.right < mat.maximum ? ref.right : mat.maximum;
					idx = j;
					break;
				}
				
			}
			// 탐색결과 idx가 바뀌지 않았으면 냉장고를 추가
			if(idx == -1) {
				refrigerator.add(new Refrigerator(mat.minimum,mat.maximum));
				cnt++;
			}
		}
		// 냉장고의  갯수 출력
		System.out.println(cnt);
	}

}
