class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);        
    }

    private int atMostKDistinct(int[] nums, int k){
        if(k<=0){
            return 0;
        }
        
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int cnt = 0;

        for(int right = 0 ; right < nums.length; right++){
            int num = nums[right];

            freq.put(num, freq.getOrDefault(num,0)+1);

            while(freq.size() > k){
                int leftNum = nums[left];

                freq.put(leftNum, freq.get(leftNum)-1);

                if (freq.get(leftNum) == 0){
                    freq.remove(leftNum);
                }
                left++;
            }
            cnt += right - left+1;
        }
        return cnt;
    }
}