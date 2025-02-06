package ChessPieces;

public class King extends ChessPiece {
    public King(PieceColor color) {
        super(PieceType.KING, color, 'k');
    }

    @Override
    public boolean isValidMove(int col, int row, ChessPiece[][] board) {
        int dCol = Math.abs(col - this.col);
        int dRow = Math.abs(row - this.row);
        return dCol <= 1 && dRow <= 1 && (board[row][col] == null || board[row][col].getColor() != color);
    }
}
