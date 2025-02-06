package ChessPieces;

public abstract class ChessPiece {
    protected PieceType type;
    protected PieceColor color;
    protected char icon;
    protected int col, row;

    public ChessPiece(PieceType type, PieceColor color, char icon) {
        this.type = type;
        this.color = color;
        this.icon = icon;
    }

    public ChessPiece(PieceType type, PieceColor color, char icon, int col, int row) {
        this.type = type;
        this.color = color;
        this.icon = icon;
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    // Check if given move is valid
    public abstract boolean isValidMove(int col, int row, ChessPiece[][] board);

    // Get color of chess piece
    public PieceColor getColor() {
        return color;
    }

    // Get displayed char depending on color
    public char getIcon() {
        return (color == PieceColor.WHITE) ? Character.toLowerCase(icon) : Character.toUpperCase(icon);
    }

    // Update a position of the chess piece
    public void updatePosition(int col, int row) {
        this.col = col;
        this.row = row;
    }
}
