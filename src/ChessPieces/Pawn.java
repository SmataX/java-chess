package ChessPieces;

public class Pawn extends ChessPiece {
    protected boolean movedTwoSquares;

    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color, 'p');
        this.movedTwoSquares = false;
    }

    public boolean getMovedTwoSquares() {
        return this.movedTwoSquares;
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
                movedTwoSquares = true;
                return true;
            }
        }

        if ((this.col - 1 == col || this.col + 1 == col) && this.row + dir == row && board[row][col] != null && board[row][col].getColor() != color) {
            return true;
        }

        if ((this.col - 1 == col || this.col + 1 == col) && this.row + dir == row) {
            ChessPiece targetPiece = board[this.row][col];
            if (targetPiece != null && targetPiece.getType() == PieceType.PAWN) {
                Pawn pawn = (Pawn) targetPiece;
                if (pawn.getColor() != color && pawn.getMovedTwoSquares() == true) {
                    board[pawn.getRow()][pawn.getCol()] = null;
                    return true;
                }
            }
        }

        return false;
    }
}
