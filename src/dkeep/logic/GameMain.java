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
	public GameElement[] wall;

	
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
			if(this.dragon[i].getX() == x && this.dragon[i].getY() == y) return this.dragon[i];
		}
		return null;
	}
	
	// Setters
	public void UpdateMap() {
		//Print Maze
		map.GenerateBoard();
		//Print Exit
		map.board[this.exit.getY()][this.exit.getX()] = this.exit.getSymbol();	
		//Print Sword
		map.board[this.sword.getY()][this.sword.getX()] = this.sword.getSymbol();
		//Print Dragons
		for(int i=0;i<this.dragon.length;i++)
			map.board[this.dragon[i].getY()][this.dragon[i].getX()] = this.dragon[i].getSymbol();
		//Print Hero
		map.board[this.hero.getY()][this.hero.getX()] = this.hero.getSymbol();
	}
	
	public void startGame(){
		this.currentState = GameState.RUNNING;
		this.UpdateMap();
	}
	public void updateState(){
		if(hero.isDead) this.currentState = GameState.GAMEOVER;
		if(hero.isFree) this.currentState = GameState.COMPLETED;
		UpdateMap();
	}
	public void newExit(char symbol,int x, int y){
		this.exit = new GameElement(symbol,x,y);
		//this.map.setObjectOnLocation(this.exit,x,y);
	}
	public void newSword(char symbol){
		this.sword = new GameElement(symbol,this.map);
	}
	public void newSword(char symbol,int x, int y){
		this.sword = new GameElement(symbol,x,y);
	}
	public void newDragons(char symbol,int number,int strategy){
		//Create reference
		this.dragon = new Dragon[number];
		//Create object for each reference
		for(int i=0;i<number;i++)
			this.dragon[i] = new Dragon(symbol,strategy,this.map);
	}
	
	public void newHero(char symbol){
		this.hero = new Hero(symbol,this.map,this.dragon);
	}
	public void newHero(char symbol, int x, int y){
		this.hero = new Hero(symbol,x,y);
	}
	
	public void fightHeroNearDragons(){
		boolean fight;
		for(int i = 0; i<this.dragon.length;i++){
			fight = false;
			if ((this.hero.getX()-1 == this.dragon[i].getX()) && this.hero.getY() == this.dragon[i].getY()) fight = true;
			if ((this.hero.getX()+1 == this.dragon[i].getX()) && this.hero.getY() == this.dragon[i].getY()) fight = true;
			if ((this.hero.getX() == this.dragon[i].getX()) && this.hero.getY()+1 == this.dragon[i].getY()) fight = true;
			if ((this.hero.getX() == this.dragon[i].getX()) && this.hero.getY()-1 == this.dragon[i].getY()) fight = true;
			
			if(fight){
				if(this.hero.hasSword) this.dragon[i].killDragon(); 
				else if(this.dragon[i].isAwake) this.hero.killHero();
			}
		}
	}
	

	public boolean moveValidDragon(int x, int y){
		for(int i = 0; i<this.dragon.length;i++){
			if (this.dragon[i].isAwake && (x == this.dragon[i].getX()) && y == this.dragon[i].getY()) return false;
		}
		return true;
	}
	public void moveAllDragons(){
		for(int i=0;i<this.dragon.length;i++){
			if(this.dragon[i].isAlive) this.dragon[i].moveDrake(this);
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


