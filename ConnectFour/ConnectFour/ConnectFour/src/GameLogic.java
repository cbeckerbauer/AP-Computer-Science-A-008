import java.util.Scanner;

public class GameLogic {

	/**
	 * Creates a new scanner to read the responses of the player 
	 * Creates a new grid for the game 
	 * Creates a boolean that is used to determine if there is a winning solution or not.
	 */

	private Scanner scan;
	private Grid grid;
	private boolean win;

	/**
	 * Creates the new scanner and makes sure it reads the player input 
	 * Initially sets the win to false to make sure there is no false wins
	 */

	public GameLogic() {
		scan = new Scanner(System.in);
		win = false;
	}

	/**
	 * @param maxCol is the total amount of columns that are possible within the grid 
	 * Asks the player for the column that they want to place their piece in 
	 * Reads the responses and sets it to the string response
	 * Transfers the response from a String into a integer which allows the grid to read it 
	 * Sets the answer to the new integer created from the string
	 * @return the answer of the column where the player wants their piece to play
	 */

	public int getCol(int maxCol) {
		System.out.println("What column do you drop your piece: " + (maxCol));
		String response = scan.nextLine();
		int answer = Integer.parseInt(response) - 1;
		return answer;
	}

	/**
	 * Asks the player for the amount of rows they want to play with 
	 * Reads the responses and sets it to the string response 
	 * Transfers the response from a String into a integer which allows the grid to read it 
	 * Sets the answer to the new integer created from the string
	 * @return the answer of the amount of rows the player wants to play with
	 */

	public int getGridRow() {
		System.out.println("What is the max number of row(s) do you want?");
		String response = scan.nextLine();
		int answer = Integer.parseInt(response);
		return answer;
	}

	/**
	 * Asks the player for the amount of columns they want to play with 
	 * Reads the responses and sets it to the string response 
	 * Transfers the response from a String into a integer which allows the grid to read it 
	 * Sets the answer to the new integer created from the string
	 * @return the answer of the amount of columns the player wants to play with
	 */

	public int getGridCol() {
		System.out.println("What is the max number of column(s) do you want?");
		String response = scan.nextLine();
		int answer = Integer.parseInt(response);
		return answer;
	}

	/**
	 * Sets the amount of rows that the player wants to play with to a integer 
	 * Sets the amount of columns that the player wants to play with to a integer 
	 * Creates a new grid with the width (determined by the amount of requested columns) and the height (determined by the amount of requested rows) 
	 * Calls the plays function with the parameters of the amount of requested columns and the amount of requested rows
	 */

	public void run() {
		int requestedRow = getGridRow();
		int requestedCol = getGridCol();
		grid = new Grid(requestedRow, requestedCol);
		plays(requestedCol, requestedRow);
	}

	/**
	 * @param maxCol is the total amount of columns in the entire grid
	 * @param maxRow is the total amount of rows in the entire grid 
	 * Sets the current column to 0 
	 * While the win is false (there is no winning solution yet) 
	 * Sets the current column to the value gained from the getCol function where it asks the player what column they want to place their piece in 
	 * If the player's piece is able to be played and is placed in the grid 
	 * It calls the checkWin function that checks if there is a winning solution 
	 * That function will return true or false depending on if there is a winning solution or not 
	 * If the winning condition is found then the function end will be called on the player 
	 * If there is not a winning condition yet then it gets the computer play 
	 * This function will place the computer's piece on the grid 
	 * It calls the checkWin function that checks if there is a winning solution 
	 * That function will return true or false depending on if there is a winning solution or not 
	 * If the winning condition is found then the function end will be called on the computer
	 * If there is not a winning condition yet then it continues in the while loop and the player plays again
	 */

	public void plays(int maxCol, int maxRow) {
		int col = 0;
		while (!win) {
			col = getCol(maxCol);
			if (grid.dropPiece(true, col) == true) {
				win = checkWin(maxRow, maxCol);
				if(!win) {
					getComputerPlays(maxCol, col, maxRow);
					checkWin(maxRow, maxCol);
				}
			}
		}
	}

