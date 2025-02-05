package ChessPieces;

public abstract class ChessPiece {
    protected PieceType type;
    protected PieceColor color;
    protected char icon;

    public ChessPiece(PieceType type, PieceColor color, char icon) {
        this.type = type;
        this.color = color;
        this.icon = icon;
    }

    public abstract boolean isValidMove(int row, int col, int targetRow, int targetCol, ChessPiece[][] board);

    public PieceColor getColor() {
        return color;
    }

    public char getIcon() {
        return (color == PieceColor.WHITE) ? Character.toLowerCase(icon) : Character.toUpperCase(icon);
    }
}
