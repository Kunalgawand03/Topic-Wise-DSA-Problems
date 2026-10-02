class Solution {
    public int mySqrt(int x) {
        if(x < 2){
            return x;
        }

        int left = 1, right = x/2, ans = 0;

        while(left <= right){
            long mid = (left + right)/2;

            if(mid * mid <= x){
                ans = (int)mid;
                left = (int) mid+1;
            }
            else{
                right = (int) mid -1;
            }
        }
        return ans;
    }
}