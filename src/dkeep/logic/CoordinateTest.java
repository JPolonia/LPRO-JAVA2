package dkeep.logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dkeep.cli.ConsoleIO;
import dkeep.logic.GameMain.GameState;

public class CoordinateTest {

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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testMoveHeroToFreeCell() {
		GameMain game = new GameMain(maze);
		game.exit = new GameElement('E',9,5);
		game.sword = new GameElement('S',game.map);
		game.dragon = new Dragon[1];
		game.dragon[0] = new Dragon('D',1,1,1);
		game.hero = new Hero('H',4,5);
		assertEquals(4,game.hero.getX());
		assertEquals(5,game.hero.getY());
		game.hero.moveL(game);
		assertEquals(3,game.hero.getX());
		assertEquals(5,game.hero.getY());
	}
	
	@Test
	public void testMoveHeroToWall() {
		GameMain game = new GameMain(maze);
		game.exit = new GameElement('E',9,5);
		game.sword = new GameElement('S',game.map);
		game.dragon = new Dragon[1];
		game.dragon[0] = new Dragon('D',1,1,1);
		game.hero = new Hero('H',4,4);
		assertEquals(4,game.hero.getX());
		assertEquals(4,game.hero.getY());
		game.hero.moveL(game);
		assertEquals(4,game.hero.getX());
		assertEquals(4,game.hero.getY());
	}
	
	@Test
	public void testMoveHeroToSword() {
		//ConsoleIO cli = new ConsoleIO();
		GameMain game = new GameMain(maze);
		//cli.printMap(game);
		game.exit = new GameElement('E',9,5);
		game.sword = new GameElement('S',4,5);
		game.dragon = new Dragon[1];
		game.dragon[0] = new Dragon('D',1,1,1);
		game.hero = new Hero('H',4,4);
		game.startGame();
		//cli.printMap(game);
		game.hero.moveD(game);
		game.updateState();
		//cli.printMap(game);
		assertEquals(game.hero.hasSword,true);
		assertEquals(game.hero.getSymbol(),'A');
	}
	
	@Test
	public void testMoveUnarmedHeroToNearDragon() {
		//ConsoleIO cli = new ConsoleIO();
		GameMain game = new GameMain(maze);
		//cli.printMap(game);
		game.exit = new GameElement('E',9,5);
		game.sword = new GameElement('S',4,5);
		game.dragon = new Dragon[1];
		game.dragon[0] = new Dragon('D',1,4,2);
		game.hero = new Hero('H',4,4);
		game.startGame();
		//cli.printMap(game);
		game.hero.moveU(game);
		game.updateState();
		//cli.printMap(game);
		assertEquals(game.hero.isDead,true);
		assertEquals(game.getState(),GameState.GAMEOVER);
	}
	
	@Test
	public void testMoveArmedHeroToNearDragon() {
		//ConsoleIO cli = new ConsoleIO();
		GameMain game = new GameMain(maze);
		//cli.printMap(game);
		game.exit = new GameElement('E',9,5);
		game.sword = new GameElement('S',1,1);
		game.dragon = new Dragon[1];
		game.dragon[0] = new Dragon('D',1,4,2);
		game.hero = new Hero('A',4,4);
		game.hero.hasSword = true;
		game.startGame();
		//cli.printMap(game);
		game.hero.moveU(game);
		game.updateState();
		//cli.printMap(game);
		assertEquals(game.dragon[0].isAlive,false);
	}
	
	@Test
	public void testMoveHeroToExitAfterKillDrakeAndHasSword() {
		//ConsoleIO cli = new ConsoleIO();
		GameMain game = new GameMain(maze);
		//cli.printMap(game);
		game.exit = new GameElement('E',9,5);
		game.sword = new GameElement('S',1,1);
		game.dragon = new Dragon[1];
		game.dragon[0] = new Dragon('D',1,4,2);
		game.hero = new Hero('A',8,5);
		game.hero.hasSword = true;
		game.dragon[0].killDragon();
		game.startGame();
		//cli.printMap(game);
		game.hero.moveR(game);
		game.updateState();
		//cli.printMap(game);
		assertEquals(game.hero.isFree,true);
		assertEquals(game.getState(),GameState.COMPLETED);
	}
	
	@Test
	public void testMoveHeroToExitWithoutSword() {
		//ConsoleIO cli = new ConsoleIO();
		GameMain game = new GameMain(maze);
		//cli.printMap(game);
		game.exit = new GameElement('E',9,5);
		game.sword = new GameElement('S',1,1);
		game.dragon = new Dragon[1];
		game.dragon[0] = new Dragon('D',1,4,2);
		game.hero = new Hero('H',8,5);
		game.startGame();
		//cli.printMap(game);
		game.hero.moveR(game);
		game.updateState();
		//cli.printMap(game);
		assertEquals(game.hero.isFree,false);
		assertEquals(game.getState(),GameState.RUNNING);
	}
	
	@Test
	public void testMoveHeroToExitWithoutKillDrake() {
		//ConsoleIO cli = new ConsoleIO();
		GameMain game = new GameMain(maze);
		//cli.printMap(game);
		game.exit = new GameElement('E',9,5);
		game.sword = new GameElement('S',1,1);
		game.dragon = new Dragon[1];
		game.dragon[0] = new Dragon('D',1,4,2);
		game.hero = new Hero('A',8,5);
		game.hero.hasSword = true;
		game.startGame();
		//cli.printMap(game);
		game.hero.moveR(game);
		game.updateState();
		//cli.printMap(game);
		assertEquals(game.hero.isFree,false);
		assertEquals(game.getState(),GameState.RUNNING);
	}

	@Test
	public void testMoveHeroToSleepyDragon() {
		//ConsoleIO cli = new ConsoleIO();
		GameMain game = new GameMain(maze);
		//cli.printMap(game);
		game.exit = new GameElement('E',9,5);
		game.sword = new GameElement('S',1,1);
		game.dragon = new Dragon[1];
		game.dragon[0] = new Dragon('D',2,4,2);
		game.dragon[0].sleepDragon();
		game.hero = new Hero('H',4,4);
		game.startGame();
		//cli.printMap(game);
		game.hero.moveU(game);
		game.updateState();
		//cli.printMap(game);
		assertEquals(game.dragon[0].isAlive,true);
		assertEquals(game.hero.isDead,false);
	}
	
	@Test
	public void testMoveArmedHeroToSleepyDragon() {
		//ConsoleIO cli = new ConsoleIO();
		GameMain game = new GameMain(maze);
		//cli.printMap(game);
		game.exit = new GameElement('E',9,5);
		game.sword = new GameElement('S',1,1);
		game.dragon = new Dragon[1];
		game.dragon[0] = new Dragon('D',2,4,2);
		game.dragon[0].sleepDragon();
		game.hero = new Hero('A',4,4);
		game.hero.hasSword = true;
		game.startGame();
		//cli.printMap(game);
		game.hero.moveU(game);
		game.updateState();
		//cli.printMap(game);
		assertEquals(game.dragon[0].isAlive,false);
		assertEquals(game.hero.isDead,false);
	}

}
