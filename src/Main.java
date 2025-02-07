import ChessPieces.ChessPiece;
import ChessPieces.PieceColor;
import ChessPieces.PieceType;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        PieceColor currentTurn = PieceColor.WHITE;

        while (true) {
            chessBoard.displayBoard();
            System.out.println(currentTurn + "'s turn:");

            if (isKingInCheck(currentTurn, chessBoard.getBoard())) {
                if (isCheckmate(currentTurn, chessBoard.getBoard())) {
                    System.out.println(currentTurn == PieceColor.WHITE ? "WHITE WINS" : "BLACK WINS");
                    break;
                }
                System.out.println("Your king is in check!!!");
            }

            ChessPiece selectedPiece = selectValidPiece(chessBoard, currentTurn);
            int[] targetCoords = selectValidMove(chessBoard, selectedPiece);

            // Move piece
            chessBoard.setPiece(selectedPiece.getCol(), selectedPiece.getRow(), null);
            chessBoard.setPiece(targetCoords[0], targetCoords[1], selectedPiece);

            chessBoard.updateChessPieces();
            currentTurn = switchTurn(currentTurn);


            System.out.println();
        }

        scanner.close();
    }

    // Selecting chess piece of current player
    private static ChessPiece selectValidPiece(ChessBoard chessBoard, PieceColor currentTurn) {
        ChessPiece piece;
        do {
            System.out.print("Select piece: ");
            int[] coords = getCoordsOnChessBoard();
            piece = chessBoard.getPiece(coords[0], coords[1]);
        } while (piece == null || piece.getColor() != currentTurn);

        return piece;
    }

    private static int[] selectValidMove(ChessBoard chessBoard, ChessPiece piece) {
        int[] targetCoords;
        do {
            System.out.print("Select target: ");
            targetCoords = getCoordsOnChessBoard();
        } while (!piece.isValidMove(targetCoords[0], targetCoords[1], chessBoard.getBoard()));

        return targetCoords;
    }

    private static PieceColor switchTurn(PieceColor currentTurn) {
        return (currentTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    private static int[] getCoordsOnChessBoard() {
        String userInput;
        while (true) {
            userInput = scanner.nextLine().trim().toUpperCase();
            if (isValidChessNotation(userInput)) {
                int col = userInput.charAt(0) - 'A';
                int row = Character.getNumericValue(userInput.charAt(1)) - 1;
                return new int[]{col, row};
            }
            System.out.println("Invalid input! Use chess notation (e.g., A2, B7). Try again.");
        }
    }

    private static boolean isValidChessNotation(String input) {
        return input.matches("^[A-H][1-8]$");
    }

    // Find an king
    private static ChessPiece getKing(PieceColor color, ChessPiece[][] board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece chessPiece = board[row][col];
                if (chessPiece != null && chessPiece.getType() == PieceType.KING && chessPiece.getColor() == color) {
                    return chessPiece;
                }
            }
        }
        return null;
    }

    // Check if king is in check
    private static boolean isKingInCheck(PieceColor color, ChessPiece[][] board) {
        ChessPiece king = getKing(color, board);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece chessPiece = board[row][col];

                // only enemy chess pieces
                if (chessPiece != null && chessPiece.getColor() != color) {
                    if (chessPiece.isValidMove(king.getCol(), king.getRow(), board)) {
                        // System.out.println("--- " + chessPiece.getType());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isCheckmate(PieceColor color, ChessPiece[][] board) {
        if (!isKingInCheck(color, board)) {
            return false;
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece chessPiece = board[row][col];

                if (chessPiece != null && chessPiece.getColor() == color) {
                    // check every valid move
                    for (int row2 = 0; row2 < 8; row2++) {
                        for (int col2 = 0; col2 < 8; col2++) {
                            if (chessPiece.isValidMove(col2, row2, board)) {
                                // simulate move
                                ChessPiece temp = board[row2][col2];
                                board[row2][col2] = chessPiece;
                                board[row][col] = null;


                                boolean stillInCheck = isKingInCheck(color, board);
                                // undo move
                                board[row][col] = chessPiece;
                                board[row2][col2] = temp;

                                if (!stillInCheck) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
