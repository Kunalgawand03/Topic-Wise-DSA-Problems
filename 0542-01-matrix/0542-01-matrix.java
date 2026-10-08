class Solution {
    public int[][] updateMatrix(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[rows][cols];

        for(int i = 0 ; i < rows; i++){
            Arrays.fill(dist[i], -1);
        }

        for(int row = 0 ; row < rows ; row++){
            for(int col = 0 ; col < cols ; col++){
                if(grid[row][col] == 0){
                    dist[row][col] = 0;
                    q.offer(new int[]{row, col});
                }
            }
        }

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while(!q.isEmpty()){
            int[] cell = q.poll();
            int row = cell[0];
            int col = cell[1];

            for(int i = 0 ; i < 4; i++){
                int nextRow = row + dRow[i];
                int nextCol = col + dCol[i];

                if(nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols && dist[nextRow][nextCol] == -1){
                    dist[nextRow][nextCol] = dist[row][col]+1;
                    q.offer(new int[]{nextRow, nextCol});
                }
            }
        }
        return dist;
    }
}