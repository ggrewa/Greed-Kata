/*
 * Menu class.
 * Greed.
 */
package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;

import model.Dice;

/**
 * This class runs the game and prints out the dice roll and score of the game.
 * 
 * @author Gurleen Grewal
 * @version Fall 2021
 */
public class Menu {
	
	/**Scanner object that is used to get user input.*/
	private static final Scanner myScanner = new Scanner(System.in);
	
	/**Dice object used throughout the game.*/
	private static Dice myDice;
	
	/**Used to evaluate user input to create a new game.*/
	private static final String MY_NEWGAME = "N";
	
	/**Used to evaluate user input to load a new game.*/
	private static final String MY_LOADGAME = "L";
	
	/**Used to evaluate user input to display the help menu.*/
	private static final String MY_HELPMENU = "H";
	
	/**Used to evaluate user input to quit the game.*/
	private static final String MY_QUIT = "Q";
	
	/**Used to evaluate user input to roll dice.*/
	private static final String MY_ROLLDICE = "R";
	
	/**Used to evaluate user input to save game.*/
	private static final String MY_SAVEGAME = "S";
	
	/**
	 * Runs the game.
	 * 
	 * @param args the arguments.
	 */
	public static void main(String [] args) {
		printIntro();
		final int[] fiveDice = new int[]{0,0,0,0,0};
    	myDice = new Dice(fiveDice);
		run();
    }
	
	/**
	 * Runs the home menu that creates a new game, loads a game, prints the help menu or quits
	 * the game.
	 */
	public static void run() {
		final String homeSelection = printHomeMenu();
		
		if(homeSelection.equals(MY_NEWGAME)) {
			myDice.playRing();
    		playGame();
    	}
    	else if(homeSelection.equals(MY_LOADGAME)) {
    		myDice.playRing();
    		loadGame();
    		System.out.println();
    	}
    	else if(homeSelection.equals(MY_HELPMENU)) {
    		myDice.playRing();
    		System.out.println();
    		printHelpMenu();
    		run();
    	}
    	else if(homeSelection.equals(MY_QUIT)) {
    		printGameOutro();
    	}
	}
	
	/**
	 * Runs in game menu, rolls the dice, saves the game or quits the game.
	 */
	public static void playGame() {
		boolean quit = false;
		while(!quit) {
			final String choice = printRollMenu();
			
			if(choice.equals(MY_ROLLDICE)) {
				myDice.playRollingDice();
				myDice.rollDice();
				final int score = myDice.calculateScore();
				System.out.println(myDice.printDiceNumbers());
				System.out.println(myDice.printScore(score));
				System.out.println(myDice.printMaxScore());
				System.out.println();
				System.out.println("+------------------------------------------------------+");
				System.out.println();
			}
			else if(choice.equals(MY_SAVEGAME)) {
				myDice.playRing();
				saveGame();
				System.out.println();
			}
			else if(choice.equals(MY_QUIT)) {
				printGameOutro();
				quit = true;
			}
		}
	}
	
	/**
	 * Loads the game.
	 */
	public static void loadGame() {
		try {
            final FileInputStream file = new FileInputStream("SavedGame");
            final ObjectInputStream putIn = new ObjectInputStream(file);
            
            myDice = (Dice)putIn.readObject();
            putIn.close();
            file.close();
            System.out.println("Game loaded!"); 
            playGame();
        
        } catch (Exception e) {
            System.out.println("Game could not be loaded. Try again!");
            run();
        }
	}
	
	/**
	 * Saves the game.
	 */
	public static void saveGame() {
		try {
            final FileOutputStream file = new FileOutputStream("SavedGame");
            final ObjectOutputStream putOut = new ObjectOutputStream(file);
            putOut.writeObject(myDice);
            putOut.close();
            file.close();
            System.out.println("Game saved!");
            
		} catch (Exception e) {
			System.out.println("The game could not be saved. Try again!");
		}
	}
	
	/**
     * Prints the home menu and gets user choice.
     * 
     * @return the user choice.
     */
	public static String printHomeMenu() {
        System.out.println("N-- New Game");
        System.out.println("L-- Load Game");
        System.out.println("H-- Help Menu");
        System.out.println("Q-- Quit \n");
        
        final String[] options = new String[]{"N", "L", "H", "Q"};
        String choice = myScanner.nextLine();
        while(!Arrays.asList(options).contains(choice)) {
        	System.out.println("Entry is not Valid! Try again.");
    		choice = myScanner.nextLine();
        }
    	return choice;
    }
	
	/**
     * Prints the in game menu and gets the user choice.
     * 
     * @return the user choice.
     */
	public static String printRollMenu() {
    	System.out.println("What would you like to do?");
    	System.out.println("R-- Roll dice");
        System.out.println("S-- Save Game");
        System.out.println("Q-- Quit \n");
        
        final String[] options = new String[]{"R", "S", "Q"};
        String choice = myScanner.nextLine();
        while(!Arrays.asList(options).contains(choice)) {
        	System.out.println("Entry is not Valid! Try again.");
    		choice = myScanner.nextLine();
        }
    	return choice;
    }
	
	/**
     * Prints game intro.
     */
	public static void printIntro() {
		System.out.println("  ____");
		System.out.println(" /\\' .\\    _____");
		System.out.println("/: \\___\\  / .  /\\");
		System.out.println("\\' / . / /____/..\\");
		System.out.println(" \\/___/  \\'  '\\  /");
		System.out.println("          \\'__'\\/");
		System.out.println();
		System.out.println("Welcome to Greed!");
		System.out.println();
	}
	
	/**
     * Prints game outro.
     */
	public static void printGameOutro() {
		System.out.println("See you soon!");
		System.out.println("  ____");
		System.out.println(" /\\' .\\    _____");
		System.out.println("/: \\___\\  / .  /\\");
		System.out.println("\\' / . / /____/..\\");
		System.out.println(" \\/___/  \\'  '\\  /");
		System.out.println("          \\'__'\\/");
		System.out.println();
	}
	
	/**
     * Prints help menu and game instructions.
     */
	public static void printHelpMenu() {
		System.out.println("Welcome to Greed Kata.");
		System.out.println("Greed is a press-your-luck dice rolling game. In the game, the");
		System.out.println("player rolls the dice and tries to earn as many points as");
		System.out.println("possible from the result. For the purposes of this kata, we will");
		System.out.println("be scoring a single roll of five dice.");
		System.out.println("");
		System.out.println("Here are the scoring rules:");
		System.out.println("A single one -> 100 points");
		System.out.println("A single five -> 50 points");
		System.out.println("[1,1,1] -> 1000 points");
		System.out.println("[2,2,2] -> 200 points");
		System.out.println("[3,3,3] -> 300 points");
		System.out.println("[4,4,4] -> 400 points");
		System.out.println("[5,5,5] -> 500 points");
		System.out.println("[6,6,6] -> 600 points");
		System.out.println("");
		System.out.println("+------------------------------------------------------+");
	}

}
