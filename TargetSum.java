class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length + 1][2001];
        for(int[] curr : dp) {
            Arrays.fill(curr, -1);
        }
        return solve(nums, 0, 0, S, dp);
    }
    
    private int solve(int[] nums, int numIncluded, int currSum, int target, int[][] dp) {
        if(dp[numIncluded][1000 + currSum] != -1) {
            return dp[numIncluded][1000 + currSum];
        }
        
        if(numIncluded == nums.length) {
            if(currSum == target) {
                return 1;
            }
            return 0;
        }
        
        dp[numIncluded][1000 + currSum] = solve(nums, numIncluded + 1, currSum + nums[numIncluded], target, dp) + solve(nums, numIncluded + 1, currSum - nums[numIncluded], target, dp);
        return dp[numIncluded][1000 + currSum];
    }
}