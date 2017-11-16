package dkeep.logic;


public class GameElement extends GameObject {

	public GameElement(char symbol, GameMap map) {
		setSymbol(symbol);
		addRandomGameElement(map);
	}

	public GameElement(char symbol, int x, int y) {
		setSymbol(symbol);
		this.setX(x);
		this.setY(y);
	}

	// Add food to random location inside the matrix limits
	public void addRandomGameElement(GameMap screen) {
		int x =0,y =0;
		boolean valid = false;
		//check if valid position
		while(!valid){
			//Generate random positions
			x = (int) (Math.random() * (screen.getScreenWidth() - 1));
			y = (int) (Math.random() * (screen.getScreenHeight() - 1));
			//Check if position is valid
			valid = screen.locationIsValid(x,y);
		}
		this.setX(x);
		this.setY(y);
		//screen.setObjectOnLocation(this,x,y);
	}
	
	
}
