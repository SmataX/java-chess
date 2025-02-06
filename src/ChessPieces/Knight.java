package ChessPieces;

public class Knight extends ChessPiece{
    public Knight(PieceColor color) {
        super(PieceType.KNIGHT, color, 'n');
    }

    @Override
    public boolean isValidMove(int col, int row, ChessPiece[][] board) {
        int dCol = Math.abs(col - this.col);
        int dRow = Math.abs(row - this.row);
        return (dCol == 2 && dRow == 1 || dCol == 1 && dRow == 2) && board[row][col] == null || board[row][col].getColor() != color;
    }
}
