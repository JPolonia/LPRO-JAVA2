package dkeep.logic;

public class GameMain {
	private int version = 1; 
	
	public enum GameState{INIT,RUNNING,GAMEOVER};
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
	
	// Setter
	public void startGame(){
		this.currentState = GameState.RUNNING;
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
   
}


