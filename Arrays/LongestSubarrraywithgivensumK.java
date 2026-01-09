//QUE:Longest Subarray with given Sum K(Positives)
// Problem Statement: Given an array nums of size n and an integer k, find the length of the longest sub-array that sums to k. If no such sub-array exists, return 0.

// Example 1:
// Input:
//  nums = [10, 5, 2, 7, 1, 9], k = 15  
// Output:
//  4  

// Brute Force
class Solution {
    // Function to find the length of the longest subarray with sum k
    public int lenOfLongSubarr(int[] arr, int n, int k) {
        int maxLength = 0;

        // Check all subarrays
        for (int i = 0; i < n; i++) {
            int currentSum = 0;

            for (int j = i; j < n; j++) {
                currentSum += arr[j];

                // If sum equals k, update maxLength
                if (currentSum == k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }
}


//Optimal Approach

class Solution {
    // Function to find the length of the longest subarray with sum k
    public int lenOfLongSubarr(int[] arr, int n, int k) {
        int maxLength = 0;
        int currentSum = 0;
        int left = 0;

        // Use sliding window technique
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];

            // Shrink window from the left if currentSum exceeds k
            while (currentSum > k && left <= right) {
                currentSum -= arr[left];
                left++;
            }

            // If currentSum equals k, update maxLength
            if (currentSum == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength;
    }
}
