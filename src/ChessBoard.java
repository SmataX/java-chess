import ChessPieces.ChessPiece;
import ChessPieces.Pawn;
import ChessPieces.PieceColor;

public class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[8][8];
        init();
    }

    // set chess pieces on board
    private void init() {
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(PieceColor.WHITE);
            board[6][i] = new Pawn(PieceColor.BLACK);
        }
    }

    public void displayBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if ((row == 0 || row == 9) && col > 0 && col < 9) {
                    System.out.print((char)('A' + col - 1));
                }
                else if ((col == 0 || col == 9) && row > 0 && row < 9) {
                    System.out.print(row);
                }
                else if (row - 1 >= 0 && col - 1 >= 0 && row - 1 < 8
                        && board[row - 1][col - 1] != null) {
                    // Draw chess pieces when exist

                    System.out.print(board[row - 1][col - 1].getIcon());
                }
                else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }
}
