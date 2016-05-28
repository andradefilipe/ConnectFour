package ai;

import output.Output;
import validation.Validations;

public class Minimax {

	private static final int WIDTH = 7, HEIGHT = 6;
	private static final int MAX_RECURDEPTH = 6;
	private static final int COMPUTER = 2;

	Output output = new Output();
	Validations validations = new Validations();

	/**
	 * Runs the minimax method
	 * 
	 * @param grid
	 *            The current grid
	 * @param columnHeight
	 *            The height of the each column
	 * @param recurDepth
	 * @param player
	 *            The player who places the disk at this move
	 * @param column
	 *            Column where it will be placed the move
	 * @return
	 */
	public int run(int[][] grid, int columnHeight[], int recurDepth, int player, int column) {
		int value = 0;
		if (columnHeight[column] >= HEIGHT) {/// column is full, no moves
												/// allowed at current column
			return 0;
		}
		recurDepth++;
		grid[column][columnHeight[column]] = player;
		columnHeight[column]++;
		// output.printGrid(grid);

		if (validations.checkWinner(grid) > 0) {
			if (player == COMPUTER) /// if winner is computer -> max
				value = MAX_RECURDEPTH + 1 - recurDepth;
			else /// if winner is human -> min
				value = -MAX_RECURDEPTH - 1 + recurDepth;
		}

		if (recurDepth < MAX_RECURDEPTH && value == 0) {
			value = MAX_RECURDEPTH + 1;
			for (int x = 0; x < WIDTH; x++) {
				if (columnHeight[x] >= HEIGHT)
					continue;
				int v = run(grid, columnHeight, recurDepth, 3 - player, x);
				if (value == (MAX_RECURDEPTH + 1))
					value = v;
				else if (player == COMPUTER) {
					if (value > v)
						value = v;
				} else if (v > value)
					value = v;
			}
		}

		recurDepth--;
		columnHeight[column]--;
		grid[column][columnHeight[column]] = 0;

		return value;
	}
}
