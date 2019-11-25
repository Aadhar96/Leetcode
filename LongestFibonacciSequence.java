class Solution {
    
    class Pair {
        int left;
        int right;
        
        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
        
        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair that = (Pair) o;
            return this.left == that.left && this.right == that.right;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.left, this.right);
        }
        
    }
    
    public int lenLongestFibSubseq(int[] A) {
        Set<Integer> lookup = new HashSet<>();
        Map<Pair, Integer> dp = new HashMap<>();
        int finalAns = 0;
        for(int i = 0 ; i < A.length; i++) {
            lookup.add(A[i]);
            for (int j = 0; j < i; j++) {
                int prevElement = A[i] - A[j];
                if(prevElement < A[j] && lookup.contains(prevElement)) {
                    int prevBest = dp.getOrDefault(new Pair(prevElement, A[j]), 2);
                    dp.put(new Pair(A[j], A[i]), prevBest + 1);
                    finalAns = Math.max(finalAns, prevBest + 1);
                }
            }
        }
        return finalAns;
    }
}