package ChessPieces;

public class Pawn extends ChessPiece {
    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color, 'p');
    }

    @Override
    public boolean isValidMove(int col, int row, ChessPiece[][] board) {
        int dir = (this.color == PieceColor.WHITE) ? 1 : -1;
        if (this.col == col && this.row + dir == row && board[row][col] == null) {
            return true;
        }

        // if ((this.col - 1 == col || this.col + 1 == col) && this.row + dir == row && board[row][col].getColor() != color) {
        //     return true;
        // }
        return false;
    }
}
