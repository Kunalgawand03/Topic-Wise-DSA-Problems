class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int idx = -1;

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                idx = i;
                break;
            }
        }

        if(idx == -1){
            reverse(nums, 0, nums.length-1);
            return;
        }

        for(int i = nums.length-1; i > idx ; i--){
            if(nums[idx]< nums[i]){
                swap(nums, i , idx);
                break;
            }
        }

        reverse(nums, idx+1, nums.length-1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
 
            left++;
            right--;
        }
    }
}