class Solution {
    public int maxDigitRange(int[] nums) {
        int maxRange = -1;
        int totalSum = 0;
        
        for (int num : nums) {
            int temp = num;
            int minDigit = 9;
            int maxDigit = 0;
            
            while (temp > 0) {
                int digit = temp % 10;
                if (digit < minDigit) minDigit = digit;
                if (digit > maxDigit) maxDigit = digit;
                temp /= 10;
            }
            
            int currentRange = maxDigit - minDigit;
            
            if (currentRange > maxRange) {
                maxRange = currentRange;
                totalSum = num; 
            } else if (currentRange == maxRange) {
                totalSum += num; 
            }
        }
        
        return totalSum;
    }
}