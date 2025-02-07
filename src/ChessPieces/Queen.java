package ChessPieces;

public class Queen extends ChessPiece {
    public Queen(PieceColor color) {
        super(PieceType.QUEEN, color, 'q');
    }


    @Override
    public boolean isValidMove(int col, int row, ChessPiece[][] board) {
        return new Rook(color, this.col, this.row).isValidMove(col, row, board) || new Bishop(color, this.col, this.row).isValidMove(col, row, board);
    }
}
