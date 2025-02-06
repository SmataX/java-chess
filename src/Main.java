import ChessPieces.ChessPiece;
import ChessPieces.PieceColor;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        PieceColor currentTurn = PieceColor.WHITE;
        boolean gameRunning = true;

        while (gameRunning) {
            chessBoard.displayBoard();
            System.out.println(currentTurn + "'s turn:");

            ChessPiece selectedPiece = selectValidPiece(chessBoard, currentTurn);
            int[] targetCoords = selectValidMove(chessBoard, selectedPiece);

            // Move piece
            chessBoard.setPiece(selectedPiece.getCol(), selectedPiece.getRow(), null);
            chessBoard.setPiece(targetCoords[0], targetCoords[1], selectedPiece);

            chessBoard.updateChessPieces();
            currentTurn = switchTurn(currentTurn);

            // checkmate
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
}
