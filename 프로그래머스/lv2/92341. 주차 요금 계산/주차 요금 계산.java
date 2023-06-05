import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

//프로그래머스 92341 주차 요금 계산
public class Solution {
	static int N,cars;
	static final int MAXT = 24*60-1;
	static StringTokenizer st;
	static Record statuss[];
	static class Record{
		int st,ct, fee;

		public Record(int st, int ct, int fee) {
			super();
			this.st = st;
			this.ct = ct;
			this.fee = fee;
		}

			
	}
	public static int getTimeValue(String raw) {
		int hour = Integer.parseInt(raw.substring(0, 2));
		int min = Integer.parseInt(raw.substring(3));

		return hour*60 + min;
	}
	public static int[] solution(int[] fees, String[] records) {
        N = records.length;
		statuss = new Record[10000];
        //list = new ArrayList<Record>();
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(records[i]);
			int time = getTimeValue(st.nextToken());
			int carN = Integer.parseInt(st.nextToken());
			
			switch(st.nextToken()) {
			case "IN":
				if(statuss[carN] != null) {
					Record cur = statuss[carN];
					statuss[carN] = new Record(time,cur.ct,0);
				}else {
					cars++;
					statuss[carN] = new Record(time,0,0);
				}
				
				break;
			case "OUT":
				Record cur = statuss[carN];
				statuss[carN] = new Record(-1,cur.ct + (time-cur.st),0);
				break;
			default:
				System.out.println("error");
				break;
			}
		}
        
        int answer[] = new int[cars];
        int index = 0;
        for (int i = 0 ; i < 10000; i++) {
        	Record cur = statuss[i];
        	if(cur == null) continue;
        	int used = cur.ct;
        	int fee = 0;
        	if(cur.st != -1) {
        		used += (MAXT - cur.st);
        	}
        	
        	if(used <= fees[0]) {
				fee = fees[1];
			}else {
				fee += fees[1];
				used -= fees[0];
				
				fee += Math.ceil((double)used/fees[2])*fees[3];
			}
        	answer[index++] = fee;
        }
        return answer;
    }
}
