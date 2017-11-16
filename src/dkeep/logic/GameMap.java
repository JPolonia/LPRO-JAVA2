package dkeep.logic;




public class GameMap {

	private int width, height;
	public char[][] maze; //Saves Maze configuration: Constant!
	public char[][] board; //Saves game board with all elements

	public GameMap(int width, int height,char[][] matrix) {
		this.width = width;
		this.height = height;
		
		this.maze = new char[matrix.length][];
		this.board = new char[matrix.length][];
		for(int i = 0; i < matrix.length; i++){
			maze[i] = matrix[i].clone();
			board[i] = matrix[i].clone();
		}
	}
	
	public void GenerateBoard(){
		for(int i = 0; i < maze.length; i++) {
	    	this.board[i] = (char[]) maze[i].clone();
	    }
	}
	
	
			
	// Getters
	public int getScreenWidth() {
		return this.width;
	}

	public int getScreenHeight() {
		return this.height;
	}
	
	public char[][] getMaze() {
		return this.maze;
	}
	
	public char[][] getBoard() {
		return this.board;
	}

	public char getObjectOnLocation(int x, int y) {
		return this.board[y][x];
	}
	
	// Setters
	public void ClearScreenLocation(int x, int y) {
		this.board[y][x] = ' ';
	}
	
	public void setObjectOnLocation(GameObject object, int x, int y) {
		this.board[y][x] = object.getSymbol();
	}

	
	// Utilities
	public boolean locationIsValid(int x, int y) {
		if (this.board[y][x] != ' ' ) return false;
		return true;
	}
	
	public boolean moveValid(int x, int y) {
		if (this.board[y][x] == 'X') return false;
		return true;
	}
}
