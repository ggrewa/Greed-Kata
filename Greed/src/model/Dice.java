/*
 * Dice class.
 * Greed.
 */
package model;

import java.io.File;
import java.io.Serializable;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This class contains a Dice and its related methods.
 * 
 * @author Gurleen Grewal
 * @version Fall 2021
 */
public class Dice implements Serializable{
	
	/**The serialization number.*/
	private static final long serialVersionUID = 1914015788099236610L;
	
	/**Array that holds the Dice.*/
	private int[] myDice;
	
	/**The roll number for the dice throw.*/
	private int myRollNum;
	
	/**The max score of the game.*/
	private int myMaxScore;
	
	/**
	 * Constructor creates Dice object using the given number of dices.
	 * Starts the roll number and the maximum score at 0.
	 * @param theDice the set of dice that are rolled.
	 */
	public Dice (final int [] theDice) {
		if(theDice.length != 5) {
			throw new IllegalArgumentException("The number of dice cannot be more than 5.");
		}
		myDice = theDice;
		myRollNum = 0;
		myMaxScore = 0;
	}
	
	/**
	 * Gets the set of dice that are rolled.
	 * @return the set of dice.
	 */
	public int[] getDice() {
		return myDice;
	}
	
	/**
	 * Sets the set of dice that are rolled.
	 * @param theDice the dice that are rolled.
	 */
	public void setDice(int[] theDice) {
		myDice = theDice;
	}
	
	/**
	 * Randomly rolls all the dice in the set of provided dice.
	 * 
	 * @return the rolled dice.
	 */
	public int[] rollDice() {
		final Random rand = new Random();
		for(int i = 0; i < myDice.length; i++) {
			myDice[i] = rand.nextInt(6) + 1;
		}
		return myDice;
	}
	
	/**
	 * Creates the number of counts for the dice set and every number on the dice, that is 1-6.
	 * 
	 * @return the counts of the dice.
	 */
	public int[] createCounts(){
		final int [] counts = new int[]{0,0,0,0,0,0};
		for(int j = 0; j < counts.length; j++) {
			for(int k = 0; k < myDice.length; k++) {
				if(myDice[k] == j+1) {
					counts[j]++;
				}
			}
		}
		return counts;
	}
	
	/**
	 * Calculates the score of the dice set that is rolled.
	 * 
	 * @return the score of the set of dice rolled
	 */
	public int calculateScore() {
		int score = 0;
		final int[] counts = createCounts();
		
		//1,1,1
		if(counts[0] >= 3) {
			score += 1000;
			int nums = counts[0]%3;
			score = (nums * 100) + score;
		}
		//1
		else {
		    score = (counts[0] * 100)+ score;
		}
		//5,5,5
		if(counts[4] >= 3) {
			score += 500;
			int nums = counts[4]%3;
			score = (nums * 50) + score;
		}
		//5
		else {
			score =  (counts[4] * 50) + score;
		}
		//2,2,2
		if(counts[1] >= 3) {
			score += 200;
		}
		//3,3,3
		if(counts[2] >= 3) {
			score += 300;
		}
		//4,4,4
		if(counts[3] >= 3) {
			score += 400;
		}
		//6,6,6
		if(counts[5] >= 3) {
			score += 600;
		}
		if(score > myMaxScore) {
			myMaxScore = score;
		}
		return score;
	}
	
	/**
	 * Plays the sound of rolling dice.
	 */
	public void playRollingDice() {
    	try 
        {
            String soundName = "RollingDice.wav";    
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
            		new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	/**
	 * Plays a ring-clinking sound.
	 */
	public void playRing() {
    	try 
        {
            String soundName = "Ring.wav";    
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
            		new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	/**
	 * Gives the string representation of the dice rolled.
	 * 
	 * @return the dice rolled.
	 */
	public String printDiceNumbers() {
		StringBuilder sb = new StringBuilder();
		myRollNum++;
		sb.append("Dice roll #" + myRollNum);
		sb.append("\n");
		for(int i = 0; i <myDice.length; i++) {
			sb.append(myDice[i] + " ");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	/**
	 * Gives the string representation of the score of a set of dice.
	 * 
	 * @param theScore the calculated score.
	 * @return the score of the set of dice.
	 */
	public String printScore(int theScore) {
		StringBuilder sb = new StringBuilder();
		sb.append("Your score is: " + theScore);
		return sb.toString();
	}
	
	/**
	 * Gives the string representation of the max score of the game.
	 * 
	 * @return the maximum score.
	 */
	public String printMaxScore() {
		StringBuilder sb = new StringBuilder();
		sb.append("Your max score is: " + myMaxScore);
		return sb.toString();
	}
}
