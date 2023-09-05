import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long X,cnt, arr[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());

        cnt = 0;
        arr = new long[N+1][2];
        makeArr();
//        for (int i = 1; i <= arr[N][0]; i++) {
//            X = i;
//            makeBurger(1,arr[N][0],N);
//            System.out.println(cnt);
//            cnt = 0;
//        }
        makeBurger(1,arr[N][0],N);

        System.out.println(cnt);
    }
    static public void makeArr(){
        arr[0][0] = 1;
        arr[0][1] = 1;
        for (int i = 1; i <= N; i++) {
            arr[i][0] = arr[i-1][0]*2 + 3;
            arr[i][1] = arr[i-1][1]*2 + 1;
        }
    }

//    static public void makeBurger(long first, long last, int round){
//        if(round == 1) return;
//
//        if (first <= X) cnt++;
//        if (last <= X) cnt++;
//
//        long mid = (first + last) / 2;
//
//        makeBurger(first + 1, mid - 1, round - 1);
//        if ( mid < X) makeBurger(mid + 1, last + 1, round - 1);
//    }
    static public void makeBurger(long first, long last, int round){
        long mid = (first + last) / 2;
//        System.out.println("first " + first + " last " + last + " mid " + mid + " X " + X);

        if ( X < first || X > last) return;
        if ( X == first ) {
            if (round == 0) cnt++;
        }
        else if( X < mid) {
            makeBurger(first + 1, mid - 1, round - 1);
            makeBurger(mid + 1, last - 1, round - 1);
        } else if (mid == X) {
            cnt++;
            cnt += arr[round-1][1];
        } else if ( X == last) {
            cnt += arr[round][1];
        } else {
            cnt += arr[round-1][1];
            cnt++;
            makeBurger(mid + 1, last - 1, round - 1);
        }

    }

}
