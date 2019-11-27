class Solution {
    
    private static int MOD = 1000000007;
    public int numWays(int steps, int arrLen) {
        int[][] dp = new int[steps + 1][steps + 1];
        dp[0][0] = 1;
        for(int stepsAllowed = 1; stepsAllowed <= steps; stepsAllowed++) {
            for(int currPos = 0; currPos <= Math.min(stepsAllowed, arrLen - 1) ; currPos++) {
                if(currPos > stepsAllowed) {
                    dp[currPos][stepsAllowed] = 0;
                } else if(currPos == stepsAllowed) {
                    dp[currPos][stepsAllowed] = 1;
                } else {
                    dp[currPos][stepsAllowed] = dp[currPos][stepsAllowed - 1];
                    
                    if(currPos > 0) {
                        dp[currPos][stepsAllowed] = (dp[currPos][stepsAllowed] + dp[currPos - 1][stepsAllowed - 1])%MOD ;
                    }
                    
                    if(currPos < dp.length - 1) {
                        dp[currPos][stepsAllowed] = (dp[currPos][stepsAllowed] + dp[currPos + 1][stepsAllowed - 1])%MOD ;
                    }
                }
            }
        }
        return dp[0][steps];
    }
}