import java.lang.reflect.Array;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;

public class Board {
	private char[][] board;
	private int size;

	Board() {
		board = new char[0][0];
		this.size = 0;
	}

	Board(int size) {
		this.size = size;
		board = new char[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				board[x][y] = 'X';
			}
		}
		if (size % 2 == 0) {
			board[size / 2 - 1][size / 2 - 1] = 'B';
			board[size / 2][size / 2] = 'B';
			board[size / 2][size / 2 - 1] = 'W';
			board[size / 2 - 1][size / 2] = 'W';
		} else {
			board[Math.floorDiv(size, 2) - 1][Math.floorDiv(size, 2) - 1] = 'B';
			board[Math.floorDiv(size + 1, 2) - 1][Math.floorDiv(size + 1, 2) - 1] = 'B';
			board[Math.floorDiv(size + 1, 2) - 1][Math.floorDiv(size, 2) - 1] = 'W';
			board[Math.floorDiv(size, 2) - 1][Math.floorDiv(size + 1, 2) - 1] = 'W';
		}
	}
	
	public int getSize(){ //Returns the size of the array
		return size;
	}
	public char getPoint(int x, int y){ //Returns the char at a point of the array
		return board[x][y];
	}

	public void printBoard() { //Displays the board to the screen
		System.out.print(" ");
		for (int y = 0; y < size; y++) {
			System.out.print(y + 1);
		}
		System.out.println("");
		for (int x = 0; x < size; x++) {
			System.out.print(x + 1);
			for (int y = 0; y < size; y++) {
				System.out.print(board[x][y]);
			}
			System.out.println();
		}
	}

	public boolean markLegalMoves(int player) { //Finds the points that have the player color, moves to helper
		char oppColor = ' ';
		char playerColor = ' ';
		boolean changed = false;
		if (player == 1) {
			oppColor = 'W';
			playerColor = 'B';
		} else {
			oppColor = 'B';
			playerColor = 'W';
		}
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y] == 'L') {
					board[x][y] = 'X'; // Cleans the board of Ls from the last
										// turn
				}
			}
		}
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y] == playerColor) {
					markLegalMovesHelper(oppColor, playerColor, x, y);
				}
			}
		}
		for (int x = 0; x < size; x++) { //Checks if a legal move exists
			for (int y = 0; y < size; y++) {
				if (board[x][y] == 'L') {
					changed = true;
				}
			}
		}
		return changed;
	}

	private void markLegalMovesHelper(char oppColor, char playerColor,
			int x, int y) { //Searches in each direction from a point and marks the legal moves from that point
		for (int tempX = x + 1; tempX < size - 1; tempX++) { // Checks
																// horizontal,
																// increasing
			if (board[tempX][y] == playerColor || board[tempX][y] == 'X') {
				break;
			} else if (board[tempX][y] == oppColor
					&& board[tempX + 1][y] == 'X') {
				board[tempX + 1][y] = 'L';
			}
		}
		for (int tempX = x - 1; tempX > 0; tempX--) { // Checks horizontal,
														// decreasing
			if (board[tempX][y] == playerColor || board[tempX][y] == 'X') {
				break;
			} else if (board[tempX][y] == oppColor
					&& board[tempX - 1][y] == 'X') {
				board[tempX - 1][y] = 'L';
			}
		}
		for (int tempY = y + 1; tempY < size - 1; tempY++) { // Checks vertical,
																// increasing
			if (board[x][tempY] == playerColor || board[x][tempY] == 'X') {
				break;
			} else if (board[x][tempY] == oppColor
					&& board[x][tempY + 1] == 'X') {
				board[x][tempY + 1] = 'L';
			}
		}
		for (int tempY = y - 1; tempY > 0; tempY--) { // Checks
														// vertical,decreasing
			if (board[x][tempY] == playerColor || board[x][tempY] == 'X') {
				break;
			} else if (board[x][tempY] == oppColor
					&& board[x][tempY - 1] == 'X') {
				board[x][tempY - 1] = 'L';
			}
		}
	}

	public boolean takeTurn(int player, int x, int y) {
		x = x - 1;
		y = y - 1; 
		boolean valid = true;
		if (x > size || y > size) { //Checks size requirements
			System.out.println("Out of bounds");
			valid = false;
		} else if (board[x][y] == 'L') { //Checks if the position is a valid move
			if (player == 1) {
				board[x][y] = 'B';
				takeTurnHelper('B', 'W', x, y);
			} else {
				board[x][y] = 'W';
				takeTurnHelper('W', 'B', x, y);
			}
			valid = true;
		} else {
			System.out.println("Invalid move");
			valid = false;
		}
		return valid;
	}

	public void takeTurnComp(int player) { //Allows the computer to take a turn
		Random rn = new Random();
		boolean finished = false;
		do {
			int xOrY = rn.nextInt(2); //Computer randomly decides to start searching...
			if (xOrY == 0) { //...down the rows or down the columns 
				for (int x = 0; x < size; x++) {
					for (int y = 0; y < size; y++) {
						if (finished) {
							break;
						}
						if (board[x][y] == 'L') {
							if (player == 1) {
								board[x][y] = 'B';
								takeTurnHelper('B', 'W', x, y);
							} else {
								board[x][y] = 'W';
								takeTurnHelper('W', 'B', x, y);
							}
							finished = true;
						}
					}
				}
			} else {
				for (int y = 0; y < size; y++) {
					for (int x = 0; x < size; x++) {
						if (finished) {
							break;
						}
						if (board[x][y] == 'L') {
							if (player == 1) {
								board[x][y] = 'B';
								takeTurnHelper('B', 'W', x, y);
								finished = true;
							} else {
								board[x][y] = 'W';
								takeTurnHelper('W', 'B', x, y);
								finished = true;
							}
						}
					}
				}
			}
		} while (!finished);
	}

	private void takeTurnHelper(char playerColor, char oppColor, int x, int y) { //Takes the legal move and changed the board
		boolean changed = false;
		int min = 0, max = 0;
		//Starts from the extremes - 1 or + 1
		//Moves back to the main point
		//If it sees and opposing color with a player color in front of it
		//Changes that opposing color to the player color
		for (int tempX = size - 2; tempX >= x; tempX--) { // Checks
			// horizontal,
			// increasing
			if(tempX <= 0 || tempX >=size){
				if (changed) {
					min--;
				} 
				break;
			}
			if (board[tempX][y] == playerColor
					&& board[tempX - 1][y] == oppColor) {
				changed = true;
				max = tempX;
				min = max;
				System.out.println("Inside first if, tempX, y: " + tempX + " "
						+ y);
			} else if (board[tempX][y] == playerColor
					&& board[tempX + 1][y] == 'X') {
				changed = false;
			}
			if (changed) {
				min--;
			}
		}
		if(min < 0){
			min = 0;
		}
		for (; min <= max; min++) {
			if(min == 0 && max == 0){
				break;
			}
			System.out.println("Min: " + min);
			board[min][y] = playerColor;
			System.out.println("(" + min + "," + y + ")");
		}
		
		changed = false;
		for (int tempX = 1; tempX <= x; tempX++) { // Checks
			// horizontal,
			// decreasing
			if(tempX <= 0 || tempX >=size){
				if (changed) {
					min--;
				}
				break;
			}
			if (board[tempX][y] == oppColor
					&& board[tempX + 1][y] == playerColor) {
				changed = true;
				min = tempX;
				max = min;
			} else if (board[tempX][y] == playerColor
					&& board[tempX - 1][y] == 'X') {
				changed = false;
			}
			if (changed) {
				max++;
			}
		}
		if(max > size - 1){
			max = size - 1;
		}
		for (; max >= min; max--) {
			if(min == 0 && max == 0){
				break;
			}
			board[max][y] = playerColor;
		}
		changed = false;
		
		for (int tempY = size - 2; tempY >= y; tempY--) { // Checks
			// vertical,
			// increasing
			if(tempY <= 0 || tempY >=size){
				if (changed) {
					min--;
				}
				break;
			}
			if (board[x][tempY] == oppColor
					&& board[x][tempY + 1] == playerColor) {
				changed = true;
				max = tempY;
				min = max;
			} else if (board[x][tempY] == playerColor
					&& board[x][tempY - 1] == 'X') {
				changed = false;
			}
			if (changed) {
				min--;
			}
		}
		if(min < 0){
			min = 0;
		}
		for (; min <= max; min++) {
			if(min == 0 && max == 0){
				break;
			}
			board[x][min] = playerColor;
		}
		
		changed = false;
		for (int tempY = 1; tempY <= y; tempY++) { // Checks
			// vertical,
			// decreasing
			if(tempY <= 0 || tempY >=size){
				if (changed) {
					min--;
				}
				break;
			}
			if (board[x][tempY] == oppColor
					&& board[x][tempY + 1] == playerColor) {
				changed = true;
				min = tempY;
				max = min;
			} else if (board[x][tempY] == playerColor
					&& board[x][tempY - 1] == 'X') {
				changed = false;
			}
			if (changed) {
				max++;
			}
		}
		if(max > size - 1){
			max = size - 1;
		}
		for (; max >= min; max--) {
			if(min == 0 && max == 0){
				break;
			}
			board[x][max] = playerColor;
		}
	}

	public int countWinner() { //Scans the array to see who wins
		int bCount = 0, wCount = 0;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y] == 'B') {
					bCount++;
				} else if (board[x][y] == 'W') {
					wCount++;
				}
			}
		}
		if (bCount < wCount) {
			return 2; // White Wins
		} else if (wCount < bCount) {
			return 1; // Black Wins
		}
		return -1; // Tie
	}
	public String[] getCoordinates(int player){ //Gets an array of coordinates that contains all legal moves for a player
		markLegalMoves(player);
		String[]coordinates = new String[(int)Math.pow((double)getSize(), 2)];
		int count = 0;
		for(int x = 0; x < getSize(); x++){
			for(int y = 0; y < getSize(); y++){
				if(getPoint(x, y) == 'L'){
					coordinates[count] = x + "," + y;
					count++;
				}
			}
		}
		String[]returnCoordinates = new String[count];
		for(int x = 0; x < count; x++){
			returnCoordinates[x] = coordinates[x];
		}
		return returnCoordinates;
	}
}
