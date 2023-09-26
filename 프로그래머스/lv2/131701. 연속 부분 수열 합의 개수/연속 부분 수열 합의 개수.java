import java.util.*;

class Solution {
    static int[] arr, sums;
    static int N;
    static Set<Integer> sets;
    public void init(int[] elements) {
        arr = elements;
        N = arr.length;
        sums = new int[N*2];
        
        sums[0] = arr[0];
        for(int i = 1 ; i < N ; i++){
            sums[i] = sums[i-1] + arr[i];
        }
        for(int i = 0 ; i < N ; i++){
            sums[N+i] = sums[N+i-1] + arr[i];
        }
        
        sets = new HashSet<>();
    }
    public void calc() {
        for(int i = 1 ; i <= N ; i++){
            // System.out.println(i);
            sets.add(sums[i-1]);
            // System.out.print(sums[i-1] + " ");
            for(int j = i; j < i+N; j++){
                int cur = sums[j] - sums[j-i];
                // System.out.print(cur + " ");
                sets.add(cur);
            }
            // System.out.println();
        }
    }
    public int solution(int[] elements) {
        init(elements);
        calc();
        return sets.size();
    }
}