//Move all Zeros to the end of the array
//Problem Statement: You are given an array of integers, 
// your task is to move all the zeros in the array to the 
// end of the array and move non-negative integers to the front by maintaining their order.


//Brute Force
public class 6MoveallZerostotheendofthearray {
    public int[] moveZeros(int[] arr){
        int[] temp = new int[arr.length];
        idx=0;

        for(i=0; i<=arr.length ; i++){
            if(arr[i] != 0){
                temp[idx]= arr[i];
                idx++;
            }
        }

        for(i = 0 ; i<=arr.length ; i++){
            arr[i] = temp[idx];
        }
        return arr;
    }
}


class Solution {
    // Function to move zeroes to the end
    public void moveZeroes(int[] nums) {
        // Pointer to the first zero
        int j = -1;

        // Find the first zero
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                j = i;
                break;
            }
        }

        // If no zero found, return
        if (j == -1) return;

        // Start from the next index of first zero
        for (int i = j + 1; i < nums.length; i++) {
            // If current element is non-zero
            if (nums[i] != 0) {
                // Swap with nums[j]
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                // Move j to next zero
                j++;
            }
        }
    }
}









