// Given an array nums of n integers, return true if the array nums is sorted in non-decreasing order or else false.


// Example 1

// Input : nums = [1, 2, 3, 4, 5]

// Output : true

// Explanation : For all i (1 <= i <= 4) it holds nums[i] <= nums[i+1], hence it is sorted and we return true.

public class 3CheckiftheArrayisSorted {
    public boolean check(int[] nums) {
        for(int i=1 ; i<=nums.length ; i++){
            if(nums[i] > nums[i-1]){
                return true;
            }
            else{
                return false;
            }
        }
        return true;
    }
}
