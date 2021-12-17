/*
 * DicetEST class.
 * Greed.
 */
package tests;

import model.Dice;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class test the Dice class.
 * 
 * @author Gurleen Grewal
 * @version Fall 2021
 */
class DiceTest {
	
	/**Dice object used throughout the test class. */
	private Dice myDice;
	
	/**
     * Method used to initialize the text fixture before each test.
     */
	@BeforeEach
	void setUp() throws Exception {
		myDice = new Dice(new int[]{0,0,0,0,0});
	}
	
	/**
	 * Tests constructor when number of dice is more than 5.
	 */
	@Test
	void testDiceMoreThanFive() {
		 assertThrows(IllegalArgumentException.class, () -> 
		 new Dice(new int[] {0,0,0,0,0,0,0}));
	}
	
	/**
	 * Tests constructor when number of dice is less than 5.
	 */
	@Test
	void testDiceLessThanFive() {
		 assertThrows(IllegalArgumentException.class, () -> 
		 new Dice(new int[] {1,2,3}));
	}
	
	/**
	 * Tests the method that randomly rolls the set of dice.
	 */
	@Test
	void testRollDice() {
		int[] arr = myDice.rollDice();
		for(int i = 0; i < arr.length; i++) {
			assertEquals(true, arr[i] != 0);
		}
	}
	
	/**
	 * Tests the method that gets and sets the set of dice.
	 */
	@Test
	void testGetSetDice() {
		int[] array = new int[] {1,2,3,4,5};
		myDice.setDice(array);
		int[] getValue = myDice.getDice();
		for(int i = 0; i < myDice.getDice().length; i++) {
			assertEquals(array[i], getValue[i]);
		}
	}
	
	/**
	 * Tests the method that creates the counts for the occurences of numbers 1-6 for the set
	 * of dice rolled.
	 */
	@Test
	void testCreateCounts() {
		int [] array = new int[] {1,2,2,5,6};
		myDice.setDice(array);
		int[] actual = myDice.createCounts();
		int[] expected = new int[] {1,2,0,0,1,1};
		
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}
	
	/**
	 * Tests the method that creates the counts for the occurences of numbers 1-6 for the set
	 * of dice rolled. Tests for a case of triple ones.
	 */
	@Test
	void testCreateTripleCounts() {
		int [] array = new int[] {1,1,1,5,6};
		myDice.setDice(array);
		int[] actual = myDice.createCounts();
		int[] expected = new int[] {3,0,0,0,1,1};
		
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}
	
	/**
	 * Tests the method that calculates the score when none of the rules are met and score 
	 * is 0.
	 */
	@Test
	void testCalculateScoreZero() {
		myDice.setDice(new int[] {2,3,4,6,2});
		assertEquals(0, myDice.calculateScore());
	}
	
	/**
	 * Tests the method that calculates the score when there is a single one.
	 */
	@Test
	void testCalculateScoreSingleOne() {
		myDice.setDice(new int[] {1,2,3,4,6});
		assertEquals(100, myDice.calculateScore());
	}
	
	/**
	 * Tests the method that calculates the score when there is a triple one and two single
	 * ones.
	 */
	@Test
	void testCalculateScoreTripleOne() {
		myDice.setDice(new int[] {1,1,1,1,1});
		assertEquals(1200, myDice.calculateScore());
	}
	
	/**
	 * Tests the method that calculates the score when there is a triple one and a single one 
	 * and five.
	 */
	@Test
	void testCalculateScoreTripleOneSingleFive() {
		myDice.setDice(new int[] {1,1,1,5,1});
		assertEquals(1150, myDice.calculateScore());
	}
	
	/**
	 * Tests the method that calculates the score when there is a triple one only.
	 */
	@Test
	void testCalculateScoreTripleOneOnly() {
		myDice.setDice(new int[] {1,1,1,4,6});
		assertEquals(1000, myDice.calculateScore());
	}
	
	/**
	 * Tests the method that calculates the score when there is a triple two and two single 
	 * twos.
	 */
	@Test
	void testCalculateScoreTripleTwo() {
		myDice.setDice(new int[] {2,2,2,2,2});
		assertEquals(200, myDice.calculateScore());
	}
	
	/**
	 * Tests the method that calculates the score when there is a triple three and single five.
	 */
	@Test
	void testCalculateScoreTripleThree() {
		myDice.setDice(new int[] {3,4,5,3,3});
		assertEquals(350, myDice.calculateScore());
	}
	
	/**
	 * Tests the method that calculates the score when there is a triple four and two single 
	 * fours.
	 */
	@Test
	void testCalculateScoreTripleFour() {
		myDice.setDice(new int[] {4,4,4,4,4});
		assertEquals(400, myDice.calculateScore());
	}
	
	/**
	 * Tests the method that calculates the score when there is a triple five and two single 
	 * fives.
	 */
	@Test
	void testCalculateScoreTripleFive() {
		myDice.setDice(new int[] {5,5,5,5,5});
		assertEquals(600, myDice.calculateScore());
	}
	
	/**
	 * Tests the method that calculates the score when there is a triple five and a single 
	 * one.
	 */
	@Test
	void testCalculateScoreTripleFiveSingleOne() {
		myDice.setDice(new int[] {5,5,5,3,1});
		assertEquals(600, myDice.calculateScore());
	}
	
	/**
	 * Tests the method that calculates the score when there is a triple six and two single 
	 * sixes.
	 */
	@Test
	void testCalculateScoreTripleSix() {
		myDice.setDice(new int[] {6,6,6,6,6});
		assertEquals(600, myDice.calculateScore());
	}
	
	/**
	 * Tests the method that prints the set of dice and its numbers.
	 */
	@Test
	void testPrintDiceNumbers() {
		myDice.setDice(new int[] {3,4,5,3,3});
		StringBuilder sb = new StringBuilder();
		sb.append("Dice roll #1");
		sb.append("\n");
		sb.append("3 4 5 3 3 ");
		sb.append("\n");
		assertEquals(sb.toString(), myDice.printDiceNumbers());
	}
	
	/**
	 * Tests the method that prints the score.
	 */
	@Test
	void testPrintScore() {
		myDice.setDice(new int[] {3,4,5,3,3});
		assertEquals("Your score is: 350", myDice.printScore(myDice.calculateScore()));
	}
	
	/**
	 * Tests the method that prints the max score.
	 */
	@Test
	void testPrintMaxScore() {
		assertEquals("Your max score is: 0", myDice.printMaxScore());
	}
}
