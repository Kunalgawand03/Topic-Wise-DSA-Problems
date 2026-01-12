//QUE: Find the missing number in an array

// Example 1:
// Input Format: N = 5, array[] = {1,2,4,5}
// Result: 3
// Explanation: In the given array, number 3 is missing. So, 3 is the answer.

//Brute Force

class Solution {
    // Function to find the missing number from 1 to N
    public int missingNumber(int[] a, int N) {
        // Check each number from 1 to N
        for (int i = 1; i <= N; i++) {
            boolean found = false;

            // Check if i exists in the array using linear search
            for (int j = 0; j < N - 1; j++) {
                if (a[j] == i) {
                    found = true;
                    break;
                }
            }

            // If not found, return it as the missing number
            if (!found) return i;
        }

        // This line should not be reached
        return -1;
    }
}


//Optimal

class Solution {
    // Function to find the missing number using sum formula
    public int missingNumber(int[] a, int N) {
        // Calculate the sum of first N natural numbers
        int sum = (N * (N + 1)) / 2;

        // Calculate the sum of elements in the array
        int actualSum = 0;
        for (int i = 0; i < N - 1; i++) {
            actualSum += a[i];
        }

        // Missing number is the difference
        return sum - actualSum;
    }
}













//QUE:Count Maximum Consecutive One's in the array
// Example 1:
// Input: prices = {1, 1, 0, 1, 1, 1}
// Output: 3
// Explanation: There are two consecutive 1’s and three consecutive 1’s in the array out of which maximum is 3.


class Solution {
    // Function to find maximum consecutive 1's in an array
    public int findMaxConsecutiveOnes(int[] nums) {
        // Variable to store current count of consecutive 1's
        int cnt = 0;
        // Variable to store maximum consecutive 1's
        int maxi = 0;

        // Traverse the array
        for (int i = 0; i < nums.length; i++) {
            // If current element is 1, increment count
            if (nums[i] == 1) {
                cnt++;
            } else {
                // If element is 0, reset count
                cnt = 0;
            }

            // Update maximum if current count is greater
            maxi = Math.max(maxi, cnt);
        }

        // Return maximum consecutive 1's
        return maxi;
    }
}










//QUE:Find the number that appears once, and the other numbers twice
// Problem Statement: Given a non-empty array of integers arr, 
// every element appears twice except for one. Find that single one.
// Example 1:
// Input Format: arr[] = {2,2,1}
// Result: 1
// Explanation: In this array, only the element 1 appear once and so it is the answer.

//Brute Force

class Solution {
    // Function to find the single element using brute force
    public int getSingleElement(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int num = arr[i];
            int count = 0;

            // Count how many times num occurs
            for (int j = 0; j < n; j++) {
                if (arr[j] == num)
                    count++;
            }

            // If only once, return it
            if (count == 1) return num;
        }

        return -1; // fallback, won't be hit if array has a single element
    }
}

//Better Approach
//***
class Solution {
    // Function to find the single element using a hash array
    public int getSingleElement(int[] arr) {
        int n = arr.length;

        // Step 1: Find maximum element
        int maxi = arr[0];
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, arr[i]);
        }

        // Step 2: Create frequency array of size maxi+1
        int[] hash = new int[maxi + 1];

        // Step 3: Count frequencies
        for (int i = 0; i < n; i++) {
            hash[arr[i]]++;
        }

        // Step 4: Find element with frequency = 1
        for (int i = 0; i < n; i++) {
            if (hash[arr[i]] == 1)
                return arr[i];
        }

        return -1; // fallback
    }
}

//OPtimal Approach

class Solution {
    // Function to find the element that appears once using XOR
    public int getSingleElement(int[] arr) {
        int xorr = 0;

        // XOR all elements — duplicates cancel each other out
        for (int num : arr) {
            xorr ^= num;
        }

        return xorr;
    }
}










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
