import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int fresh = 0;
        int minutes = 0;
        Queue<int[]> q = new LinkedList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 2) {
                    q.offer(new int[] {row, col});
                } else if (grid[row][col] == 1) {
                    fresh++;
                }
            }
        }

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!q.isEmpty() && fresh > 0) {
            int levelSize = q.size();

            for (int cnt = 0; cnt < levelSize; cnt++) {
                int[] cell = q.poll();
                int row = cell[0];
                int col = cell[1];
                
                for (int i = 0; i < 4; i++) {
                    int r = row + dRow[i];
                    int c = col + dCol[i];

                    if (r >= 0 && c >= 0 && r < rows && c < cols && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        fresh--;
                        q.offer(new int[]{r, c});
                    }
                }
            }
            minutes++;
        }

        return fresh > 0 ? -1 : minutes;
    }
}