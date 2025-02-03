public abstract class ChessPiece {
    protected PieceType type;
    protected PieceColor color;
    protected int row, col;

    public ChessPiece(PieceType type, PieceColor color, int row, int col) {
        this.type = type;
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public abstract boolean isValidMove(int row, int col, ChessPiece[][] board);
}
