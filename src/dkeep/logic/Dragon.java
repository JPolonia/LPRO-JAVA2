package dkeep.logic;

public class Dragon extends GameObject {

	public boolean isAlive;
	public boolean isAwake;
	public int SleepingTurns;
	
	public Dragon(char symbol) {
		setSymbol(symbol);
		this.isAlive = true;
		this.isAwake = true;
		this.SleepingTurns = 0;
	}
	
	// Setters
	public void killDragon(GameMain game){
		this.isAlive = false;
		game.map.ClearScreenLocation(this.getX(), this.getX());
		this.transformObject(game,this,' ');
		this.setX(0);
		this.setY(0);
		game.map.setObjectOnLocation(this,0,0);		
	}
	
	public void wakeUpDragon(GameMain game){
		this.isAwake = true;
		transformObject(game,this,'D');
		game.map.setObjectOnLocation(this,this.getX(),this.getY());
	}
	public void sleepDragon(GameMain game){
		this.isAwake = false;
		transformObject(game,this,'d');
		game.map.setObjectOnLocation(this,this.getX(),this.getY());
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
		int x_old,y_old,x,y,move,tries;
		boolean valid = false;
		int awake;
		
		x_old = this.getX();
		y_old = this.getY();
		x = 0;
		y = 0;
		move = 0;
		tries = 0;
		awake = 1;
		
		if(this.SleepingTurns < 1){
			awake = 0 + (int) (Math.random() * ((1-0)+1));
			if(awake == 0)
				this.SleepingTurns = 1 + (int) (Math.random() * ((10-1)+1));
		}else
			this.SleepingTurns--;
		
		
		if(this.SleepingTurns == 0){
			wakeUpDragon(game);
			//check if valid position 1-left, 2-right, 3-up, 4-down
			while(!valid && tries < 200){
				//Generate random positions Math.random() * (max - min)+1 + min
				//Min + (int)(Math.random() * ((Max - Min) + 1))
				move = 1 + (int) (Math.random() * ((4-1)+1));
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
				tries++;
				System.out.print(".");
			}
			System.out.println(" ");
			if(!valid) System.out.println("COULDNT FIND VALID LOCATION FOR DRAGON");
			
			if(game.map.nearObject(x,y,game.hero)){
				System.out.print("DRAGON NEAR HERO!   ");
				//Check if hero has sword
				if(game.hero.getSymbol() == 'A'){ 
					System.out.println("DRAGON DEAD!!");
					this.killDragon(game);
				}else {
					System.out.println("HERO DEAD!");
					game.hero.killHero(game);
					transformObject(game,game.hero,'-');
				}
				
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
								this.transformObject(game,this,'D');
								game.map.setObjectOnLocation(game.sword,x_old,y_old); //Put sword back
								game.map.setObjectOnLocation(game.sword,x,y); //Put sword back
							}else{
								game.map.ClearScreenLocation(x_old, y_old);
							}
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
			
		}else{
			sleepDragon(game);
			
		}
		
	}
	
	// Getters
}
