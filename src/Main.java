import ChessPieces.ChessPiece;
import ChessPieces.PieceColor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        PieceColor currentColor = PieceColor.WHITE;
        boolean isFinished = false;

        // main game loop
        while (!isFinished) {
            chessBoard.displayBoard();
            System.out.println((currentColor == PieceColor.WHITE) ? "(WHITE):" : "(BLACK):");
            ChessPiece selectedChessPiece = null;
            while (selectedChessPiece == null || selectedChessPiece.getColor() != currentColor) {
                System.out.println("(select): ");
                selectedChessPiece = getChessPiece(chessBoard);
            }

            System.out.println("(target): ");
            int[] targetCoords = getCoordsOnChessBoard();
            while (!selectedChessPiece.isValidMove(targetCoords[0], targetCoords[1], chessBoard.getBoard())) {
                System.out.println("(target2): ");
                targetCoords = getCoordsOnChessBoard();
            }

            chessBoard.setPiece(selectedChessPiece.getCol(), selectedChessPiece.getRow(), null);
            chessBoard.setPiece(targetCoords[0], targetCoords[1], selectedChessPiece);

            chessBoard.updateChessPieces();
            currentColor = (currentColor == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
            // Checkmate
        }
    }


    public static ChessPiece getChessPiece(ChessBoard chessBoard) {
        int[] playerInput = getCoordsOnChessBoard();
        int col = playerInput[0];
        int row = playerInput[1];
        return chessBoard.getPiece(col, row);
    }

    // Returns the coords on the chess board given by the player
    public static int[] getCoordsOnChessBoard() {
        Scanner scanner = new Scanner(System.in);
        String userInput = null;
        boolean inputError = false;

        // Input validation
        while (userInput == null || userInput.length() != 2 || inputError) {
            userInput = scanner.nextLine();
            inputError = false;

            if (!(Character.isLetter(userInput.charAt(0)) && Character.isDigit(userInput.charAt(1)))
                    && userInput.length() == 2) {
                inputError = true;
            }
        }

        // Converting char values to int
        int col = Character.getNumericValue(userInput.charAt(0)) - 10;
        int row = Character.getNumericValue(userInput.charAt(1)) - 1;

        return new int[] {col, row};
    }
}