import java.util.*;
class Solution {
    static int t;
    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};
    static boolean[][] visited;
    static class Info {
        int x,y,dist;
        public Info(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public int[] solution(String[][] places) {
        t = places.length;
        int[] answer = new int[t];
        
        for(int i = 0; i < t; i++){
            answer[i] = testcase(places[i]);
        }
        return answer;
    }
    public int testcase(String[] map) {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(map[i].charAt(j) == 'P') 
                    if(!bfs(i,j,map)) return 0;
            }
        }
        return 1;
    }
    
    public boolean bfs(int x, int y, String[] map){
        visited = new boolean[5][5];
        visited[x][y] = true;
        Queue<Info> queue = new LinkedList<>();
        queue.offer(new Info(x,y,0));
        while(!queue.isEmpty()){
            Info cur = queue.poll();
            
            if(cur.dist > 0 && cur.dist < 3 && map[cur.x].charAt(cur.y) == 'P') {
                return false;
            }
            if(cur.dist == 3) continue;
            
            for(int k = 0; k < 4; k ++){
                int xx = cur.x + dx[k];
                int yy = cur.y + dy[k];
                
                if(!isRange(xx,yy) || map[xx].charAt(yy) == 'X'|| visited[xx][yy] ) continue;
                
                queue.offer(new Info(xx,yy,cur.dist + 1));
                visited[xx][yy] = true;
                
            }
        }
        return true;
    }
    
    public boolean isRange(int x, int y){
        return x >= 0 && x < 5 && y >= 0 && y < 5 ;
    }
}