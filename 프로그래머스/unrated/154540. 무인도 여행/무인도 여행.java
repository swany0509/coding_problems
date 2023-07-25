import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.awt.Point;

class Solution {
    static int[][] smap;
    static int N,M;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {-1,1,0,0};
    public int[] solution(String[] maps) {
        PriorityQueue<Integer> pqueue = new PriorityQueue<>();
        N = maps.length;
        M = maps[0].length();
        smap = new int[N][M];
        for(int i = 0; i < N ; i++ ) {
            for(int j = 0; j < M ; j++ ) {
                if(maps[i].charAt(j) == 'X') smap[i][j] = 0;
                else smap[i][j] = maps[i].charAt(j) - '0';
            }
        }
        
        for(int i = 0; i < N ; i++ ) {
            for(int j = 0; j < M ; j++ ) {
                if(smap[i][j] != 0) pqueue.offer(bfs(i,j));
            }
        }
        
        if(pqueue.size() == 0) return new int[]{-1};
        
        
        int[] answer = new int[pqueue.size()];
        for(int i = 0, size = pqueue.size(); i < size ; i++){
            answer[i] = pqueue.poll();
        }
        return answer;
    }
    public static int bfs(int sx, int sy) {
        int result = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sx,sy));
        result += smap[sx][sy];
        smap[sx][sy] = 0;
        
        while(!queue.isEmpty()){
            
            Point cur = queue.poll();
        
            for(int k = 0; k < 4 ; k++ ){
                int ii = cur.x + dx[k];
                int jj = cur.y + dy[k];
                
                if(isRange(ii,jj) && smap[ii][jj] != 0){
                    result += smap[ii][jj];
                    smap[ii][jj] = 0;
                    queue.offer(new Point(ii,jj));
                }
            }
        }
        return result;
    }
    
    public static boolean isRange(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}