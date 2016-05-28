package output;

/**
 * This class has all possible console outputs
 * 
 * @author
 *
 */
public class Output {

	private static final int WIDTH = 7, HEIGHT = 6;

	/**
	 * Prints message informing human's turn
	 */
	public void humanTurn() {
		System.out.println(" ");
		System.out.println(" - Your turn, choose a column          (Press 9 to save current game)");
		System.out.print(" : ");
	}

	/**
	 * Prints the first message in the game
	 */
	public void mainMenu() {
		System.out.println(" ***** WELCOME TO CONNECT4 GAME ****");
		System.out.println(" - Press N to start a New Game");
		System.out.println(" - Press L to Load Game ");
		System.out.print(" : ");
	}

	/**
	 * Prints message asking the player to choose who starts
	 */
	public void chooseStarterPlayer() {
		System.out.println("");
		System.out.println(" Choose who starts");
		System.out.println(" - Press 1 (Human)");
		System.out.println(" - Press 2 (Computer)");
		System.out.print(" : ");
	}

	/**
	 * Printed when user tried an invalid input
	 */
	public void invalidInput() {
		System.out.println("");
		System.out.println(" - You entered an invalid option, try again");
		System.out.print(" : ");
	}

	/**
	 * Feedback user when he tries to load a game
	 */
	public void loadingGame() {
		System.out.println("");
		System.out.println(" Loading game... ");
	}

	/**
	 * Prints the current grid situation
	 * 
	 * @param grid
	 */
	public void printGrid(int[][] grid) {
		System.out.println("");
		for (int j = HEIGHT - 1; j >= 0; j--) {
			for (int i = 0; i < WIDTH; i++) {

				if (grid[i][j] == 2) {
					System.out.print(" X");
				} else if (grid[i][j] == 1) {
					System.out.print(" O");
				} else
					System.out.print(" .");
			}
			System.out.println("");
		}
		System.out.println(" 1 2 3 4 5 6 7");
	}

	/**
	 * Printed when the game ends with a draw
	 */
	public void drawMessage() {
		System.out.println("");
		System.out.println("It's a draw.");
	}

	/**
	 * Prints the game winner
	 */
	public void printWinner(int winner) {
		if (winner == 1)
			System.out.println("You won the game.");
		else
			System.out.println("Computer won the game.");

	}

}
