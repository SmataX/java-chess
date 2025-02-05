import ChessPieces.PieceColor;

import java.util.Scanner;

public class GameEngine {
    private PieceColor playerTurn;

    private void changeTurn() {
        playerTurn = (playerTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    // Returns the coordinates on the chess board given by the player
    public int[] getCoordinatesOnChessBoard() {
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
