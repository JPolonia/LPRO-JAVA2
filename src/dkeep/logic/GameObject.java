package dkeep.logic;


public class GameObject {
	
	private int x, y;
	private char symbol;
	
	// Getters
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public char getSymbol() {
		return symbol;
	}
	
	// Setters
	public void setX(int newLocation) {
		this.x = newLocation;
	}
	
	public void setY(int newLocation) {
		this.y = newLocation;
	}
	
	public void setSymbol(char newSymbol) {
		this.symbol = newSymbol;
	}
	
	/*public void moveUp() {
		this.setY(this.getY()-1);
	}
	public void moveDown() {
		this.setY(this.getY()+1);
	}
	public void moveRight() {
		this.setY(this.getX()+1);
	}
	public void moveLeft() {
		this.setY(this.getX()-1);
	}*/
	
	public boolean nearDragonAwake(Dragon dragon[]) {
		for(int i = 0; i<dragon.length;i++){
			if (dragon[i].isAwake && (this.getX()-1 == dragon[i].getX()) && this.getY() == dragon[i].getY()) return true; //Left
			if (dragon[i].isAwake && (this.getX()+1 == dragon[i].getX()) && this.getY() == dragon[i].getY()) return true; //Right
			if (dragon[i].isAwake && (this.getY()-1 == dragon[i].getY()) && this.getX() == dragon[i].getX()) return true; //Up
			if (dragon[i].isAwake && (this.getY()+1 == dragon[i].getY()) && this.getX() == dragon[i].getX()) return true; //Down
		}
		return false;
	}
		
	public boolean nearObject(GameObject obj) {
		if ((this.getX()-1 == obj.getX()) && this.getY() == obj.getY()) return true; //Left
		if ((this.getX()+1 == obj.getX()) && this.getY() == obj.getY()) return true; //Right
		if ((this.getY()-1 == obj.getY()) && this.getX() == obj.getX()) return true; //Up
		if ((this.getY()+1 == obj.getY()) && this.getX() == obj.getX()) return true; //Down
		return false;
	}
		

}
