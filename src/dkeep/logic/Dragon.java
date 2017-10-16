package dkeep.logic;

public class Dragon extends GameObject {

	public boolean isAlive;
	
	public Dragon(char symbol) {
		setSymbol(symbol);
		this.isAlive = true;
	}
	
	// Setters
	public void killDragon(GameMain game){
		this.isAlive = false;
		game.map.ClearScreenLocation(this.getX(), this.getX());
		
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
	
	public void moveDragon(GameMain game){
		int x_old,y_old,x,y,move;
		boolean valid = false;
		
		x_old = this.getX();
		y_old = this.getY();
		x = 0;
		y = 0;
		move = 0;
		
		//check if valid position 1-left, 2-right, 3-up, 4-down
		while(!valid){
			//Generate random positions Math.random() * (max - min) + min
			move = (int) (Math.random() * (4-1) + 1);
			//Check if position is valid
			switch(move){
				case 1: x = x_old - 1;
						y = y_old;
						break;
				case 2: x = x_old + 1;
						y = y_old;
						break;
				case 3: x = x_old;
						y = y_old - 1;
						break;
				case 4: x = x_old;
						y = y_old + 1;
						break;
			}
			valid = game.map.moveValid(x,y);
			//System.out.println("CHEGEI");
		}
				
		switch (game.map.getObjectOnLocation(x,y)){
			case 'X':	break;
			case 'E':	break;
			case 'A':	//Dragon dies
						this.killDragon(game);
						break;
			case 'H':	//Hero dies
						game.hero.killHero(game);
						break;
			
			case ' ':   if(this.getSymbol() == 'F'){ 
							game.map.setObjectOnLocation(game.sword,x_old,y_old); //Put sword back
							this.transformObject(game,this,'D'); //if is not asleep
						}else 
							game.map.ClearScreenLocation(x_old, y_old);
						this.setX(x);
						this.setY(y);
						game.map.setObjectOnLocation(this,x,y);
						break;
						
						
			case 'S':	this.transformObject(game,this,'F');
						game.map.ClearScreenLocation(x_old, y_old);
						this.setX(x);
						this.setY(y);
						game.map.setObjectOnLocation(this,x,y);
						break;
		}
		
	}
	
	// Getters
}
