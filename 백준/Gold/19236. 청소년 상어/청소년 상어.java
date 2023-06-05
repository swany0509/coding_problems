import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int maxValue = 0;
    static int dx[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int dy[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    static class Info {
        String code;
        int value, sharkIndex;

        public Info(String code, int value, int sharkIndex) {
            this.code = code;
            this.value = value;
            this.sharkIndex = sharkIndex;
        }

        void switchSeat(int ra, int rb, boolean isprev, int newDirect) {
            // ra < rb 인 것으로 생각, 0~31인 것으로 생각
            String before = code.substring(0, ra);
            String a = isprev ? code.substring(ra, ra + 1) + newDirect : code.substring(ra, ra + 2);
            String middle = code.substring(ra + 2, rb);
            String b = isprev ? code.substring(rb, rb + 2) : code.substring(rb, rb + 1) + newDirect;
            String next = code.substring(rb + 2);

            this.code = before + b + middle + a + next;
        }

        String sharkMovedCode(int ra, int rb, boolean isprev) {
            // ra < rb 인 것으로 생각, 0~31인 것으로 생각
            String before = code.substring(0, ra);
            String a = isprev ? "a" : "r";
            String middle = code.substring(ra + 1, rb);
            String b = isprev ? "r" : "a";
            String next = code.substring(rb + 1);

            return before + b + middle + a + next;
        }

        boolean canGo(int idx) {
            // idx0~31
            return code.charAt(idx) != 'a';
        }

        void moveFishes() {
            for (char c = 'b'; c <= 'q'; c++) {
                for (int k = 0; k < 32; k += 2) {
                    if (code.charAt(k) == c) {
                        rotateAndSwitchFish(k);
                        break;
                    }
                }
            }
        }

        void rotateAndSwitchFish(int idx) {
//            char cur = code.charAt(idx);
            int initDirect = code.charAt(idx + 1) - '0';
            int d = initDirect;
            int x = (idx / 2) / 4;
            int y = (idx / 2) % 4;

            while (true) {
                int xx = x + dx[d];
                int yy = y + dy[d];

                if (!isRange(xx, yy) || !canGo((xx * 4 + yy) * 2)) {
                    d++;
                    if (d == 9) d = 1;
                    if (d == initDirect) break;
                } else {
                    int targetIdx = (xx * 4 + yy) * 2;
                    if (targetIdx < idx) {
                        switchSeat(targetIdx, idx, false, d);
                    } else {
                        switchSeat(idx, targetIdx, true, d);
                    }
                    break;
                }
            }
        }

        List<Info> getAvailableSharkMoves() {
            List<Info> infoList = new ArrayList<>();
            int d = code.charAt(sharkIndex + 1) - '0';
            int x = (sharkIndex / 2) / 4;
            int y = (sharkIndex / 2) % 4;

            while (true) {
                x += dx[d];
                y += dy[d];
                if (!isRange(x, y)) break;
                int targetIdx = (x * 4 + y) * 2;
                if (code.charAt(targetIdx) == 'r') continue;

                String nextCode = "";
                if (targetIdx < sharkIndex) {
                    nextCode = sharkMovedCode(targetIdx, sharkIndex, false);
                } else {
                    nextCode = sharkMovedCode(sharkIndex, targetIdx, true);
                }

                infoList.add(new Info(nextCode, value + (code.charAt(targetIdx) - 'a'), targetIdx));

            }

            return infoList;
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }

    public static String arrayToCode(int[][][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append((char) (arr[i][j][0] + 'a')).append(arr[i][j][1]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][][] initState = new int[4][4][2];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                initState[i][j][0] = Integer.parseInt(st.nextToken());
                initState[i][j][1] = Integer.parseInt(st.nextToken());
            }
        }
        int firstAte = initState[0][0][0];
        initState[0][0][0] = 0;
        bfs(new Info(arrayToCode(initState), firstAte, 0));
        System.out.println(maxValue);
        br.close();
    }


    public static void bfs(Info initInfo) {
        initInfo.moveFishes();
        Queue<Info> queue = new LinkedList<>();
        queue.offer(initInfo);

        while (!queue.isEmpty()) {
            Info cur = queue.poll();
            maxValue = Math.max(maxValue, cur.value);

            List<Info> curList = cur.getAvailableSharkMoves();
            if (curList.isEmpty()) continue;

            for (Info info : curList) {
                info.moveFishes();
                queue.offer(info);
            }
        }
    }
}