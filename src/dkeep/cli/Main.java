package dkeep.cli;

import dkeep.logic.GameMain;

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
		
		//Init Game
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
		
		/*// Init elements
		GameElement key = new GameElement('K');
		key.addRandomGameElement(map, key);
		
		GameElement exit = new GameElement('E');
		map.setObjectOnLocation(exit,9,5);
		
		GameElement dragon = new GameElement('D');
		dragon.addRandomGameElement(map, dragon);
		
		// Init player
		Hero hero = new Hero('H');
		hero.addRandomLocation(map, hero);*/
		
		// Input from player
		//Scanner scanner = new Scanner(System.in);
		
		
		//Get number of dragons from user and update Game
		//input = scanner.nextInt();
		

		// The game logic starts here
		char input_c;
		boolean isRunning = true;

		while (isRunning) {
			//screen.PrintScreen();
			// Get input from player and do something
			input = cli.getChar();
			switch (input) {
				case 'a':
					//hero.moveLeft(screen, hero);
					break;
				case 'd':
					//hero.moveRight(screen, hero);
					break;
				case 'w':
					//hero.moveUp(screen, hero);
					break;
				case 's':
					//hero.moveDown(screen, hero);
					break;
				default: break;
			}
			//isRunning = (hero.isFree || hero.isDead) ? false:true;
		}
		
		//scanner.close();
	}
	
	

}
