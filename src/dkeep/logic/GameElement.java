package dkeep.logic;


public class GameElement extends GameObject {

	public GameElement(char symbol) {
		setSymbol(symbol);
	}

	// Add food to random location inside the matrix limits
	public void addRandomGameElement(GameMap screen, GameElement elem) {
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
		
		screen.setObjectOnLocation(elem,x,y);
	}
	
	
}
