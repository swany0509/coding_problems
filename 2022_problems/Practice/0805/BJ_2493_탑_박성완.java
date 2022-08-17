//package s0805.bj_2493;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

// 백준 2493 탑
public class Main {
	static int N;
	static Stack<int[]> tower;
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		tower = new Stack<>();
		
		int cnt = 1;
		tower.push(new int[] {sc.nextInt(),cnt});
		System.out.print(0 + " ");
		int current;
		
		while(true) {
			current = sc.nextInt();
			
			while(!tower.isEmpty() && tower.peek()[0] < current) {
				tower.pop();
			}
			
			if(tower.isEmpty()) System.out.print(0 + " ");
			else System.out.print(tower.peek()[1]+ " ");
			
			tower.push(new int[] {current,++cnt});
			if (cnt==N) break;
		}
		

	}
	
}
