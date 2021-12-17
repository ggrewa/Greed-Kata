package tests;

import model.Dice;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiceTest {
	
	Dice myDice;

	@BeforeEach
	void setUp() throws Exception {
		myDice = new Dice(new int[]{0,0,0,0,0});
	}
	
	@Test
	void testDiceMoreThanFive() {
		 assertThrows(IllegalArgumentException.class, () -> 
		 new Dice(new int[] {0,0,0,0,0,0,0}));
	}
	
	@Test
	void testDiceLessThanFive() {
		 assertThrows(IllegalArgumentException.class, () -> 
		 new Dice(new int[] {1,2,3}));
	}

	@Test
	void testRollDice() {
		int[] arr = myDice.rollDice();
		for(int i = 0; i < arr.length; i++) {
			assertEquals(true, arr[i] != 0);
		}
	}
	
	@Test
	void testGetSetDice() {
		int[] array = new int[] {1,2,3,4,5};
		myDice.setDice(array);
		int[] getValue = myDice.getDice();
		for(int i = 0; i < myDice.getDice().length; i++) {
			assertEquals(array[i], getValue[i]);
		}
	}
	
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
	
	@Test
	void testCalculateScoreSingleOne() {
		myDice.setDice(new int[] {1,2,3,4,6});
		assertEquals(100, myDice.calculateScore());
	}
	
	@Test
	void testCalculateScoreTripleOne() {
		myDice.setDice(new int[] {1,1,1,1,1});
		assertEquals(1200, myDice.calculateScore());
	}
	
	@Test
	void testCalculateScoreTripleOneSingleFive() {
		myDice.setDice(new int[] {1,1,1,5,1});
		assertEquals(1150, myDice.calculateScore());
	}
	
	@Test
	void testCalculateScoreZero() {
		myDice.setDice(new int[] {2,3,4,6,2});
		assertEquals(0, myDice.calculateScore());
	}
	
	@Test
	void testCalculateScoreTripleThree() {
		myDice.setDice(new int[] {3,4,5,3,3});
		assertEquals(350, myDice.calculateScore());
	}
}
