
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String command;
    static int T, d, xa,xb,ya,yb, cx,cy;

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            command = br.readLine();
            d = 0; cx = 0; cy = 0; xa = 0; xb = 0; ya = 0; yb = 0;

            for (int j = 0, size = command.length(); j < size; j++) {
                switch(command.charAt(j)){
                    case 'F':
                        cx += dx[d];
                        cy += dy[d];

                        xa = Math.min(xa,cx);
                        xb = Math.max(xb,cx);
                        ya = Math.min(ya,cy);
                        yb = Math.max(yb,cy);
                        break;
                    case 'B':
                        cx += dx[(d + 2)%4];
                        cy += dy[(d + 2)%4];

                        xa = Math.min(xa,cx);
                        xb = Math.max(xb,cx);
                        ya = Math.min(ya,cy);
                        yb = Math.max(yb,cy);
                        break;
                    case 'L':
                        d = (d + 4 - 1) % 4;
                        break;
                    case 'R':
                        d = (d + 1) % 4;
                        break;
                    default:
                        System.out.println("error");
                        break;
                }

            }

            sb.append( (xb-xa)*(yb-ya) ).append('\n');
        }

        System.out.println(sb.toString());
        br.close();
    }
}
