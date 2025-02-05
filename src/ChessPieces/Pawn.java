package ChessPieces;

public class Pawn extends ChessPiece {
    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color, 'p');
    }

    @Override
    public boolean isValidMove(int row, int col, int targetRow, int targetCol, ChessPiece[][] board) {
        int dir = (this.color == PieceColor.WHITE) ? -1 : 1;
        if (targetCol == col && targetRow == row + dir && board[targetRow][targetCol] == null) {
            // Movement
            return true;
        }
        else if ((targetCol == col-1 || targetCol == col+1) && targetRow == row + dir && board[targetRow][targetCol].getColor() != color) {
            return true;
        }
        return false;
    }
}
