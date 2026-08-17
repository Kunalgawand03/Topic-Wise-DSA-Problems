class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;

        for(int i = 0; i <= n ; i++){
            while(!st.isEmpty() && (i == n || heights[st.peek()] >= (i < n ? heights[i] : 0))){
                int height = heights[st.pop()];

                int width = 0;
                if(st.isEmpty()){
                    width = i;
                }else{
                    width = i - st.peek() - 1;
                }

                maxArea = Math.max(maxArea, height * width);
            }
            st.push(i);
        }
        return maxArea;
    }
}