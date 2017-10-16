package dkeep.cli;

import dkeep.logic.GameMain;
import java.util.Scanner;

public class ConsoleIO {
	private Scanner scanner;
	
	public ConsoleIO(){
		this.scanner = new Scanner(System.in);
	}
	
	//Close Scanner
	public void close(){
		this.scanner.close();
	}
	
	// Getters
	public int getInt(){
		return scanner.nextInt();
	}
	public char getChar(){
		return scanner.next().charAt(0);
	}
	
	// Printers
	public void println(String string){
		System.out.println(string);
	}
	public void print(String string){
		System.out.print(string);
	}
	public void printMap(GameMain game) {
		for (int i = 0; i < game.map.getScreenHeight(); i++) {
			for (int j = 0; j < game.map.getScreenWidth(); j++) {
				System.out.print(game.map.getObjectOnLocation(j,i)+ " ");
			}
			System.out.println();
		}
	}
	public void printWelcome(GameMain game){
		System.out.println("Welcome to the Dragon's Bane v" + game.getVersion());
	}
	
	public void printCurrentState(GameMain game){
		System.out.println(game.getState());
	}
	public void askNextMove(){
		System.out.print("Next move: ");
	}
	public void askNumberDragons(){
		System.out.print("Choose number of dragons: ");
	}
	public void printExitClose(){
		System.out.println("YOU NEED A KEY!!");
	}
	public void PrintGameCompleted() {
		System.out.println("YOU GOT OUT! ENJOY YOUR FREEDOM!!");
	}
	public void PrintGameOver() {
		System.out.println("YOU GOT EATEN BY AN HUNGRY DRAGON!!");
	}
	public void PrintGameElements(GameMain game) {
		System.out.println("Positions of Elements:");
		System.out.println("Hero: x="+game.hero.getX() + "y="+game.hero.getY()+ " symbol: "+ game.hero.getSymbol()+ " isDead="+game.hero.isDead + " isFree="+game.hero.isFree);
		System.out.println("Sword: x="+game.sword.getX() + "y="+game.sword.getY()+ " symbol: "+ game.sword.getSymbol() );
		for(int i=0;i<game.dragon.length;i++){
			System.out.println("Dragon["+i+"]: x="+game.dragon[i].getX() + "y="+game.dragon[i].getY()+ " symbol: "+ game.dragon[i].getSymbol()+ " isAlive="+game.dragon[i].isAlive);
		}
	}
}
