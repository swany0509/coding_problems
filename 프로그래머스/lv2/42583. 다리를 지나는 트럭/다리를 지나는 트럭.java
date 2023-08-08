import java.util.*;
class Solution {
    static class Info{
        int w, t;
        public Info(int w, int t){
            this.w = w;
            this.t = t;
        }
    }
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Info> queue = new LinkedList<>();    
        int time = 1;
        int curweight = truck_weights[0];
        int goals = 0;
        int idx = 1;
        queue.offer(new Info(curweight,bridge_length));
        
        while (goals < truck_weights.length){
            if(queue.peek().t == time){
                curweight -= queue.peek().w;
                queue.poll();
                goals++;
            }
            
            if(idx < truck_weights.length && curweight + truck_weights[idx] <= weight){
                Info truck = new Info(truck_weights[idx],time+bridge_length);
                curweight += truck.w;
                queue.offer(truck);
                idx++;
            }
            time++;
        }
        return time;
    }
}