package ChessPieces;

public class Bishop extends ChessPiece {
    public Bishop(PieceColor color) {
        super(PieceType.BISHOP, color, 'b');
    }

    public Bishop(PieceColor color, int col, int row) {
        super(PieceType.BISHOP, color, 'b', col, row);
    }

    @Override
    public boolean isValidMove(int col, int row, ChessPiece[][] board) {
        if (Math.abs(col - this.col) != Math.abs(row - this.row)) {
            return false;
        }

        int dCol = Integer.compare(col, this.col);
        int dRow = Integer.compare(row, this.row);
        int curCol = this.col + dCol;
        int curRow = this.row + dRow;

        while (curCol != col || curRow != row) {
            if (board[curRow][curCol] != null) {
                return false;
            }
            curCol += dCol;
            curRow += dRow;
        }
        return board[row][col] == null || board[row][col].getColor() != color;
    }
}
