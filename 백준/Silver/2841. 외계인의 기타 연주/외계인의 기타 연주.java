
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N,P, line, pret, count, temp;
    static Deque<Integer>[] deques;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        deques = new Deque[N];
        count = 0;

        for (int i = 0; i < N; i++) {
            deques[i] = new LinkedList<>();
        }

        for (int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            line = Integer.parseInt(st.nextToken())-1;
            pret = Integer.parseInt(st.nextToken());

//            System.out.println(line + " " + pret);

            while(true) {
                if (deques[line].isEmpty()){
//                    System.out.println("empty");
                    deques[line].offerLast(pret);
                    count++;
                    break;
                }

                temp = deques[line].peekLast();

                if(temp > pret) {
//                    System.out.println("finger out");
                    deques[line].pollLast();
                    count++;
                } else if (temp == pret) {
//                    System.out.println("same");
//                    count++;
                    break;
                } else {
                    deques[line].offerLast(pret);
//                    System.out.println("can swing");
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
        br.close();
    }
}
