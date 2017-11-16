package dkeep.logic;

public class Dragon extends GameObject {

	public boolean isAlive;
	public boolean isAwake;
	public DragonMovement dragonMovement;

	
	public Dragon(char symbol,int strategy,GameMap map) {
		setSymbol(symbol);
		this.isAwake = true;
		this.isAlive = true;
		if(strategy==2){
			dragonMovement = new SleepyMovement(this);
		}else{
			dragonMovement = new RandomMovement();
		}	
		this.addRandomLocation(map);
	}
	
	public Dragon(char symbol,int strategy, int x, int y) {
		setSymbol(symbol);
		this.isAwake = true;
		this.isAlive = true;
		if(strategy==2){
			dragonMovement = new SleepyMovement(this);
		}else{
			dragonMovement = new RandomMovement();
		}
		this.setX(x);
		this.setY(y);
	}
	
	// Setters
	public void killDragon(){
		this.isAlive = false;
		this.setSymbol('-');	
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
			valid = map.locationIsValid(x,y);
		}
		this.setX(x);
		this.setY(y);
	}
	
	public void moveDrake(GameMain game){
		dragonMovement.move(game, this);
	}
	
	public void wakeUpDragon(){
		this.isAwake = true;
		this.setSymbol('D');
	}
	public void sleepDragon(){
		this.isAwake = false;
		this.setSymbol('d');
	}
	
	
	// Getters
}
