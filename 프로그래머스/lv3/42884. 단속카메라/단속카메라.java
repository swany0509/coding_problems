import java.util.*;
class Solution {
    int cameraState = -30001;
    static Route[] list;
    static class Route implements Comparable<Route>{
        int start, end;
        
        public Route(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Route o){
            return this.end - o.end;
        }
    }
    public int solution(int[][] routes) {
        int answer = 0;
        
        list = new Route[routes.length];
        for(int i = 0, size = routes.length; i < size ; i++){
            list[i] = new Route(routes[i][0], routes[i][1]);
        }
        Arrays.sort(list);
        for(Route r : list){
            if(r.start > cameraState) {
                cameraState = r.end;
                answer++;
            }
        }
        
        return answer;
    }
}