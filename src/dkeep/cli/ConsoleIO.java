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
				System.out.print(game.map.board[i][j]+ " ");
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
	public void PrintDebug(GameMain game) {
		System.out.println("Positions of Elements:");
		System.out.println(" Sword("+game.sword.getSymbol()+"):("+game.sword.getX() + ","+game.sword.getY()+")");
		System.out.print(" Hero("+ game.hero.getSymbol()+"): ("+game.hero.getX() + ","+ game.hero.getY()+ ")");
			if(game.hero.isDead) System.out.print(" isDead");
			if(game.hero.isFree) System.out.print(" isFree");
			System.out.print("\n");
		for(int i=0;i<game.dragon.length;i++){
			System.out.print(" Dragon["+i+"]("+ game.dragon[i].getSymbol()+"): ("+game.dragon[i].getX() + ","+ game.dragon[i].getY()+ ")");
				if(!game.dragon[i].isAlive) System.out.print(" isDead");
				if(!game.dragon[i].isAwake) System.out.print(" isSleeping");
				System.out.print("\n");
		}
	}
}
