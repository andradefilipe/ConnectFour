package launcher;

import java.io.IOException;

import connectfour.ConnectFour;

public class Main {
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) throws IOException {
		ConnectFour connectFour = new ConnectFour(); /// creates a new instance
														/// of connect four
		connectFour.play(); /// starts a new game
	}
}