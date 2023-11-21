
class Solution {
    public static int solution(int n, int k) {
        String raw = Integer.toString(n, k);
        String[] data = raw.split("0");
        int count = 0;

        if (data.length == 0) return 0;
        for ( String s:data ) {
            if (s.equals("")) continue;
            if (isPrime(Long.parseLong(s))) count++;
        }

        return count;
    }

    public static boolean isPrime(Long number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

}