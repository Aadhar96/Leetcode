class Solution {
    private static int MOD = 1000000007;
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int[][] dp = new int[G+1][P+1];
        dp[0][0] = 1;
        int totalSchemes = 0;
        for(int crime = 0; crime < group.length; crime++) {
            int numberOfMembers = group[crime];
            int profitFromCrime = profit[crime];
            for(int alreadyParticipated = G - numberOfMembers; alreadyParticipated >= 0; alreadyParticipated--) {
                for(int profitEarnedTillNow = P; profitEarnedTillNow >= 0; profitEarnedTillNow--) {
                    int newProfit = Math.min(profitEarnedTillNow + profitFromCrime, P);
                    int newMemberCount = alreadyParticipated + numberOfMembers;
                    dp[newMemberCount][newProfit] = (dp[newMemberCount][newProfit] + dp[alreadyParticipated][profitEarnedTillNow])%MOD;
                    if(newProfit == P) {
                        totalSchemes += dp[alreadyParticipated][profitEarnedTillNow];
                        totalSchemes %= MOD;
                    }
                }
            }
        }
        return totalSchemes;
    }
}