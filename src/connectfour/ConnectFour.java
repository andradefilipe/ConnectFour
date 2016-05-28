package connectfour;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import ai.Minimax;
import output.Output;
import validation.Validations;

public class ConnectFour {

	private static final int WIDTH = 7, HEIGHT = 6; /// Controls the grid size
	private static final int MAX_RECURDEPTH = 6; /// Controls AI Depth
	private static final int HUMAN = 1; /// In every place, human means 2 O
	private static final int COMPUTER = 2; /// In every place, computer means 1
											/// X
	private static final String USERPATH = "C:/Users/Andrade/Desktop/eclipse/connectfour.txt";
	private static int recurDepth = 0;

	Random zufaellig = new Random(); /// The first computer move is random
	Vector moves = new Vector(); /// The list of possible moves(columns)
	Scanner sc = new Scanner(System.in); /// Variable to scan user entries

	private int[][] grid = new int[WIDTH][HEIGHT]; /// Creates the grid with
													/// this size
	private int[] columnHeight = new int[WIDTH]; /// Controls each column Height

	boolean saveGameFlag = false; /// Flag to control save game

	Output output = new Output(); /// New instance of output
	Minimax minimax = new Minimax(); /// New instance of minimax
	Validations validations = new Validations(); /// New instance of validations

