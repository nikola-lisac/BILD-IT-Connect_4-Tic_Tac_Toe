package TicTacToe;
import java.util.Scanner;
/**
 * Classic Tic Tac Toe game
 * @author Nikola Lisicic
 *
 */
public class TicTacToe {

	private char[][] board;// game board which holds the player's moves
	private char player;// player who is on the turn

	TicTacToe() {
		board = new char[3][3];// new 3*3 board
		player = 'X';// player's turn is X
	}

	/**
	 * Printing a game board
	 */
	void drawBoard() {
		System.out.println("\t-------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("\t|");
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(" " + board[i][j] + " |");
			}
			System.out.println("\n\t-------------");
		}
	}

	/**
	 * Fills the board with ' ' characters
	 */
	void fillBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

	/**
	 * Getting the position where the player wants to put his mark
	 * 
	 * @return int[] which hold the values of number of row and column where the
	 *         player wants to place his mark
	 */
	int[] playersMove() {
		int count = 0;// number of prints(1 for row, 1 for column)
		int[] playersMoveRowColumn = new int[2]; // number of row and column for
													// players move
		String rowOrColumn = "";// string which will contain "row" or "column"
								// to print message to the user

		// outer loop which iterates twice (once for the row input, and once for
		// the column input
		do {

			// changing the message for the user depending on the iteration
			if (count == 0) {
				rowOrColumn = "row";
			} else {
				rowOrColumn = "column";
			}

			// first time it prints "enter a row..", second time it prints
			// "enter a column.."
			System.out.print("Enter a " + rowOrColumn
					+ " (0, 1, or 2) for player " + player + ": ");

			boolean isOk = false; // control variable for the input loop
			// loop runs until the player enters correct input
			while (!isOk) {
				int playersInput = -1;
				isOk = true;
				try {
					Scanner in = new Scanner(System.in);
					playersInput = in.nextInt(); // users input
				} catch (Exception e) {
					System.out.println("Wrong input.");
					isOk = false;
				}
				if (playersInput < 0 || playersInput > 2) {
					isOk = false;
					System.out.print("Try again: ");
				} else {
					playersMoveRowColumn[count] = playersInput;
				}
			}
			count++;
		} while (count < 2);
		
		return playersMoveRowColumn;
	}

	/**
	 * Placing a players mark on the desired position if the position is taken,
	 * player inputs another position
	 * 
	 * @param position
	 *            number of row and column where the player wants to put his
	 *            mark
	 */
	void placeMark(int[] position) {
		if (this.isTaken(position)) {
			System.out.println("That position is taken, try another position.");
			this.playersMove();
		} else {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					board[position[0]][position[1]] = player;
				}
			}
		}
	}

	/**
	 * Checking is the specified field taken
	 * 
	 * @param position
	 *            array with coordinates of the desired position, index 0 - row,
	 *            index 1 - column
	 * @return true if position is taken. false otherwise
	 */
	boolean isTaken(int[] position) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[position[0]][position[1]] != ' ') {
					System.out.println("That field is taken, try again.");
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Setting the player whose turn is next
	 */
	void changePlayer() {
		if (player == 'X') {
			player = 'O';
		} else {
			player = 'X';
		}
	}

	/**
	 * Check to see if all three values are the same (and not empty) indicating
	 * a win.
	 */
	boolean checkMarks(char c1, char c2, char c3) {
		return ((c1 != ' ') && (c1 == c2) && (c2 == c3));
	}

	/**
	 * Checking if there are same marks in rows, if there are, we got a win
	 * 
	 * @return true, if there is a win by rows, false if there isnt
	 */
	boolean checkRowsForWin() {
		for (int i = 0; i < board.length; i++) {
			if (checkMarks(board[i][0], board[i][1], board[i][2])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checking if there is a winner in columns
	 * 
	 * @return true if there is a winner, false if there isnt
	 */
	boolean checkColumnsForWin() {
		for (int i = 0; i < board.length; i++) {
			if (checkMarks(board[0][i], board[1][i], board[2][i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checking for the win in diagonals
	 * 
	 * @return true if there is a winner, false if there isnt
	 */
	boolean checkDiagonalsForWin() {
		if (checkMarks(board[0][0], board[1][1], board[2][2])
				|| checkMarks(board[0][2], board[1][1], board[2][0])) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if there is a winner
	 * 
	 * @return true if there is a winner, otherwise false
	 */
	boolean checkForWin() {
		if (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin()) {
			return true;

		}
		return false;

	}

	/**
	 * Checking if all positions on the board are filled
	 * 
	 * @return true if they are, false if they arent
	 */
	boolean isBoardFull() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;

	}
	
	/**
	 * Printing the winner
	 */
	void whoWon() {
		System.out.println("\n\t"+player + " player won!");
		System.exit(0);
	}

}
