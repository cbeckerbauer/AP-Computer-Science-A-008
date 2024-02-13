public class Grid {
	
	/**
	 * Creates a string matrix for the grid 
	 * Creates a class integer value holder for the amount of rows
	 */
	
	public String[][]grid;
	private int row;
	
	/**
	 * @param row : The amount of rows that the player wants
	 * @param col : The amount of columns that the player wants
	 * Creates a new grid by creating a string matrix with the desired amount of rows and columns that the player wants and it adds 2 more to the row count to have room for the formated first and last lines
	 * Sets the class row value to the value of the @param row
	 * Follows up by printing the entire grid with the function printGrid and has the @param of true as it is the first time that it is printing the grid
	 */
	
	public Grid(int row, int col) {
		grid = new String[row + 2][col];
		this.row = row;
		printGrid(true);
	}
	
	/**
	 * @param first : Is only true if it is the first time that the grid is being printed
	 * For loop to increment through every row and column in the grid
	 * If it is the first row then it numbers it and makes that the values of that row per column and then prints it with a certain format of being divided by "|"
	 * If it is the last row then it adds a row that forms a bottom line for aesthetic purposes of the grid and then prints it with a certain format of just straight "-"
	 * If it is the first time that this function is being run it sets everything to a "null" with a "~" and then prints it with a certain format of being divided by "|"
	 * Else it just prints the entire grid with a certain format of being divided by "|"
	 * After it iterates through the if loop that it satisfies it follows it up by starting a new line
	 */
	
	public void printGrid(boolean first) {
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				if(r == 0) {
					System.out.print("|");
					grid[r][c] = (c + 1) + "";
					System.out.print(grid[r][c] + "|");
				}
				else if(r == (row + 1)) {
					grid[r][c] = "-";
					System.out.print("-" + grid[r][c] + "-");
				}
				else if(first) {
					System.out.print("|");
					grid[r][c] = "~";
					System.out.print(grid[r][c] + "|");
				}
				else {
					System.out.print("|");
					System.out.print(grid[r][c] + "|");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * @param player : Is only true if the player is placing their piece
	 * @param col : Is the column location the player or computer is trying to place on the grid 
	 * Creates a string piece that will be the player or computer piece 
	 * The piece is automatically set to "X" whether or not it is the player playing or the computer
	 * If the @param player is false it starts off printing the title of "Computer Play: " to let the player know who's move is going to be printed when the grid is printed
	 * If the @param player is false then the computer is playing and it resets the piece value to "O"
	 * If the first row at the requested column is not free (Doesn't have a null in it as a "~"; Has another value "X" or "O" in that location) then it returns false 
	 * If the first row at the requested column is free (Has a null in it as a "~") then it sets that location on the grid to the piece
	 * Using a for loop to iterate down the rows
	 * If the row below at the requested column is free (Has a null in it as a "~") 
	 * Then it sets that location on the grid to the piece and sets the previous location to null as a "~"
	 * If there is something (Doesn't have a null in it as a "~"; Has another value "X" or "O" in that location) it just breaks the for loop
	 * Prints the grid and uses the @param of false as it is not the first time that it is printing the grid
	 * @return false if there is something (Doesn't have a null in it as a "~"; Has another value "X" or "O" in that location) in the requested location
	 * @return true if it was able to place the piece on the grid
	 */
	
	public boolean dropPiece(boolean player, int col) {
		String piece = "X";
		if(!player) {
			piece = "O";
		}
		if(grid[1][col] != "~") {
			return false;
		}
		else {
			if(piece == "O") {
				System.out.println("Computer Play: ");
			}
			grid[1][col] = piece; 
		}
		for(int i = 2; i < grid.length; i++) {
			if(grid[i][col] == "~") {
				grid[i][col] = piece;
				grid[i-1][col] = "~";
			}
			else {
				break;
			}
		}
		printGrid(false);
		return true;
	}
	
	/**
	 * @param col is the column location that the requested location is at 
	 * Checks if the requested location is empty or full
	 * @return true if the location is full with a piece (A "X" or "O")
	 * @return false if the location is empty
	 */
	
	public boolean isItFull(int col) {
		if(grid[1][col] != "~") {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param row of the requested location
	 * @param col of the requested location
	 * @return the piece at the specific row and column 
	 */
	
	public String getPiece(int row, int col) {
		return grid[row][col];
	}
	
}
