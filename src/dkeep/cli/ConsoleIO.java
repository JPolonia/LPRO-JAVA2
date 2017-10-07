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
		return scanner.nextLine().charAt(0);
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
		System.out.println("Welcome to the Dragon's Bane v" + game.GetVersion());
	}
	
	public void printCurrentState(GameMain game){
		System.out.println(game.GetState());
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
}
