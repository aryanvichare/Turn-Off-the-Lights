
public class TurnOffTheLights {
	private boolean gameOver; // flag to record if the game is over
	private int playerTurn; // whose turn it is
	private int winner; // who the winner is (0 if no winner)
	private int cols, rows; // # of rows and columns in game
	private int[][] grid; // the grid that stores the pieces

	// The constructor initializes the game
	public TurnOffTheLights(int r, int c) {
		// Create the board
		this.cols = c;
		this.rows = r;
		grid = new int[r][c];

		// Initialize starting positions
		grid[0][0] = 1;
		grid[grid.length - 1][0] = 1;
		grid[0][grid[0].length - 1] = 1;
		grid[grid.length - 1][grid[0].length - 1] = 1;

		// Set that the game is not over
		gameOver = false;
	}

	/*
	 * Return true if r, c is a valid move for the game.
	 */
	public boolean isValidMove(int r, int c) {
		if (isInGrid(r, c) == false) // if outside grid, not valid
			return false;

		return true; // otherwise it's valid
	}

	/*
	 * Return true if the location at row, col is in the bounds of the grid.
	 * Return false otherwise.
	 */
	public boolean isInGrid(int row, int col) {
		if(row < 0 || row >= grid.length) return false; 
		if(col < 0 || col >= grid[0].length) return false; 
		return true;
	}

	/*
	 * Return true if the location l is in the bounds of the grid. Note: this
	 * method calls the other isInGrid to do the work.
	 */
	public boolean isInGrid(Location l) {
		return isInGrid(l.getRow(), l.getCol());
	}

	// makes the move
	// returns false if no move was made, true if the move was successful.
	public boolean move(int r, int c) {
		if (isValidMove(r, c) == false)
			return false; // if not valid, exit
		if (gameOver == true)
			return false; // if game is over, exit
		
		turnOnNeighboringLights(r, c); // We can only turn off the blue lights 

		// check for the winner
		gameOver = checkForWinner();

		return true; // this means the move was successfully made
	}

	/*
	 * Return true if the game is over. False otherwise.
	 */
	private boolean checkForWinner() {
		// Loop through all locations. If you see a light on, return false because
		// the game isn't over.
		
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if(grid[row][col] == 1) return false; 
			}
		}
		return true;
	}
	
	public void toggle(int r, int c) {
		if(grid[r][c] == 1) grid[r][c] = 0;
		else grid[r][c] = 1;
	}
	
	public void turnOnNeighboringLights(int r, int c) {
		// Get all four locations 
		int left = r-1;
		int right = r+1;
		int up = c-1;
		int down = c+1;
		
		toggle(r, c);
		if(isValidMove(left, c)) toggle(left, c);
		if(isValidMove(right, c)) toggle(right, c);
		if(isValidMove(r, up)) toggle(r, up);
		if(isValidMove(r, down)) toggle(r, down);
		
	}
	
	public boolean isGameOver() {
		return gameOver;
	}

	public int[][] getGrid() {
		return grid;
	}
}