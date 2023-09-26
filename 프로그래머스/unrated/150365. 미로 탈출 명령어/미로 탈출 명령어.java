class Solution {
    static String least = "zzzzz";
    static int N,M,ex,ey,K;
    static int dx[] = {1,0,0,-1};
    static int dy[] = {0,-1,1,0};
    static boolean flag = false;
    static char direct[] = {'d','l','r','u'};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n-1; M = m-1; ex = r-1; ey = c-1; K = k;
        int manhatten = Math.abs(r-x) + Math.abs(c-y);
        if(manhatten%2 != k%2) return "impossible";
        perm(x-1,y-1,k, "");
        if (least.equals("zzzzz")) return "impossible";
        return least;
    }
    public static void perm(int x, int y, int k, String path) {
        if (k < 0 ) return;
        if (k == 0 && x == ex && y == ey) {
            if (least.compareTo(path) > 0) {
                least = path;
                flag = true;
            }
            return;
        }
        if ( flag || (Math.abs(ex-x) + Math.abs(ey-y)) > k) return;
        
        for(int i = 0; i < 4 ; i++){
            int xx = x + dx[i];
            int yy = y + dy[i];
            
            if(isRange(xx,yy)) {
                perm(xx,yy,k-1,path+direct[i]);
            }
        }       
    }
    public static boolean isRange(int x, int y){
        return x >= 0 && x <= N && y >= 0 && y <= M;
    }
}