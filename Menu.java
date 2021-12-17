package view;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;

import model.Dice;

public class Menu {
	
	static Scanner myScanner = new Scanner(System.in);
	private static Dice myDice;
	
	public static void main(String [] args) {
		printIntro();
		int[] arr = new int[]{0,0,0,0,0};
    	myDice = new Dice(arr);
		run();
    }
	
	public static String printHomeMenu() {
        System.out.println("1-New Game");
        System.out.println("2-Load Game");
        System.out.println("3-Help Menu");
        System.out.println("4-Quit \n");
        
        final String[] options = new String[]{"1", "2", "3", "4"};
        String choice = myScanner.nextLine();
        while(!Arrays.asList(options).contains(choice)) {
        	System.out.println("Entry is not Valid! Try again.");
    		choice = myScanner.nextLine();
        }
    	return choice;
    }
	
	public static String printMenu2() {
    	System.out.println("What would you like to do?");
    	System.out.println("1-Roll dice");
        System.out.println("2-Save Game");
        System.out.println("3-Quit \n");
        
        final String[] options = new String[]{"1", "2", "3"};
        String choice = myScanner.nextLine();
        while(!Arrays.asList(options).contains(choice)) {
        	System.out.println("Entry is not Valid! Try again.");
    		choice = myScanner.nextLine();
        }
    	return choice;
    }
	
	public static void printIntro() {
		System.out.println("  ____");
		System.out.println(" /\\' .\\    _____");
		System.out.println("/: \\___\\  / .  /\\");
		System.out.println("\\' / . / /____/..\\");
		System.out.println(" \\/___/  \\'  '\\  /");
		System.out.println("          \\'__'\\/");
		System.out.println();
		System.out.println("Welcome to Greed!");
	}
	
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
	}
	
	public static void run() {
		final String homeSelection = printHomeMenu();
		
		if(homeSelection.equals("1")) {			// create new game
    		playGame();
    	}
    	else if(homeSelection.equals("2")) {	//load game
    		loadGame();
    	}
    	else if(homeSelection.equals("3")) {	//help menu
    		printHelpMenu();
    		run();
    	}
    	else if(homeSelection.equals("4")) {	//quit 
    		printGameOutro();
    	}
	}
	
	public static void playGame() {
		boolean quit = false;
		while(!quit) {
			final String choice = printMenu2();
			
			if(choice.equals("1")) {			//roll the dice
				myDice.rollDice();
				int score = myDice.calculateScore();
				System.out.println(myDice.printDiceNumbers());
				System.out.println(myDice.printScore(score));
			}
			else if(choice.equals("2")) {		//saves the game
				saveGame();
			}
			else if(choice.equals("3")) {		//quits the game
				printGameOutro();
				quit = true;
			}
		}
	}
	
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
            e.printStackTrace();
            run();
        }
	}
	
	public static void saveGame() {
		try {
            final FileOutputStream file = new FileOutputStream("SavedGame");
            final ObjectOutputStream putOut = new ObjectOutputStream(file);
            putOut.writeObject(myDice);
            putOut.close();
            file.close();
            System.out.println("Game saved! we'll keep your secrets safe!\n");
            
		} catch (Exception e) {
			System.out.println("The game could not be saved. Try again!");
			e.printStackTrace();
		}
	}

}
