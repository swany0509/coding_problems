import java.util.Arrays;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        boolean aa = false;
        boolean bb = false;
        
        int lcmA = getLCM(arrayA);
        int lcmB = getLCM(arrayB);
        
        for(int i = 0; i < arrayA.length ; i++){
            if(arrayA[i]%lcmB == 0) {
                bb = true;
                break;
            }
        }
        
        for(int i = 0; i < arrayB.length ; i++){
            if(arrayB[i]%lcmA == 0) {
                aa = true;
                break;
            }
        }
        
        System.out.println(lcmA);
        System.out.println(lcmB);
        
        if (aa && bb) return 0;
        else if (aa) return lcmB;
        else if (bb) return lcmA;
        else return (int) Math.max(lcmA, lcmB);
    }
    public int getLCM(int[] array){
        int res = array[0];
        if(array.length == 1) return array[0];
        else {
            for(int i = 0; i < array.length-1 ; i++){
                res = lcm(res,array[i+1]);
            }
        }
        return res;
    }
    public int lcm (int a, int b){
        int tmp = 0;
        if(b > a) {
            tmp = a;
            a = b;
            b = tmp;
        }
        
        int mod = a%b;
        
        while(true){
            if(mod==0) return b;
            else {
                a = b;
                b = mod;
                mod = a%b;
            }
        }
    }
}