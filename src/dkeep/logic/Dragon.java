package dkeep.logic;

public class Dragon extends GameObject {

	public boolean isAlive;
	
	public Dragon(char symbol) {
		setSymbol(symbol);
		this.isAlive = true;
	}
	
	public void addRandomLocation(GameMap map) {
		int x =0,y =0;
		boolean valid = false;
		//check if valid position
		while(!valid){
			//Generate random positions
			x = (int) (Math.random() * (map.getScreenWidth() - 1));
			y = (int) (Math.random() * (map.getScreenHeight() - 1));
			//Check if position is valid
			valid = map.locationDragonValid(x,y);
		}
		this.setX(x);
		this.setY(y);
		map.setObjectOnLocation(this,x,y);
	}
	
	
}
