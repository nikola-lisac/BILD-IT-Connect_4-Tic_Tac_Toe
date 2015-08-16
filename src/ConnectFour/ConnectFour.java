package ConnectFour;

import java.util.Scanner;
/**
 * Classic game Connect 4
 * Connect Four is a two-player connection game in which the players first choose a color 
 * and then take turns dropping colored discs from the top into a seven-column, six-row vertically suspended grid. 
 * The pieces fall straight down, occupying the next available space within the column. 
 * The objective of the game is to connect four of one's own discs of the same color 
 * next to each other vertically, horizontally, or diagonally before your opponent.
 * @author Nikola Lisicic
 *
 */
public class ConnectFour {
	private char[][] board; // game board
	private char player; // player (color of the disks R or Y

	/** Class constructor which creates new 6*7 board and sets first player to red */
	ConnectFour() {
		board = new char[6][7];
		player = 'R';
	}

	/**
	 * Filling the board with ' ' (empty spaces)
	 */
	void initializeBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

	/**
	 * Printing game board
	 */
	void drawBoard() {
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			System.out.print("|");
			for (int j = 0; j < board[0].length; j++) {
				System.out.print("| " + board[i][j] + " |");
			}
			System.out.print("|");
			System.out.println();
		}
		System.out.println("-------------------------------------");
	}

	/**
	 * Getting the name of the color for the current player
	 * @return  String with the name of the currents players color
	 */
	String colorOfTheDisk() {
		if (player == 'R') {
			return "red";
		} else {
			return "yellow";
		}
	}

	/**
	 * Checking if there is an open slot in the specified column
	 * @param column  number of column to check for available slot
	 * @return true if there is an available slot in that column, otherwise false
	 */
	boolean availableField(int column) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][column] == ' ') {
				return true;
			}
		}
		return false;
	}

	/**
	 * Player is entering number of column in which he wants to put his disk
	 * @return number of column to put the disk
	 */
	int playersMove() {
		String diskColor = colorOfTheDisk(); //players disk color
		boolean isOk = false;
		int playersInput = -1;
		
		while (!isOk) {
			isOk = true;
			System.out.print("Drop a " + diskColor
					+ " disk in the column (0 - 6): ");
			
			try {
				
				Scanner input = new Scanner(System.in);
				playersInput = input.nextInt(); //players input column
				
			} catch (Exception e) {
				System.out.println("Wrong input.");
				isOk = false;
			}
			//if the wrong number of column is entered
			if (playersInput < 0 || playersInput > 6) {
				System.out.println("Wrong input, column are marked from 0 to 6, try again. ");
				isOk = false;
			}
			//if there is no available slot in the column the player has chosen
			if (!availableField(playersInput)) {
				System.out.println("Unavaible field in that column, try again. ");
				isOk = false;
			}

		}
		return playersInput;
	}

	/**
	 * Placing a disk on the first open position in required column from the
	 * bottom
	 * 
	 * @param column
	 *            number of column where the disk is required to be placed
	 */
	void placeDisk(int column) {
		for (int i = board.length - 1; i >= 0; i--) {
			if (board[i][column] == ' ') {
				board[i][column] = player;
				break;
			}
		}
	}

	/**
	 * changing the player if current player is red, the yellow player has next
	 * turn, and vice versa
	 */
	void changePlayer() {
		if (player == 'R') {
			player = 'Y';
		} else {
			player = 'R';
		}
	}

	/**
	 * Checking if four consecutive disk's color's are the same if they are we
	 * have a winner
	 * 
	 * @param diskOne
	 *            mark of the first disk
	 * @param diskTwo
	 *            mark of the second disk
	 * @param diskThree
	 *            mark of the third disk
	 * @param diskFour
	 *            mark of the fourth disk
	 * @return true if all marks are the same and we have a winner, otherwise
	 *         false
	 */
	boolean checkDiskColorsForWin(char diskOne, char diskTwo, char diskThree,
			char diskFour) {
		if (diskOne != ' ' && diskOne == diskTwo && diskTwo == diskThree
				&& diskThree == diskFour) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checking if there is a win in any row
	 * 
	 * @return true if there is a win, otherwise false
	 */
	boolean checkRowsForWin() {
		for (int i = board.length - 1; i >= 0; i--) {
			for (int j = 0; j <= 3; j++) {
				if (checkDiskColorsForWin(board[i][j], board[i][j + 1],
						board[i][j + 2], board[i][j + 3])) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checking if there is a win in any column
	 * 
	 * @return true if there is a win, otherwise false
	 */
	boolean checkColumnsForWin() {
		for (int i = board[0].length - 1; i >= 0; i--) {
			for (int j = 0; j < 3; j++) {
				if (checkDiskColorsForWin(board[j][i], board[j + 1][i],
						board[j + 2][i], board[j + 3][i])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checking if there is a win in any diagonal
	 * 
	 * @return true if there is a win, otherwise false
	 */
	boolean checkDiagonalsForWin() {
		// checking left-down to right-up diagonals for win
		for (int i = board.length - 1; i >= 3; i--) {
			for (int j = 3; j >= 0; j--) {
				if (checkDiskColorsForWin(board[i][j], board[i - 1][j + 1],
						board[i - 2][j + 2], board[i - 3][j + 3])) {
					return true;
				}
			}
		}

		// checking right down to left up diagonals for win
		for (int i = board.length - 1; i >= 3; i--) {
			for (int j = board[0].length - 1; j >= 3; j--) {
				if (checkDiskColorsForWin(board[i][j], board[i - 1][j - 1],
						board[i - 2][j - 2], board[i - 3][j - 3])) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Check if there is a win on the board First we are checking for win in the
	 * rows, than columns and finally diagonals If any of that methods returns
	 * true, we have a winner
	 * 
	 * @return true if there is, otherwise false
	 */
	boolean checkForWin() {
		if (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Printing the color of the player who won and the message that he won
	 */
	void printWinner() {
		String winner = colorOfTheDisk(); // getting the color of the current
											// player
		System.out.println("The " + winner + " player won!");
		System.exit(0);
	}

	/**
	 * Checking if all the slots on the board are filled
	 * 
	 * @return true if the slots are filled, otherwise false
	 */
	boolean isFilledBoard() {
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
	 * Starts a new game and players play it until the winner is anounced or
	 * there is a draw
	 */
	void newGame() {
		initializeBoard(); //setting slots to ' '
		drawBoard(); //drawing the game board
		
		//while there are open slots on the board
		while (!isFilledBoard()) {
			//player enters a column
			int requiredColumn = playersMove();
			//placing players disk in desired column
			placeDisk(requiredColumn);
			//drawing updated game board
			drawBoard();
			//if there is a winner
			if (checkForWin()) {
				//print winner
				printWinner();
			}
			//change the mark of the player who is on turn
			changePlayer();

		}
		//if all slots of the board are filled we have a draw
		System.out.println("We have a draw!");
	}

	/**
	 * Prints game header
	 */
	void printHeader() {
		System.out.println("***********************************");
		System.out.println("\t    Connect 4\n");
		System.out.println("***********************************\n");
		
		}

	
}
