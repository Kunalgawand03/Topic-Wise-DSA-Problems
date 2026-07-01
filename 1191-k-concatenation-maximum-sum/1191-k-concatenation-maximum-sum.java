class Solution {

    public int kConcatenationMaxSum(int[] arr, int k) {
        long MOD = 1000000007;
        long total = 0;

        for(int num : arr)
            total += num;

        if(k == 1)
            return (int)(kadane(arr) % MOD);

        int n = arr.length;
        int[] twice = new int[2*n];

        for(int i = 0; i < 2 * n; i++)
            twice[i] = arr[i % n];

        long best = kadane(twice);

        if(total > 0){
            best = (best + (k - 2) * total) % MOD;
        }

        return (int)(best % MOD);
    }

    public long kadane(int[] nums){
        long sum = 0;
        long ans = 0;

        for(int num : nums){
            sum += num;
            ans = Math.max(ans, sum);
            if(sum < 0)
                sum = 0;
        }

        return ans;
    }
}