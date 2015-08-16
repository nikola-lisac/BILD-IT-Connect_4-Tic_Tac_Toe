package TicTacToe;

public class NewGame {

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();//new instance
		System.out.println("\t Tic Tac Toe");
		game.drawBoard();//drawing a board
		game.fillBoard();//filling the fields of the board
		
		//outer loop that iterates until all the fields are filled or someone wins
		do {
			boolean isOk = false;
			int[] playersPosition = new int[2];//position where number of row and column will be stored
			//looping until player enters a position that isn't taken
			while (!isOk) {
				isOk = true;
				playersPosition = game.playersMove(); //number of raw and column where player wants to put his mark
				if (game.isTaken(playersPosition)) {
					isOk = false;
				}
			}
			//placing a players mark
			game.placeMark(playersPosition);
			//drawing a board with changes
			game.drawBoard();
			//check if we have a winner
			if(game.checkForWin()) {
				game.whoWon();
			}
			//change a player
			game.changePlayer();

		} while (!game.isBoardFull());
		//print a message if there is a draw
		System.out.println("We have a draw.");
	}

}
