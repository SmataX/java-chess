package ChessPieces;

public class Rook extends ChessPiece{
    protected boolean hasMoved = false;

    public Rook(PieceColor color) {
        super(PieceType.ROOK, color, 'r');
    }

    public Rook(PieceColor color, int col, int row) {
        super(PieceType.ROOK, color, 'r', col, row);
    }

    public boolean hasMoved() {
        return this.hasMoved;
    }


    @Override
    public boolean isValidMove(int col, int row, ChessPiece[][] board) {
        if (this.col != col && this.row != row) {
            return false;
        }

        int dirCol = Integer.compare(col, this.col);
        int dirRow = Integer.compare(row, this.row);
        int curCol = this.col + dirCol;
        int curRow = this.row + dirRow;

        while (curCol != col || curRow != row) {
            if (board[curRow][curCol] != null) {
                return false;
            }
            curCol += dirCol;
            curRow += dirRow;
        }
        if (board[row][col] == null || board[row][col].getColor() != color) {
            hasMoved = true;
            return true;
        }
        return false;
    }
}
