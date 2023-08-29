import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String target;
    static char[] vowels = {'a','e','i','o','u'};
    static boolean doesHaveVowel = false;
    static int groupCount = 0;
    static int len;

    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while(true){
            target = br.readLine().trim();
            if(target.equals("end")) break;

            // flag 초기화
            doesHaveVowel = false;
            groupCount = 0;
            len = target.length();

            validate(target);

        }

        System.out.println(sb);
        br.close();
    }

    static void validate(String t) {
        for (int i = 0; i < len; i++) {
            char cur = t.charAt(i);

            //모음 계산
            if(isVowel(cur)) doesHaveVowel = true;


            if(i == 0) continue;

            //같은글자 연속 판정
            if(cur != 'e' && cur != 'o' && cur == t.charAt(i-1)) {
                getRes(t,false);
                return;
            }

            //3연속 판별
            if(i == 1) continue;
            if(isVowel(cur)) {
                if(isVowel(t.charAt(i-1)) && isVowel(t.charAt(i-2))) {
                    getRes(t,false);
                    return;
                }
            } else {
                if(!isVowel(t.charAt(i-1)) && !isVowel(t.charAt(i-2))) {
                    getRes(t,false);
                    return;
                }
            }
        }

        // 모음이 없으면 불인정
        if(!doesHaveVowel) getRes(t,false);
        else getRes(t,true);
    }

    static boolean isVowel(char c){
        for (int i = 0; i < 5; i++) {
            if(c == vowels[i]) return true;
        }
        return false;
    }

    static void getRes(String s, boolean b) {
        if(b) sb.append("<").append(s).append("> is acceptable.\n");
        else sb.append("<").append(s).append("> is not acceptable.\n");
    }
}
