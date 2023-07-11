import java.util.Arrays;
class Solution {
    public boolean solution(String[] phone_book) {
        int len = phone_book.length;
        Arrays.sort(phone_book);
        for(int i = 0; i < len -1 ; i++) {
            String a = phone_book[i];
            int mylen = a.length();
            String b = phone_book[i+1];

            boolean yes = true;
            for (int k = 0; k < mylen ; k++){
                if(a.charAt(k) != b.charAt(k)) {
                    yes = false;
                    break;
                }
            }
            if(yes) return false;
            
        }
        return true;
    }
}