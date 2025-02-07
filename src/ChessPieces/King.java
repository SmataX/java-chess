package ChessPieces;

public class King extends ChessPiece {
    protected boolean hasMoved = false;

    public King(PieceColor color) {
        super(PieceType.KING, color, 'k');
    }

    public boolean hasMoved() {
        return this.hasMoved;
    }

    @Override
    public boolean isValidMove(int col, int row, ChessPiece[][] board) {
        int dCol = Math.abs(col - this.col);
        int dRow = Math.abs(row - this.row);

        if (dCol <= 1 && dRow <= 1) {
            return board[row][col] == null || board[row][col].getColor() != color;
        }

        if (dCol == 2 && dRow == 0 && !hasMoved) {
            int dir = col > this.col ? 1 : -1;
            int rookCol = row > this.row ? 0 : 7;
            ChessPiece targetPiece = board[this.row][rookCol];

            if (targetPiece != null && targetPiece.getType() == PieceType.ROOK) {
                Rook rook = (Rook)targetPiece;

                if (rook.hasMoved()) {
                    return false;
                }

                if (board[this.row][this.col + dir] == null && board[this.row][this.col + dir * 2] == null) {
                    ChessPiece temp = board[rook.getRow()][rook.getCol()];
                    board[rook.getRow()][rook.getCol()] = null;
                    board[rook.getRow()][rook.getCol() - dir * 2] = temp;
                    return true;
                }
            }
        }
        return false;
    }
}