	public void play() throws IOException { /// Starts a new game

		int column;
		int player;

		player = firstMenu(); /// shows intro menu, with necessary inputs to
								/// start the game

		while (true) { /// While the game is not ended
			if (player == HUMAN) { /// If the first choice is player2,
									/// human
				/// starts
				output.printGrid(grid); /// Prints the empty grid
				do {
					output.humanTurn(); /// Prints human turn message
					column = inputColumn() - 1; /// gets the desired column
					if (column == 8) { /// if option = 8 save game
						saveGameFlag = true; /// flag goes true
						try {
							saveGame(grid); /// save current grid status in a
											/// external file
							break; /// finishes the game
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} while (column < 0 || column >= WIDTH || columnHeight[column] >= HEIGHT);
			} else { /// if the first choice is player1, computer starts
				moves.removeAllElements(); /// clean moves vector
				column = 0;
				int prevValue = -MAX_RECURDEPTH - 1;
				for (int x = 0; x < WIDTH; x++) {
					if (columnHeight[x] >= HEIGHT)
						continue;
					int value = minimax.run(grid, columnHeight, recurDepth, COMPUTER, x); /// calls
					/// minimax
					if (value > prevValue) { /// if new value is grater than
												/// previous value
						moves.removeAllElements(); /// clean moves vector
						prevValue = value; /// prevValue gets new value
					}
					if (value == prevValue) { /// value gets previous
												/// value
						moves.add(new Integer(x)); /// add this move to
													/// moves
													/// list
					}
				}
				if (moves.size() > 0) { /// if moves list is greater than 1
					Collections.shuffle(moves); /// shuffles moves
					column = ((Integer) moves.get(0)).intValue(); /// column
																	/// gets
																	/// position
																	/// 0 of
																	/// moves
																	/// list
				}
				if (moves.size() == 0) { /// if moves list is zero
					output.drawMessage(); /// it is a draw
					break;
				}
			}
			if (column != 8) {

				grid[column][columnHeight[column]] = player; /// grid gets
																/// the
																/// move
				columnHeight[column]++; /// increases current column height

				int winner = 0; /// Initializates variable
				winner = validations.checkWinner(grid); /// check if we have
														/// a
														/// winner
				output.printGrid(grid); /// prints grid

				if (winner > 0) { /// if we have a winner
					output.printGrid(grid); /// prints the grid
					output.printWinner(winner); /// prints the winner and
												/// ends
												/// game
					break;
				}
				player = 3 - player; /// used to switch players in each
										/// iteraction
			} else
				break;
		}
	}

	private int firstMenu() throws IOException {
		int player;
		output.mainMenu(); /// Prints welcome message

		while (!sc.hasNext("[LlNn]")) { /// Aims to get user valid choice
			output.invalidInput(); /// Prints invalid input
			sc.next();
		}
		String input = sc.next();
		if (isLoadGame(input)) { /// checks if the user wants to load
			loadGame(); /// opens external file and load grid
			output.printGrid(grid);
			player = COMPUTER; ///
		} else
			player = getFirstPlayer(); /// asks user about the first player
		return player;
	}

	/**
	 * Method to get the first choice (first player)
	 * 
	 * @return the player who will start the game
	 * @throws IOException
	 */
	public int getFirstPlayer() throws IOException {
		int firstPlayer;
		do {
			firstPlayer = getsValidUserInput(); /// choose who start playing
		} while (firstPlayer != HUMAN && firstPlayer != COMPUTER); /// only
																	/// accepts
																	/// 1 or 2
		return firstPlayer; /// returns the desired first player
	}

	/**
	 * Method to control if the user wants to load game
	 * 
	 * @return true if the user selected to load game, false instead
	 * 
	 * @throws IOException
	 */
	public boolean isLoadGame(String input) throws IOException {
		if (input.equals("n") || input.equals("N")) { /// N or n means new game
			output.chooseStarterPlayer(); /// show this message
		} else if (input.equals("l") || input.equals("L")) { /// load game
			output.loadingGame(); /// shows loading game message
			return true;
		}
		return false;
	}

	/**
	 * Method used to load game
	 * 
	 * @return The grid with its map
	 * @throws IOException
	 */
	public void loadGame() throws IOException {
		sc = new Scanner(new File(USERPATH)); /// path to store data
		int[] laenge = new int[(HEIGHT * WIDTH) + WIDTH]; /// creates a vector

		int z = 0;
		while (sc.hasNextInt()) { /// reads file data
			laenge[z++] = sc.nextInt(); /// store data in this variable
		}

		int zaehler = 0; /// this part converts a 1D array into 2D array
		for (int i = 0; i < WIDTH; i++) { /// loop into lines and columns
			for (int j = 0; j < HEIGHT; j++) { /// loop into lines and columns
				grid[i][j] = laenge[zaehler]; /// loads 1D array into 2D array
				zaehler++; /// goes alog 1D array
			}
		}
		zaehler = 0; /// the last part of the saved file is columnHeight. This
						/// code loads it into the variable
		for (int i = 42; i < (HEIGHT * WIDTH) + WIDTH - 1; i++) {
			columnHeight[zaehler] = laenge[i]; /// from line 42 till the end,
												/// the data stored is
												/// columnHeight
			zaehler++;
		}

	}

	/**
	 * Method to ask user which column the user wants
	 * 
	 * @return the desired column
	 */
	public int inputColumn() {
		int column;
		do {
			column = getsValidUserInput();
		} while (column != 9 && (column < 1 || column > WIDTH));
		return column;
	}

	/**
	 * Method to gets user input
	 * 
	 * @return user's input
	 */
	public int getsValidUserInput() {

		int zugEingabe;
		sc = new Scanner(System.in); /// scans user input

		while (!sc.hasNextInt()) { /// while user input is invalid
			output.invalidInput(); /// prints invalid input
			sc.next();
		}

		zugEingabe = sc.nextInt(); /// gets the input as an integer

		return zugEingabe; /// return the valid input
	}

	public void saveGame(int[][] grid) throws IOException {

		FileWriter schreiben = new FileWriter(USERPATH); /// user path to save
		PrintWriter txt = new PrintWriter(schreiben);

		for (int i = 0; i < WIDTH; i++) /// loops over grid lines/columns
			for (int j = 0; j < HEIGHT; j++) /// loops over grid lines/columns
				txt.println(grid[i][j]); /// write data in txt file

		for (int j = 0; j < WIDTH; j++) /// loops over columnHeight lines
			txt.println(columnHeight[j]); /// write dat in txt file

		txt.flush(); /// flush data
		txt.close(); /// close txt stream
		schreiben.close();/// close file
	}

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}



	
}