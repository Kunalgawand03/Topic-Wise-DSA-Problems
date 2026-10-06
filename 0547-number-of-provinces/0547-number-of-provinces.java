class Solution {
    private void dfs(int city, int[][] isConnected, int[] vis){
        vis[city] = 1;

        int n = isConnected.length;

        for(int i = 0 ; i < n ; i++){
            if (isConnected[city][i] == 1 && vis[i] == 0) {
                dfs(i, isConnected, vis);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] vis = new int[n];
        int province = 0;

        for(int city = 0 ; city < n ; city++){
            if(vis[city] == 0){
                province++;
                dfs(city, isConnected, vis);
            }
        }
        return province;
    }
}