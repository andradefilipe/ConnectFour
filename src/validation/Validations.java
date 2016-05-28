package validation;

/**
 * This class aims to validate game possibilities
 * 
 * @author
 *
 */
public class Validations {
	private static final int WIDTH = 7, HEIGHT = 6;

	/**
	 * Check if the param grid has 4 disks in a row, column or diagonar
	 * 
	 * @param grid
	 * @return the winner player (1 or 2) or return 0 if there is not a winner
	 */
	public int checkWinner(int[][] grid) {
		int numberOfEqualSequencePieces, player;

		/*
		 * Checks four equal pieces in a row
		 */
		for (int y = 0; y < HEIGHT; y++) {
			numberOfEqualSequencePieces = 0; /// variable to control the number
												/// of equal positions
			player = 0;
			for (int x = 0; x < WIDTH; x++) {
				if (grid[x][y] == player)
					numberOfEqualSequencePieces++;
				else {
					numberOfEqualSequencePieces = 1;
					player = grid[x][y];
				}
				if (numberOfEqualSequencePieces == 4 && player > 0)
					return player;
			}
		}

		/*
		 * Checks four equal pieces in a column
		 */
		for (int x = 0; x < WIDTH; x++) {
			numberOfEqualSequencePieces = 0;
			player = 0;
			for (int y = 0; y < HEIGHT; y++) {
				if (grid[x][y] == player)
					numberOfEqualSequencePieces++;
				else {
					numberOfEqualSequencePieces = 1;
					player = grid[x][y];
				}
				if (numberOfEqualSequencePieces == 4 && player > 0)
					return player;
			}
		}

		/*
		 * Checks four equal pieces in a diagonal Upper,Left-Bottom,Right
		 */
		for (int xStart = 0, yStart = 2; xStart < 4;) {
			numberOfEqualSequencePieces = 0;
			player = 0;
			for (int x = xStart, y = yStart; x < WIDTH && y < HEIGHT; x++, y++) {
				if (grid[x][y] == player) {
					numberOfEqualSequencePieces++;
				} else {
					numberOfEqualSequencePieces = 1;
					player = grid[x][y];
				}
				if (numberOfEqualSequencePieces == 4 && player > 0) {
					return player;
				}
			}

			if (yStart == 0) {
				xStart++;
			} else {
				yStart--;
			}
		}

		/*
		 * Checks four equal pieces in a diagonal Bottom,Left-Upper,Right
		 */
		for (int xStart = 0, yStart = 3; xStart < 4;) {

			numberOfEqualSequencePieces = 0;
			player = 0;

			for (int x = xStart, y = yStart; x < WIDTH && y >= 0; x++, y--) {

				if (grid[x][y] == player) {
					numberOfEqualSequencePieces++;
				} else {
					numberOfEqualSequencePieces = 1;
					player = grid[x][y];
				}
				if (numberOfEqualSequencePieces == 4 && player > 0) {
					return player;
				}
			}
			if (yStart == HEIGHT - 1) {
				xStart++;
			} else {
				yStart++;
			}
		}
		return 0;
	}
}
