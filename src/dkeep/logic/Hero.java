package dkeep.logic;

public class Hero extends GameObject {

	private boolean hasKey = false;
	private boolean hasSword = false;
	public boolean isFree = false;
	public boolean isDead = false;
	
	public Hero(char symbol) {
		setSymbol(symbol);
	}
	
	public void addRandomLocation(GameMap screen) {
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
		screen.setObjectOnLocation(this,x,y);
	}
	
	private void moveHero(GameMap screen, int x, int y, int x_old, int y_old){
		//Check if there is a Dragon waiting...
		
		if(screen.nearDragon(x,y)){
			//Is eaten by dragon - GAME OVER!
			this.isDead = true;
			transformHero(screen,this,'-');
			//screen.PrintGameOver();
		}
			switch (screen.getObjectOnLocation(x,y)){
				case 'X':   //Hits a wall!
							break;		
							
				case 'E': 	//If he has the key he can exit - GAME COMPLETED!
							if(this.hasKey) {this.isFree = true;/*screen.PrintGameCompleted()*/;transformHero(screen,this,'o');}
							else {/*screen.PrintExitClose()*/;break;}
							
				case 'S':	//Gets the key!
							this.hasSword = true;
							
				default: 	this.setX(x);
							this.setY(y);
							screen.setObjectOnLocation(this,x,y);
							screen.ClearScreenLocation(x_old, y_old);
							break;
			}
		
	}
	
	public void moveLeft(GameMap screen) {
		moveHero(screen,this.getX()-1,this.getY(),this.getX(),this.getY());
	}
	
	public void moveRight(GameMap screen) {
		moveHero(screen,this.getX() + 1,this.getY(),this.getX(),this.getY());
	}
	
	public void moveUp(GameMap screen) {
		moveHero(screen,this.getX(),this.getY()-1,this.getX(),this.getY());
	}
	
	public void moveDown(GameMap screen) {
		moveHero(screen,this.getX(),this.getY()+1,this.getX(),this.getY());
	}
	
	public void transformHero(GameMap screen,Hero hero, char symbol) {
		screen.clearHero(hero,symbol);
		hero.setSymbol(symbol);
	}
}
