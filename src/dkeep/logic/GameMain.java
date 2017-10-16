package dkeep.logic;

public class GameMain {
	private int version = 1; 
	
	public enum GameState{INIT,RUNNING,GAMEOVER,COMPLETED};
	private GameState currentState; 
	
	public GameMap map;
	public Hero hero;
	public GameElement exit;
	public GameElement sword;
	public Dragon[] dragon;
	
	public GameMain(char[][] maze) {
		this.currentState = GameState.INIT;
		//Generate Maze
		this.map = new GameMap(maze.length,maze[0].length,maze);
	}
	
	
	// Getters
	public GameState getState(){
		return this.currentState;
	}
	
	public int getVersion(){
		return this.version;
	}
	public Dragon getDragonOnPosition(int x, int y){
		for(int i=0;i<this.dragon.length;i++){
			if(this.dragon[i].getX()==x && this.dragon[i].getY()==y) return this.dragon[i];
		}
		return null;
	}
	
	// Setters
	public void startGame(){
		this.currentState = GameState.RUNNING;
	}
	public void updateState(){
		if(hero.isDead) this.currentState = GameState.GAMEOVER;
		if(hero.isFree) this.currentState = GameState.COMPLETED;
	}
	public void newExit(char symbol,int x, int y){
		this.exit = new GameElement(symbol);
		this.map.setObjectOnLocation(this.exit,x,y);
	}
	public void newSword(char symbol){
		this.sword = new GameElement(symbol);
		this.sword.addRandomGameElement(this.map);
	}
	public void newDragons(char symbol,int number){
		//Create reference
		this.dragon = new Dragon[number];
		//Create object for each reference
		for(int i=0;i<number;i++){
			this.dragon[i] = new Dragon(symbol);
			this.dragon[i].addRandomLocation(this.map);
		}
	}
	public void newHero(char symbol){
		this.hero = new Hero(symbol);
		this.hero.addRandomLocation(this.map);
	}
	public void killDragonsNearPosition(int x, int y){
		if (x>0) if (this.map.getObjectOnLocation(x-1,y) == 'D') this.getDragonOnPosition(x-1,y).killDragon(this);
		if (x<9) if (this.map.getObjectOnLocation(x+1,y) == 'D') this.getDragonOnPosition(x+1,y).killDragon(this);
		if (y<9) if (this.map.getObjectOnLocation(x,y+1) == 'D') this.getDragonOnPosition(x,y+1).killDragon(this);
		if (y>0) if (this.map.getObjectOnLocation(x,y-1) == 'D') this.getDragonOnPosition(x,y-1).killDragon(this);
	}
	public void moveAllDragons(){
		for(int i=0;i<this.dragon.length;i++){
			this.dragon[i].moveDragon(this);
		}
	}	
	
	// Booleans
	public boolean allDragonSlained(){
		for(int i=0;i<this.dragon.length;i++){
			if(this.dragon[i].isAlive) return false;
		}
		return true;
	}
   
}


