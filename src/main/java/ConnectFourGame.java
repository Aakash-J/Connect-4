import java.sql.SQLException;
import java.util.Scanner;

public class ConnectFourGame {
	static char[][] board = new char[6][7];

	public char[][] playGame(int col, String userName, char color, String gameId)
			throws ClassNotFoundException, SQLException {

		GameDao gd = new GameDao();
		board = gd.getBoard(Integer.valueOf(gameId));
		displayTheBoard(board);
		// Prompt the first player
		dropTheDisc(color, board, col, userName, Integer.valueOf(gameId));

		board = gd.getBoard(Integer.valueOf(gameId));
		displayTheBoard(board);
		if (isWon(board)) {
			// board[0][0] = 'W';
			gd.updateGame_status(Integer.valueOf(gameId), userName);
			System.out.println("Red player won!");
			// System.exit(1);
		} else if (isDraw(board)) {
			gd.updateGame_status(Integer.valueOf(gameId), "draw");
			board[0][0] = 'D';
			// System.exit(2);
		}
		return board;

	}

	public static void dropTheDisc(char player, char[][] board, int col, String userName, int gameId)
			throws ClassNotFoundException, SQLException {
		Scanner input = new Scanner(System.in);

		boolean done = false;

		do {
			System.out.print("Drop a " + (player == 'R' ? "red" : "yellow") + " disk at column (0-6): ");
			// int column = input.nextInt();

			if (placeTheDisk(board, col, player, userName, gameId)) {
				done = true;
			} else
				System.out.println("This column is full. Try a different column");
			break;
		} while (!done);
	}

	static boolean placeTheDisk(char[][] board, int column, char player, String userName, int gameId)
			throws ClassNotFoundException, SQLException {
		for (int i = 0; i < board.length; i++) {
			if (board[i][column] == '\u0000') {
				GameDao gd = new GameDao();
				gd.setBoard(gameId, userName, i + "," + column, "", player);
				board[i][column] = player;
				return true; // successful
			}
		}

		return false; // unsuccessful
	}

	static void displayTheBoard(char[][] board) {
		for (int i = board.length - 1; i >= 0; i--) {
			System.out.print("|");
			for (int j = 0; j < board[i].length; j++)
				System.out.print(board[i][j] != '\u0000' ? board[i][j] + "|" : " |");
			System.out.println();
		}
		System.out.println("----------------------");
	}

	public static boolean isWon(char[][] board) {
		return isConsecutiveFour(board);
	}

	public static boolean isDraw(char[][] board) {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				if (board[i][j] == '\u0000')
					return false;

		return true; // Cells are all now occupied
	}

	public static boolean isConsecutiveFour(char[][] values) {
		int numberOfRows = values.length;
		int numberOfColumns = values[0].length;

		// Check rows
		for (int i = 0; i < numberOfRows; i++) {
			if (isConsecutiveFour(values[i]))
				return true;
		}

		// Check columns
		for (int j = 0; j < numberOfColumns; j++) {
			char[] column = new char[numberOfRows];
			// Get a column into an array
			for (int i = 0; i < numberOfRows; i++)
				column[i] = values[i][j];

			if (isConsecutiveFour(column))
				return true;
		}

		// Check major diagonal (lower)
		for (int i = 0; i < numberOfRows - 3; i++) {
			int numberOfElementsInDiagonal = Math.min(numberOfRows - i, numberOfColumns);
			char[] diagonal = new char[numberOfElementsInDiagonal];
			for (int k = 0; k < numberOfElementsInDiagonal; k++)
				diagonal[k] = values[k + i][k];

			if (isConsecutiveFour(diagonal))
				return true;
		}

		// Check major diagonal (upper)
		for (int j = 1; j < numberOfColumns - 3; j++) {
			int numberOfElementsInDiagonal = Math.min(numberOfColumns - j, numberOfRows);
			char[] diagonal = new char[numberOfElementsInDiagonal];
			for (int k = 0; k < numberOfElementsInDiagonal; k++)
				diagonal[k] = values[k][k + j];

			if (isConsecutiveFour(diagonal))
				return true;
		}

		// Check sub-diagonal (left part)
		for (int j = 3; j < numberOfColumns; j++) {
			int numberOfElementsInDiagonal = Math.min(j + 1, numberOfRows);
			char[] diagonal = new char[numberOfElementsInDiagonal];

			for (int k = 0; k < numberOfElementsInDiagonal; k++)
				diagonal[k] = values[k][j - k];

			if (isConsecutiveFour(diagonal))
				return true;
		}

		// Check sub-diagonal (right part)
		for (int i = 1; i < numberOfRows - 3; i++) {
			int numberOfElementsInDiagonal = Math.min(numberOfRows - i, numberOfColumns);
			char[] diagonal = new char[numberOfElementsInDiagonal];

			for (int k = 0; k < numberOfElementsInDiagonal; k++)
				diagonal[k] = values[k + i][numberOfColumns - k - 1];

			if (isConsecutiveFour(diagonal))
				return true;
		}

		return false;
	}

	public static boolean isConsecutiveFour(char[] values) {
		for (int i = 0; i < values.length - 3; i++) {
			boolean isEqual = true;
			for (int j = i; j < i + 3; j++) {
				if (values[j] == '\u0000' || values[j] != values[j + 1]) {
					isEqual = false;
					break;
				}
			}

			if (isEqual)
				return true;
		}

		return false;
	}
}