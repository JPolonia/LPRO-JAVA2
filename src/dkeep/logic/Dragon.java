package dkeep.logic;

public class Dragon extends GameObject {

	public boolean isDead = false;
	
	public Dragon(char symbol) {
		setSymbol(symbol);
	}
	
	public void addRandomLocation(GameMap screen, Dragon hero) {
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
		hero.setX(x);
		hero.setY(y);
		screen.setObjectOnLocation(hero,x,y);
	}
	
	
}
