import java.util.*;

class Solution {
    static int len;
    public int solution(int n, int k, int[] enemy) {
        len = enemy.length;
        int answer = k;
        int mine = n;
        int least = 0;
        
        if (k >= len) return len;
        
        PriorityQueue<Integer> pqueue = new PriorityQueue<>();
        for(int i = 0; i < k ; i++){
            pqueue.offer(enemy[i]);
        }
        
        for(int i = k; i < len ; i++){
            pqueue.offer(enemy[i]);
            least = pqueue.poll();
            
            if(mine >= least) {
                answer++;
                mine -= least;
            } else break;
        }
        return answer;
    }
}