class Solution {
    private void markSafe(int row, int col , char[][] board){
        int rows = board.length;
        int cols = board[0].length;

        if (row < 0 || col < 0 || row >= rows || col >= cols || board[row][col] != 'O') {
            return;
        }

        board[row][col] = '#';
 
        markSafe(row - 1, col, board);
 
        markSafe(row + 1, col, board);
 
        markSafe(row, col - 1, board);
 
        markSafe(row, col + 1, board);
    }
    public void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for(int row = 0 ; row < rows ; row++){
            markSafe(row, 0 , board);
            markSafe(row, cols - 1, board);
        }  

        for(int col = 0 ; col < cols ; col++){
            markSafe(0, col, board);
            markSafe(rows - 1, col, board);
        }

        for(int r = 0 ; r < rows ; r++){
            for(int c = 0; c < cols ; c++){
                if(board[r][c] == 'O'){
                    board[r][c] = 'X';
                }

                else if(board[r][c] == '#'){
                    board[r][c]= 'O';
                }
            }
        }
    }
}