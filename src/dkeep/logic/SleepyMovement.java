package dkeep.logic;

public class SleepyMovement implements DragonMovement {

	public int SleepingTurns;
	
	public SleepyMovement(Dragon drake) {
		drake.isAwake = true;
		this.SleepingTurns = 0;
	}
	
	
	
	public void move(GameMain game, Dragon drake) {
		// TODO Auto-generated method stub
		int x_old,y_old,x,y,move,tries;
		boolean valid = false;
		int awake;
		
		x_old = drake.getX();
		y_old = drake.getY();
		x = 0;
		y = 0;
		move = 0;
		tries = 0;
		awake = 1;
		
		if(this.SleepingTurns < 1){
			awake = 0 + (int) (Math.random() * ((9-0)+1));
			if(awake > 8)
				this.SleepingTurns = 1 + (int) (Math.random() * ((10-1)+1));
		}else
			this.SleepingTurns--;
		
		
		if(this.SleepingTurns == 0){
			if(drake.isAwake == false)drake.wakeUpDragon();
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
				if(valid){
					valid = game.moveValidDragon(x,y);
				}
				tries++;
				System.out.print(".");
			}
			System.out.println(" ");
			if(!valid) System.out.println("COULDNT FIND VALID LOCATION FOR DRAGON");
			
					
			switch (game.map.getObjectOnLocation(x,y)){
				case 'X':	break;
				case 'E':	break;
				case 'A':	//Dragon dies
							drake.killDragon();
							break;
				case 'H':	//Hero dies
							game.hero.killHero();
							break;
				
				case ' ':   if(drake.getSymbol() == 'F')drake.setSymbol('D');
							drake.setX(x);
							drake.setY(y);
							break;
							
				case 'S':	drake.setSymbol('F');
							drake.setX(x);
							drake.setY(y);
							break;
			}
			game.fightHeroNearDragons();
		}else{
			drake.sleepDragon();
		}
		
		
		
	}

}