	/**
	 * @param maxCol is the total amount of columns in the grid
	 * @param col is the current row that the player played at
	 * @param maxRow is the total amount of rows in the grid Creates a integer and sets it to the player's location that they played last at
	 * Subtracts one from the total amount of columns in the grid to eliminate the idea of an extra row and makes the condition below possible 
	 * If the computer is not able to drop a piece on top of the player's last piece it checks if the column that it is playing at is equal to the total amount of columns in the grid minus one 
	 * It will then print to let the player know that the computer recognizes that there is not a possible move on top of their piece 
	 * If the grid has a piece to the left the computer lets the player know it can't move by printing the message: Computer cannot move 
	 * It calls the checkWin function that checks if there is a winning solution 
	 * That function will return true or false depending on if there is a winning solution or not 
	 * If the winning condition is found then the function end will be called on the computer 
	 * If there is not a winning solution found then it will just break the loop 
	 * If there is not a piece in the column to the left then it will place the computer's piece in the left 
	 * If the column that it is playing is not equal to the total amount of columns in the grid it move on
	 * If the computer is not able to drop a piece on top of the player's last piece it checks if the column that it is playing at is equal to 0 
	 * It will then print to let the player know that the computer recognizes that there is not a possible move on top of their piece 
	 * If the grid has a piece to the left the computer lets the player know it can't move by printing the message: Computer cannot move 
	 * It calls the checkWin function that checks if there is a winning solution 
	 * That function will return true or false depending on if there is a winning solution or not 
	 * If the winning condition is found then the function end will be called on the computer 
	 * If there is not a winning solution found then it will just break the loop 
	 * If there is not a piece in the column to the left then it will place the computer's piece in the right
	 * If the column that it is playing is not equal to 0 it move on
	 * If the grid has pieces on either side of the player's piece and those columns are full then it lets the player know
	 * It then increments through and tries to move right or left and will let the player know which way it went 
	 * If it cannot move left or right it will let the player know
	 * If the grid doesn't have pieces on either side of the player's piece and those columns are not full then it will move on
	 * If the column to left is full then it lets the player know
	 * It then increments through and tries to move right unless it cannot then it will move left and will let the player know which way it went 
	 * If it cannot move left or right it will let the player know
	 * If the grid does not have a full column to the left then it will move on
	 * If the column to right is full then it lets the player know
	 * It then increments through and tries to move left unless it cannot then it will move right and will the let the player know which way it went
	 * If it cannot move left or right it will let the player know
	 * If the grid does not have  full column to the right then it will move on
	 * If none of these conditions are satisfied then it will just place the computer's piece as normal
	 */

	public void getComputerPlays(int maxCol, int col, int maxRow) {
		int computerCol = col;
		maxCol = maxCol - 1;
		if (grid.dropPiece(false, computerCol) == false) {
			if (computerCol == maxCol) {
				System.out.println("Max Col");
				if (grid.isItFull(col - 1)) {
					System.out.println("Computer cannot move.");
					checkWin(maxRow, maxCol);
				} else {
					grid.dropPiece(false, computerCol - 1);
				}
			} else if (computerCol == 0) {
				System.out.println("Zero");
				if (grid.isItFull(col + 1)) {
					System.out.println("Computer cannot move.");
					checkWin(maxRow, maxCol);
				} else {
					grid.dropPiece(false, computerCol + 1);
				}
			} else if (grid.isItFull(col - 1) && grid.isItFull(col + 1)) {
				System.out.println("Both rows are full");
				for (int i = 0; i < grid.grid.length; i++) {
					if (grid.isItFull(col + i) == false) {
						System.out.println("I moved right");
						grid.dropPiece(false, col + 1);
					} else if (grid.isItFull(col - i) == false) {
						System.out.println("I moved left");
						grid.dropPiece(false, col - 1);
					} else {
						System.out.println("Computer cannot move");
						checkWin(maxRow, maxCol);
					}
				}
			} else if (grid.isItFull(col - 1)) {
				System.out.println("Left Full");
				if (grid.isItFull(col - 1) && grid.isItFull(col)) {
					for (int i = 0; i < grid.grid.length; i++) {
						if (grid.isItFull(col + i) == false) {
							System.out.println("I moved right");
							grid.dropPiece(false, col + 1);
						} else if (grid.isItFull(col - i) == false) {
							System.out.println("I moved left");
							grid.dropPiece(false, col - 1);
						} else {
							System.out.println("Computer cannot move");
							checkWin(maxRow, maxCol);
						}
					}
				} else if (grid.isItFull(col - 1) == false) {
					grid.dropPiece(false, col - 1);
				} else if (grid.isItFull(col + 1) == false) {
					grid.dropPiece(false, col + 1);
				} else {
					grid.dropPiece(false, computerCol + 1);
				}
				checkWin(maxRow, maxCol);
			} else if (grid.isItFull(col + 1)) {
				System.out.println("Right Full");
				if (grid.isItFull(col + 1) && grid.isItFull(col)) {
					for (int i = 0; i < grid.grid.length; i++) {
						if (grid.isItFull(col + i) == false) {
							System.out.println("I moved right");
							grid.dropPiece(false, col + 1);
						} else if (grid.isItFull(col - i) == false) {
							System.out.println("I moved left");
							grid.dropPiece(false, col - 1);
						} else {
							System.out.println("Computer cannot move");
							checkWin(maxRow, maxCol);
						}
					}
				} else if (grid.isItFull(col - 1) == false) {
					grid.dropPiece(false, col - 1);
				} else if (grid.isItFull(col + 1) == false) {
					grid.dropPiece(false, col + 1);
				} else {
					grid.dropPiece(false, computerCol + 1);
				}
			}
		}
	}

