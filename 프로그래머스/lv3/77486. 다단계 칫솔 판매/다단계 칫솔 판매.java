import java.util.*;
class Solution {
    static Map<String, Node> map;
    static class Node {
        String name;
        int point;
        Node parent;
        public Node(String name, Node parent){
            this.name = name;
            this.point = 0;
            this.parent = parent;
        }
    }
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        map = new HashMap<>();
        Node center = new Node("-", null);
        map.put("-", center);
        
        for(int i = 0, size = enroll.length; i < size ; i++) {
            Node parent = map.get(referral[i]);
            Node current = new Node(enroll[i], parent);
            map.put(enroll[i], current);
        }
        
        for(int i = 0, size = seller.length; i < size ; i++) {
            getPoints(map.get(seller[i]), amount[i]*100);
        }
        
        int[] answer = new int[enroll.length];
        
        for(int i = 0, size = enroll.length; i < size ; i++) {
            answer[i] = map.get(enroll[i]).point;
        }
        
        return answer;
    }
    public static void getPoints(Node node, int point){
        if (point * 0.1 < 1 || node.parent == null) {
            node.point += point;
            return;
        }
        int uppers = (int)(Math.floor(point * 0.1));
        node.point += point - uppers;
        getPoints(node.parent, uppers );
        
    }
}