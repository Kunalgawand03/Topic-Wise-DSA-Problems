class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int Idx = 0;
        int Idx2 = n-1;

        for(int i = 0; i < n ; i++){
            if(nums[i] % 2 == 0){
                res[Idx] = nums[i];
                Idx++;
            }
            else{
                res[Idx2] = nums[i];
                Idx2--;
            }
        }
        return res;
    }
}