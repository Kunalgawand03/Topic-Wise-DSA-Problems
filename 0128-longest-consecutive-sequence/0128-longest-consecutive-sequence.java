class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> st = new HashSet<>();
        int cnt = 0;
        int longest = 0;

        for(int i = 0 ; i < nums.length; i++){
            st.add(nums[i]);
        }

        for(int it: st){
            if(!st.contains(it-1)){
                cnt = 1;
                int x = it;

                while(st.contains(x+1)){
                    cnt++;
                    x++;
                }
            }
            longest = Math.max(longest, cnt);
        }
        return longest;
    }
}