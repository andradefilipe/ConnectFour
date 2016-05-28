package tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import connectfour.ConnectFour;
import validation.Validations;

public class ConnectFourTest {

	private ConnectFour connectFour;
	private Validations validations;

	int[][] grid = { { 1, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 2, 2, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0 } };

	int[] columnHeight = { 4, 0, 0, 0, 0, 2, 1 };
	int winner;

	@Before
	public void setUp() {

		connectFour = new ConnectFour();
		validations = new Validations();
		winner = validations.checkWinner(grid);
	}

	@Test
	public void testGameWinner() throws IOException {

		connectFour.setGrid(grid); /// Game is loaded with pre-set matrix

		assertTrue(winner == 1); /// Assert that the game is ended

	}

}
