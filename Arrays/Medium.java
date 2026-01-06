//QUE:Two Sum : Check if a pair with given sum exists in Array
//Problem Statement: Given an array of integers arr[] and an integer target.

//Brute Force Approach: O(n^2) time complexity
class Solution{
// Function to return indices of two numbers that sum to target (variant 2)
    public int[] twoSumIndices(int[] arr, int target) {
        int n = arr.length;
        // Outer loop picks one element at a time
        for (int i = 0; i < n; i++) {
            // Inner loop searches for another element that complements arr[i]
            for (int j = i + 1; j < n; j++) {
                // If sum equals target, return the pair of indices
                if (arr[i] + arr[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        // No such pair found
        return new int[]{-1, -1};
    }
}


//Better Approach: O(n) time complexity using HashMap

class Solution{
    // Variant 2: Return indices of two numbers that sum to target using hashing
    public int[] twoSumIndices(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            // If complement found, return indices
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            // Store current element and index
            map.put(arr[i], i);
        }
        // No pair found
        return new int[] { -1, -1 };
    }
}


//Optimal Approach: O(n log n) time complexity using Sorting and Two Pointers
class Solution{
    // Variant 2: Return indices of two numbers that sum to target
    public int[] twoSumIndices(int[] arr, int target) {
        int n = arr.length;
        int[][] numsWithIndex = new int[n][2];
        
        // Store element with original index
        for (int i = 0; i < n; i++) {
            numsWithIndex[i][0] = arr[i];
            numsWithIndex[i][1] = i;
        }
        
        // Sort by the value to apply two-pointer
        Arrays.sort(numsWithIndex, (a, b) -> Integer.compare(a[0], b[0]));

        int left = 0, right = n - 1;
        while (left < right) {
            int sum = numsWithIndex[left][0] + numsWithIndex[right][0];
            if (sum == target) {
                // Return original indices of the two numbers found
                return new int[] {numsWithIndex[left][1], numsWithIndex[right][1]};
            } else if (sum < target) {
                // Increase sum by moving left pointer forward
                left++;
            } else {
                // Decrease sum by moving right pointer backward
                right--;
            }
        }
        
        // No pair found
        return new int[] {-1, -1};
    }
}





//QUE:Sort an array of 0s, 1s and 2s
// Problem Statement: Given an array nums consisting of only 0, 1, or 2. Sort the array in non-decreasing order. The sorting must be done in-place, without making a copy of the original array.

//Brute force Approach
class Solution {
    // Function to sort the array containing only 0s, 1s and 2s
    public void sortZeroOneTwo(int[] nums) {
        // Initialize count variables for 0s, 1s, and 2s
        int count0 = 0, count1 = 0, count2 = 0;

        // Count the frequency of 0s, 1s, and 2s
        for(int num : nums) {
            if(num == 0) count0++;
            else if(num == 1) count1++;
            else count2++;
        }

        // Overwrite the array with sorted values
        int index = 0;

        // Fill with 0s
        while(count0-- > 0) {
            nums[index++] = 0;
        }

        // Fill with 1s
        while(count1-- > 0) {
            nums[index++] = 1;
        }

        // Fill with 2s
        while(count2-- > 0) {
            nums[index++] = 2;
        }
    }
}

//Better Approach: Counting Sort
class Solution {
    // Function to sort an array containing only 0s, 1s, and 2s
    public void sortZeroOneTwo(int[] nums) {
        // Count of 0s, 1s, and 2s
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;

        // First pass: Count the number of 0s, 1s, and 2s
        for (int num : nums) {
            if (num == 0) cnt0++;
            else if (num == 1) cnt1++;
            else cnt2++;
        }

        // Second pass: Fill the array with 0s, then 1s, then 2s

        // Fill the first 'cnt0' elements with 0
        for (int i = 0; i < cnt0; i++) {
            nums[i] = 0;
        }

        // Fill the next 'cnt1' elements with 1
        for (int i = cnt0; i < cnt0 + cnt1; i++) {
            nums[i] = 1;
        }

        // Fill the remaining elements with 2
        for (int i = cnt0 + cnt1; i < nums.length; i++) {
            nums[i] = 2;
        }
    }
}


//Optimal Approach: Dutch National Flag Algorithm

class Solution {
    // Function to sort array containing 0s, 1s, and 2s using Dutch National Flag Algorithm
    public void sortZeroOneTwo(int[] nums) {
        // Initialize three pointers: low and mid at 0, high at the end
        int low = 0, mid = 0, high = nums.length - 1;

        // Continue processing until mid crosses high
        while (mid <= high) {
            // If current element is 0, swap with low and move both low and mid forward
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            }
            // If current element is 1, just move mid forward
            else if (nums[mid] == 1) {
                mid++;
            }
            // If current element is 2, swap with high and move only high backward
            else {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }
}