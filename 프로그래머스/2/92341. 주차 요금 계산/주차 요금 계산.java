import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    static Map<String, Info> map;
    static int basicTime;
    static int basicFee;
    static int intervalTime;
    static int intervalFee;
    static final int MAX_TIME = 23*60+59;


    static class Info{
        int tot, intime;

        public Info(int intime) {
            this.tot = 0;
            this.carIn(intime);
        }

        public void carIn(int time){
            this.intime = time;
        }

        public void carOut(int time){
            tot += time-this.intime;
            this.intime = -1;
        }
    }
    public static int[] solution(int[] fees, String[] records) {
        init(fees);

        for (String raw: records) {
            String[] data = raw.split(" ");

            if (map.containsKey(data[1])) {
                switch(data[2]) {
                    case "IN":
                        map.get(data[1]).carIn(dateValue(data[0]));
                        break;
                    case "OUT":
                        map.get(data[1]).carOut(dateValue(data[0]));
                        break;
                    default:
                        break;
                }
            } else {
                map.put(data[1],new Info(dateValue(data[0])));
            }

        }

        Object[] mapKey = map.keySet().toArray();
        Arrays.sort(mapKey);

        int[] answer = new int[map.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = calcFee(map.get(mapKey[i]));
        }

        return answer;
    }

    public static int calcFee(Info info){
        int totTime = info.tot;

        if(info.intime != -1) {
            totTime += MAX_TIME-info.intime;
        }

        if (totTime <= basicTime) return basicFee;
        else return basicFee + (int)(Math.ceil((totTime-basicTime)/(double)intervalTime) * intervalFee);
    }

    public static void init(int[] fees){
        map = new HashMap<>();

        basicTime = fees[0];
        basicFee = fees[1];
        intervalTime = fees[2];
        intervalFee = fees[3];

    }

    public static int dateValue(String code) {
        String[] date = code.split(":");
        return Integer.parseInt(date[0])*60 + Integer.parseInt(date[1]);
    }

    public static void main(String[] args) {
        int[] ans = solution(new int[]{1, 461, 1, 10}, new String[]{"00:00 1234 IN"});

        for ( int n: ans ) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
