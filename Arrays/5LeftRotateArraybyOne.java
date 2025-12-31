// Given an integer array nums, rotate the array to the left by one.



// Note: There is no need to return anything, just modify the given array.


// Example 1

// Input: nums = [1, 2, 3, 4, 5]

// Output: [2, 3, 4, 5, 1]

// Explanation:

// Initially, nums = [1, 2, 3, 4, 5]

// Rotating once to left -> nums = [2, 3, 4, 5, 1]

public class 5LeftRotateArraybyOne {
    public void rotateArrayByOne(int[] nums) {
        int temp = nums[0];
        for(int i = 1 ; i < nums.length ; i++){
            nums[i-1]=nums[i];
        }
        nums[nums.length-1]= temp;
    }
}
