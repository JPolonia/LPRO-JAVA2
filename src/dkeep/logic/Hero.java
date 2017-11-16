package dkeep.logic;

public class Hero extends GameObject{

	public boolean hasSword = false;
	public boolean isFree = false;
	public boolean isDead = false;
	
	public Hero(char symbol,GameMap map, Dragon dragon[]) {
		setSymbol(symbol);
		addRandomLocation(map,dragon);
	}
	
	public Hero(char symbol,int x, int y) {
		setSymbol(symbol);
		this.setX(x);
		this.setY(y);
	}
	
		
	public void killHero(){
		this.isDead = true;
		this.setSymbol('-');
	}
	
	public void addRandomLocation(GameMap map, Dragon dragon[]) {
		int x =0,y =0;
		boolean valid = false;
		//check if valid position
		while(!valid){
			//Generate random positions
			x = (int) (Math.random() * (map.getScreenWidth() - 1));
			y = (int) (Math.random() * (map.getScreenHeight() - 1));
			//Check if position is valid
			valid = map.locationIsValid(x,y);
			if(valid){
				this.setX(x);
				this.setY(y);
				valid = this.nearDragonAwake(dragon) ? false : true;
			}
		}
	}
	
	private void moveHero(GameMain game, int x, int y, int x_old, int y_old){
		//Check if there is a Dragon waiting...
		switch (game.map.getObjectOnLocation(x,y)){
			case 'X':   break; //Hits a wall!
						
			case 'D':	if(this.hasSword){
							this.setX(x);
							this.setY(y);
							game.getDragonOnPosition(x,y).killDragon();
							System.out.println("DRAGON KILLED!!");
						}
						break;
						
			case 'd':  	if(this.hasSword){
							game.getDragonOnPosition(x,y).killDragon();
							System.out.println("SLEEPY DRAGON KILLED!!");
						}
						break;
						
			case 'E': 	if(game.allDragonSlained()  && game.hero.hasSword) this.isFree = true; //If hero slained all dragons he can exit - GAME COMPLETED!
						break;
						
			case 'S':	this.hasSword = true; //Gets the key!
						this.setSymbol('A');
						game.sword.setSymbol('o');
						
			default: 	this.setX(x);
						this.setY(y);
						break;
		}
		
		game.fightHeroNearDragons();
		
	}
	
	public void moveL(GameMain game) {
		moveHero(game,this.getX()-1,this.getY(),this.getX(),this.getY());
	}
	
	public void moveR(GameMain game) {
		moveHero(game,this.getX() + 1,this.getY(),this.getX(),this.getY());
	}
	
	public void moveU(GameMain game) {
		moveHero(game,this.getX(),this.getY()-1,this.getX(),this.getY());
	}
	
	public void moveD(GameMain game) {
		moveHero(game,this.getX(),this.getY()+1,this.getX(),this.getY());
	}

	public void move(GameMain game) {
		// TODO Auto-generated method stub
		
	}
}
