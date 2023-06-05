import java.util.*;
class Solution {
    static int N;
    static Map<String,Integer> indexMap;
    static int[] wantedState;
    static int[] curState;
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        N = want.length;
        wantedState = new int[N];
        curState = new int[N];
        indexMap = new HashMap<>();
        
        for(int i = 0; i < N ; i++){
            wantedState[i] = number[i];
            indexMap.put(want[i],i);
        }
        
        for(int i = 0, size = discount.length; i < size ; i++){
            String curFruit = discount[i];
            if (indexMap.get(curFruit) != null) curState[indexMap.get(curFruit)]++;
            if(i > 9) {
                String beforeFruit = discount[i-10];
                if(indexMap.get(beforeFruit) != null) curState[indexMap.get(beforeFruit)]--;
            }
            if(i >= 9 && isMatch()) answer++;
        }
        return answer;
    }
    public static boolean isMatch(){
        for(int i = 0; i < N ; i++) {
            if(wantedState[i] != curState[i]) return false; 
        }
        return true;
    }
}