package ChessPieces;

public class Pawn extends ChessPiece {
    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color, 'p');
    }

    @Override
    public boolean isValidMove(int col, int row, ChessPiece[][] board) {
        int dir = (this.color == PieceColor.WHITE) ? 1 : -1;

        if (this.col == col && board[row][col] == null) {
            int dRow = Math.abs(row - this.row);

            if (dRow == 1) {
                return true;
            }
            else if (dRow == 2 && ((this.row == 1 && color == PieceColor.WHITE) || (this.row == 6 && color == PieceColor.BLACK))) {
                return true;
            }
        }

        if ((this.col - 1 == col || this.col + 1 == col) && this.row + dir == row && board[row][col] != null && board[row][col].getColor() != color) {
            return true;
        }
        return false;
    }
}
