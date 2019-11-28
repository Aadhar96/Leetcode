class Solution {
    
    public int maxEnvelopes(int[][] envelopes) {
        if(Objects.isNull(envelopes)) {
            return 0;
        }
        int N = envelopes.length;
        
        if(N < 2) {
            return N;
        }
        
        Arrays.sort(envelopes, (envelope1, envelope2) -> {
            if(envelope1[0] != envelope2[0]) {
                return Integer.compare(envelope1[0], envelope2[0]);
            }
            return Integer.compare(envelope2[1], envelope1[1]);
        });
        
        return findLongestIncreasingSubsequence(envelopes, N);
    }
    
    private int leftBisect(int[] arr, int start, int end, int val) {
        while(start < end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] < val) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    
    private int findLongestIncreasingSubsequence(int[][] envelopes, int numOfEnvelopes) {
        int[] maxTailHeight = new int[numOfEnvelopes];
        maxTailHeight[0] = envelopes[0][1];
        int currMaxIndex = 0;
        int currPos;
        for(int envelope = 1; envelope < numOfEnvelopes; envelope++) {
            if(envelopes[envelope][1] <= maxTailHeight[0]) {
                currPos = 0;
            } else if(envelopes[envelope][1] > maxTailHeight[currMaxIndex]) {
                currPos = ++currMaxIndex;
            } else {
                currPos = leftBisect(maxTailHeight, 0, currMaxIndex, envelopes[envelope][1]);
            }
            maxTailHeight[currPos] = envelopes[envelope][1];
        }
        return currMaxIndex + 1;
    }
    
}