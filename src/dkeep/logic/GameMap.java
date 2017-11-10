package dkeep.logic;


public class GameMap {

	private int width, height;
	private char[][] screenMatrix;

	public GameMap(int width, int height,char[][] screenMatrix) {
		this.width = width;
		this.height = height;
		this.screenMatrix = screenMatrix;
	}
			
	// Getters
	public int getScreenWidth() {
		return this.width;
	}

	public int getScreenHeight() {
		return this.height;
	}
	
	public char[][] getScreenMatrix() {
		return this.screenMatrix;
	}

	public char getObjectOnLocation(int x, int y) {
		return this.screenMatrix[y][x];
	}
	
	// Setters
	public void ClearScreenLocation(int x, int y) {
		this.screenMatrix[y][x] = ' ';
	}
	
	public void setObjectOnLocation(GameObject object, int x, int y) {
		this.screenMatrix[y][x] = object.getSymbol();
	}

	
	// Utilities
	public boolean nearDragon(int x, int y) {
		if (x>0 && this.screenMatrix[y][x-1] == 'D') return true;
		if (x<9 && this.screenMatrix[y][x+1] == 'D') return true;
		if (y<9 && this.screenMatrix[y+1][x] == 'D') return true;
		if (y>0 && this.screenMatrix[y-1][x] == 'D') return true;
		return false;
	}
	
	public boolean locationIsValid(int x, int y) {
		//Check if there is already an object
		if (this.screenMatrix[y][x] != ' ' ) return false;
		
		//Check if it is near Dragon
		if (nearDragon(x,y)) return false;
		
		return true;
	}
	
	public boolean locationDragonValid(int x, int y) {
		if (this.screenMatrix[y][x] != ' ' ) return false;
		return true;
	}
	
	public boolean moveValid(int x, int y) {
		if (this.screenMatrix[y][x] == 'X' || this.screenMatrix[y][x] == 'D') return false;
		return true;
	}

	public boolean nearObject(int x, int y,GameObject object ) {
		// TODO Auto-generated method stub
		if (x>0 && this.screenMatrix[y][x-1] == object.getSymbol()) return true;
		if (x<9 && this.screenMatrix[y][x+1] == object.getSymbol()) return true;
		if (y<9 && this.screenMatrix[y+1][x] == object.getSymbol()) return true;
		if (y>0 && this.screenMatrix[y-1][x] == object.getSymbol()) return true;
		return false;
	}
}
