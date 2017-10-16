package dkeep.logic;

public class Hero extends GameObject {

	private boolean hasSword = false;
	public boolean isFree = false;
	public boolean isDead = false;
	
	public Hero(char symbol) {
		setSymbol(symbol);
	}
	
	public void killHero(GameMain game){
		this.isDead = true;
		this.transformObject(game, this, '-');
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
	
	private void moveHero(GameMain game, int x, int y, int x_old, int y_old){
		//Check if there is a Dragon waiting...
		
		if(game.map.nearDragon(x,y)){
			//Check if hero has sword
			if(this.hasSword) game.killDragonsNearPosition(x,y);
			else {
				this.isDead = true; //Is eaten by dragon - GAME OVER!
				transformObject(game,this,'-');
			}
			
		}
			switch (game.map.getObjectOnLocation(x,y)){
				case 'X':   //Hits a wall!
							break;		
							
				case 'E': 	//If hero slained all dragons he can exit - GAME COMPLETED!
							if(game.allDragonSlained()) this.isFree = true;
							break;
							
				case 'S':	//Gets the key!
							this.hasSword = true;
							this.transformObject(game,this,'A');
							
				default: 	this.setX(x);
							this.setY(y);
							game.map.setObjectOnLocation(this,x,y);
							game.map.ClearScreenLocation(x_old, y_old);
							break;
			}
		
	}
	
	public void moveLeft(GameMain game) {
		moveHero(game,this.getX()-1,this.getY(),this.getX(),this.getY());
	}
	
	public void moveRight(GameMain game) {
		moveHero(game,this.getX() + 1,this.getY(),this.getX(),this.getY());
	}
	
	public void moveUp(GameMain game) {
		moveHero(game,this.getX(),this.getY()-1,this.getX(),this.getY());
	}
	
	public void moveDown(GameMain game) {
		moveHero(game,this.getX(),this.getY()+1,this.getX(),this.getY());
	}
}
