package dkeep.logic;

public class GameMain {
	private int version = 1; 
	
	private enum GameState{INIT,RUNNING,GAMEOVER};
	private GameState currentState; 
	
	public GameMap map;
	public Hero hero;
	public GameElement exit;
	public GameElement sword;
	public Dragon[] dragon;
	
	public GameMain(char[][] maze) {
		this.currentState = GameState.INIT;
		this.map = new GameMap(maze.length,maze[0].length,maze);
	}
	
	
	// Getters
	public GameState GetState(){
		return this.currentState;
	}
	
	public int GetVersion(){
		return this.version;
	}
	
	// Setter
	public void newExit(char symbol,int x, int y){
		this.exit = new GameElement(symbol);
		this.map.setObjectOnLocation(this.exit,x,y);
	}
	public void newSword(char symbol){
		this.sword = new GameElement(symbol);
	}
	public void newDragons(char symbol,int number){
		this.sword = new GameElement(symbol);
	}
	public void newHero(char symbol){
		this.hero = new Hero(symbol);
	}
   
}


