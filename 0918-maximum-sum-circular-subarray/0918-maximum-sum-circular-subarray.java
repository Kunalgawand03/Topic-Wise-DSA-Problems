class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totSum =0;
        
        int maxSum = Integer.MIN_VALUE;
        int currMax = 0;
        int minSum = Integer.MAX_VALUE;
        int currMin = 0;

        for(int i = 0; i < nums.length; i++){
            totSum += nums[i];
            
            currMax += nums[i];
            maxSum = Math.max(currMax, maxSum);
            if(currMax < 0){
                currMax = 0;
            }

            currMin += nums[i];
            minSum = Math.min(currMin, minSum);
            if(currMin >0){
                currMin = 0;
            }
        }
        if(maxSum < 0){
            return maxSum;
        }
        return Math.max(maxSum,totSum- minSum);
    }
}