package model;

import java.util.Random;

public class Dice {
	
	private int[] myDice;
	
	private int myRollNum;
	
	public Dice(final int [] theDice) {
		if(theDice.length != 5) {
			throw new IllegalArgumentException("The number of dice cannot be more than 5.");
		}
		myDice = theDice;
		myRollNum = 0;
	}
	
	public int[] getDice() {
		return myDice;
	}
	
	public void setDice(int[] theDice) {
		myDice = theDice;
	}
	
	public int[] rollDice() {
		Random rand = new Random();
		for(int i = 0; i < myDice.length; i++) {
			myDice[i] = rand.nextInt(6) + 1;
		}
		return myDice;
	}
	
	public int[] createCounts(){
		int [] counts = new int[]{0,0,0,0,0,0};
		for(int j = 0; j < counts.length; j++) {
			for(int k = 0; k < myDice.length; k++) {
				if(myDice[k] == j+1) {
					counts[j]++;
				}
			}
		}
		return counts;
	}
	
	public int calculateScore() {
		int score = 0;
		int[] counts = createCounts();
		
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
		return score;
	}
	
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
	
	public String printScore(int theScore) {
		StringBuilder sb = new StringBuilder();
		sb.append("Your score is: " + theScore);
		return sb.toString();
	}
}
