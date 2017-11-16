package dkeep.cli;

import dkeep.logic.GameMain;
import dkeep.logic.GameMain.GameState;

public class Main {
	

	public static void main(String[] args) {

		//User defined Maze Layout		
		char[][] maze ={{'X','X','X','X','X','X','X','X','X','X'},
				  		{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				  		{'X',' ','X','X',' ','X',' ','X',' ','X'},  
				  		{'X',' ','X','X',' ','X',' ',' ',' ','X'},  
				  		{'X',' ','X','X',' ','X',' ','X',' ','X'},
				  		{'X',' ',' ',' ',' ',' ',' ','X',' ','X'},  
				  		{'X',' ','X','X',' ','X',' ','X',' ','X'},  
				  		{'X',' ',' ',' ',' ','X',' ','X',' ','X'},  
				  		{'X',' ','X','X',' ',' ',' ',' ',' ','X'},  
				  		{'X','X','X','X','X','X','X','X','X','X'}
				  	   };

		
		//Init Command Line Interface
		ConsoleIO cli = new ConsoleIO();
		
		//Init Maze
		GameMain game = new GameMain(maze);
		cli.printWelcome(game);
		cli.printMap(game);
		cli.println("Maze successfully created!");
		
		//Init Elements
		game.newExit('E',9,5);
		game.newSword('S');
		
		//Init Dragons
		cli.print("Choose number of dragons: ");
		int input = cli.getInt();
		cli.print("Choose Dragon Movement Strategy (1-Random, 2-Sleepy): ");
		int input2 = cli.getInt();
		game.newDragons('D',input,input2);
		game.newHero('H');
		//game.hero = new Hero('H',game.map,game.dragon);
		
		//Init Game
		game.startGame();
		cli.printCurrentState(game);
		cli.printMap(game);
		//Game is Running
		while (game.getState() == GameState.RUNNING) {
			
			cli.askNextMove();
			
			// Get input from player and do something
			char input_c = cli.getChar();
			switch (input_c) {
				case 'a':
					game.hero.moveL(game);
					break;
				case 'd':
					game.hero.moveR(game);
					break;
				case 'w':
					game.hero.moveU(game);
					break;
				case 's':
					game.hero.moveD(game);
					break;
				default: break;
			}
			//Check  state
			game.updateState();
			if(game.getState() != GameState.RUNNING) break;
			
			//Dragons move
			game.moveAllDragons();
			
			//Check state
			game.updateState();
			
			//Debug Print
			cli.PrintDebug(game);
			cli.printMap(game);
		}
		
		if(game.getState() == GameState.COMPLETED) cli.PrintGameCompleted();
		if(game.getState() == GameState.GAMEOVER) cli.PrintGameOver();
	}
	
	

}
