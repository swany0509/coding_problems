class Solution {
    static char[] a124 = {'1','2','4'};
    static int remain = 0;
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        while(true){
            n--;
            remain = n % 3;
            sb.insert(0,a124[remain]);

            n /= 3;
            if(n <= 0) break;
        }
        return sb.toString();
    }
}