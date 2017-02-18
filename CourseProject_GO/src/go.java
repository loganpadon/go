//Logan Padon, CS 2336.001
//Start the program, input the indicated values
//X is the vertical component, Y is the horizontal component
import java.util.Scanner;

public class go {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		Scanner in3 = new Scanner(System.in);
		String playAgain;
		do {
			System.out.println("Time to play the gaaaaaaame. Size?");
			int size = in.nextInt();
			System.out
					.println("Which game type? 1 for PVP, 2 for PVC, and 3 for CVC");
			String choice = in2.nextLine();
			if (choice.equalsIgnoreCase("1")) {
				playGamePVP(size);
			} else if (choice.equals("2")) {
				playGamePVC(size);
			} else if (choice.equals("3")) {
				playGameCVC(size);
			}
			System.out.println("Want to play again? Y/N");
			playAgain = in3.nextLine();
		} while (playAgain.toUpperCase() != "Y");

	}

	static void playGamePVP(int size) { // Plays the game between two players
		Board b = new Board(size);
		Scanner in = new Scanner(System.in);
		int player = 1;
		boolean movesExist = true, validMove = false;

		b.markLegalMoves(player);
		b.printBoard();
		do {
			do {
				System.out.println("Player " + player + ", your x coordinate?");
				int x = in.nextInt();
				System.out.println("Player " + player + ", your y coordinate?");
				int y = in.nextInt();
				validMove = b.takeTurn(player, x, y);
			} while (!validMove);
			if (player != 1) {
				player = 1;
			} else {
				player = 2;
			}
			movesExist = b.markLegalMoves(player);

			System.out.println("");
			b.printBoard();
		} while (movesExist);

		int winner = b.countWinner();
		if (winner == 1) {
			System.out.println("Black Wins!");
		} else if (winner == 2) {
			System.out.println("White Wins!");
		} else {
			System.out.println("It's a tie!");
		}

		
	}

	static void playGamePVC(int size) { // Plays the game between a player and a
										// computer
		Board b = new Board(size);
		Scanner in = new Scanner(System.in);
		int player = 1;
		boolean movesExist = true, validMove = false;

		do {
			b.markLegalMoves(player);
			b.printBoard();
			do {
				player = 1;
				System.out.println("Player " + player + ", your x coordinate?");
				int x = in.nextInt();
				System.out.println("Player " + player + ", your y coordinate?");
				int y = in.nextInt();
				validMove = b.takeTurn(player, x, y);
			} while (!validMove);
			player = 2;
			movesExist = b.markLegalMoves(player);
			b.takeTurnComp(player);
			movesExist = b.markLegalMoves(1);
		} while (movesExist);

		int winner = b.countWinner();
		if (winner == 1) {
			System.out.println("Black Wins!");
		} else if (winner == 2) {
			System.out.println("White Wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	static void playGameCVC(int size) { // Plays the game between two computers
		Scanner in = new Scanner(System.in);
		Board b = new Board(size);
		int player = 1;
		boolean movesExist = true;
		b.markLegalMoves(player);
		b.printBoard();
		int doCount = 0;
		do {
			b.takeTurnComp(player);
			if (player != 1) {
				player = 1;
			} else {
				player = 2;
			}
			movesExist = b.markLegalMoves(player);
			System.out.println(doCount++);
			b.printBoard();
		} while (movesExist);

		int winner = b.countWinner();
		if (winner == 1) {
			System.out.println("Black Wins!");
		} else if (winner == 2) {
			System.out.println("White Wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}
}