	/**
	 * @param maxRow is the total amounts of rows in the grid
	 * @param maxCol is the total amount of columns in the grid
	 * Sets the win count to 0 initially
	 * Sets the piece to blank
	 * If the maxCol is less then four then it sets the win count to 3 meaning a player only needs to get 3 in a row to win
	 * If the maxCol is greater than four and less then 7 then it sets the win count to 4 meaning a player only needs to get 4 in a row to win
	 * If the maxCol is greater than seven then it sets the win count to 5 meaning a player only needs to get 5 in a row to win
	 * Sets up a nested for loop to check every piece on the board 
	 * If the piece is a piece played by the player (X) or (O) then it continues on
	 * Sets the piece to the location that the piece that it just checked on 
	 * Sets up a nested for loop that checks if there are any imitate neighboring characters of the same type
	 * Checks if the nearby space is in the bounds of the grid 
	 * Checks if the piece is the same as the old one before it 
	 * If the piece is the same and it is a new one then it checks more in that direction
	 * Sets the number in the row to zero
	 * Keeps checking in that direction and then checks if the spot exists 
	 * If it is the same as the piece then it increments the number in the row up
	 * If the number in the row is equal to or greater than the win count needed then it calls the end function
	 * @return true if the end function is called
	 * @return false otherwise
	 */

	public boolean checkWin(int maxRow, int maxCol) {
		int winCount = 0;
		String piece = "";
		if (maxCol <= 4) {
			winCount = 3;
		} else if (maxCol <= 7 && maxCol > 4) {
			winCount = 4;
		} else if (maxCol > 7) {
			winCount = 5;
		}
		for(int row = 0; row < maxRow + 1; row++) {
            for(int col = 0; col < maxCol; col++) {
                if(grid.getPiece(row, col) == "X" || grid.getPiece(row, col) == "O") {
                    piece = grid.getPiece(row, col);
                    for(int r = -1; r < 2; r++) {
                        for(int c= -1; c < 2; c++) {
                            if(row+r >=0 && row+r < maxRow + 1 && col+c >= 0 && col+c < maxCol) {
                                if(grid.getPiece(row+r, col+c) == piece && !(r == 0 && c == 0)) {
                                    int numInARow = 0;
                                    for(int i=0; i<winCount; i++) {//keep checking in that direction
                                        if(row+(i*r) >=0 && row+(i*r) < maxRow + 1 && col+(i*c) >= 0 && col+(i*c) < maxCol) {//check if the spot exists
                                            if(grid.getPiece(row + (i*r), col + (i*c)) == piece) {//if it is the same
                                                numInARow++;//we found another
                                            }
                                        }
                                    }
                                    if(numInARow >= winCount) {
                                        end(piece);
                                        return true;
                                    }
                                }
                            }
                        }
                        
                    }
                }
            }
        
        }
		return false;
	}

	/**
	 * @param piece is the piece that is either the player or the computer's win
	 * Sets the number of times the computer has won to zero
	 * Sets the number of times the player has won to zero
	 * If the piece is the player's then it increments the amount of times the player won up one and asks if they would like to play again 
	 * Prints the amount of times the player has won
	 * Calls the run function to reset the game
	 * If the piece is the computer's then it increments the amount of times the computer won up one and asks if the player would like to play again
	 * Prints the amount of times the computer has won
	 * Calls the run function to reset the game
	 */

	public void end(String piece) {
		int winComputer = 0;
		int winPlayer = 0;
		if (piece == "X") {
			winPlayer++;
			System.out.println("Congrats! Would you like to play again?");
			System.out.println(winPlayer);
			run();
		} else {
			winComputer++;
			System.out.println("So sad, you lost? Would you like to play again?");
			System.out.println(winComputer);
			run();
		}
	}

}