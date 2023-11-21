class Solution
{
    static int maxSize = 0;
    static int[][] dp;
    static int N,M;
    public static int solution(int[][] board)
    {
        N = board.length+1;
        M = board[0].length+1;
        dp = new int[N][M];
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (board[i-1][j-1] == 1){
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                    maxSize = Math.max(maxSize,dp[i][j]);
                }
            }
        }

        return maxSize*maxSize;
    }
}