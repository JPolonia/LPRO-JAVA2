package dkeep.cli;

import dkeep.logic.GameMain;
import dkeep.logic.GameMain.GameState;

public class Main {
	

	public static void main(String[] args) {

		//User defined Maze Layout		
		char[][] maze ={{'X','X','X','X','X','X','X','X','X','X'},
				  		{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				  		{'X',' ','X','X',' ','X',' ','X',' ','X'},  
				  		{'X',' ','X','X',' ','X',' ','X',' ','X'},  
				  		{'X',' ','X','X',' ','X',' ','X',' ','X'},
				  		{'X',' ',' ',' ',' ',' ',' ','X',' ','X'},  
				  		{'X',' ','X','X',' ','X',' ','X',' ','X'},  
				  		{'X',' ','X','X',' ','X',' ','X',' ','X'},  
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
		cli.print("Choose number of dragons: ");
		int input = cli.getInt();
		
		//Init Elements
		game.newExit('E',9,5);
		game.newSword('S');
		game.newDragons('D',input);
		game.newHero('H');
		
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
					game.hero.moveLeft(game);
					break;
				case 'd':
					game.hero.moveRight(game);
					break;
				case 'w':
					game.hero.moveUp(game);
					break;
				case 's':
					game.hero.moveDown(game);
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
			cli.PrintGameElements(game);
			cli.printMap(game);
		}
		
		if(game.getState() == GameState.COMPLETED) cli.PrintGameCompleted();
		if(game.getState() == GameState.GAMEOVER) cli.PrintGameOver();
	}
	
	

}
