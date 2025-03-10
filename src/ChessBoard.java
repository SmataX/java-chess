import ChessPieces.*;

public class ChessBoard {
    private final ChessPiece[][] board = new ChessPiece[8][8];

    public ChessBoard() {
        initializeBoard();
        updateChessPieces();
    }

    // set chess pieces on board
    private void initializeBoard() {
        // Placing PAWNS
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(PieceColor.WHITE);
            board[6][i] = new Pawn(PieceColor.BLACK);
        }

        board[0][0] = new Rook(PieceColor.WHITE);
        board[0][7] = new Rook(PieceColor.WHITE);
        board[7][0] = new Rook(PieceColor.BLACK);
        board[7][7] = new Rook(PieceColor.BLACK);

        board[0][1] = new Knight(PieceColor.WHITE);
        board[0][6] = new Knight(PieceColor.WHITE);
        board[7][1] = new Knight(PieceColor.BLACK);
        board[7][6] = new Knight(PieceColor.BLACK);

        board[0][2] = new Bishop(PieceColor.WHITE);
        board[0][5] = new Bishop(PieceColor.WHITE);
        board[7][2] = new Bishop(PieceColor.BLACK);
        board[7][5] = new Bishop(PieceColor.BLACK);

        board[0][3] = new Queen(PieceColor.WHITE);
        board[7][3] = new Queen(PieceColor.BLACK);

        board[0][4] = new King(PieceColor.WHITE);
        board[7][4] = new King(PieceColor.BLACK);
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    public void setPiece(int col, int row, ChessPiece piece) {
        board[row][col] = piece;
    }

    public ChessPiece getPiece(int col, int row) {
        return board[row][col];
    }

    // Updating position for each piece on the board
    public void updateChessPieces() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board[row][col];
                if (piece != null) {
                    piece.updatePosition(col, row);
                }
            }
        }
    }

    // Displaying the chessboard with labels
    public void displayBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if ((row == 0 || row == 9) && col > 0 && col < 9) {
                    System.out.print((char)('a' + col - 1) + " ");
                }
                else if ((col == 0 || col == 9) && row > 0 && row < 9) {
                    System.out.print(row + " ");
                }
                else if (row - 1 >= 0 && col - 1 >= 0 && row - 1 < 8
                        && board[row - 1][col - 1] != null) {
                    // Draw chess pieces when exist

                    System.out.print(board[row - 1][col - 1].getIcon() + " ");
                }
                else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
