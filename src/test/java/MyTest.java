import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class MyTest {


	//Baccarat dealer class tests start here
	@Test
	void generateDeckTest() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();

		assertEquals(52,test.deckSize());

		BaccaratDealer test2 = new BaccaratDealer();
		test2.generateDeck();

		assertEquals(52,test2.deckSize());
	}

	@Test
	void dealHandTest() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		ArrayList<Card> cards = test.dealHand();

		assertEquals(2,cards.size());
		assertNotEquals(cards.get(0),cards.get(1));
		assertEquals(50,test.deckSize());
	}

	@Test
	void drawOneTest(){
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();

		Card temp = test.drawOne();
		assertEquals(51,test.deckSize());
		assertNotEquals(temp,test.drawOne());
	}

	@Test
	void shuffleDeckTest() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		Card temp = test.drawOne();
		test.shuffleDeck();
		assertNotEquals(temp,test.drawOne());

		temp = test.drawOne();
		assertNotEquals(temp,test.drawOne());

	}

	@Test
	void deckSizeTest() {
		BaccaratDealer test = new BaccaratDealer();
		test.generateDeck();
		assertEquals(52,test.deckSize());

		test.drawOne();
		test.drawOne();
		test.drawOne();

		assertEquals(49,test.deckSize());
		for(int i = 0; i < 49; i++){
			test.drawOne();
		}
		assertEquals(0,test.deckSize());
	}

	//Baccarat Game logic tests
	@Test
	void whoWonTest(){
		BaccaratGameLogic test = new BaccaratGameLogic();
		Card card1 = new Card("",9);
		Card card2 = new Card("",13);
		ArrayList<Card> player = new ArrayList<>();
		player.add(card1);
		player.add(card2);

		Card card3 = new Card("",1);
		Card card4 = new Card("",2);
		ArrayList<Card> banker = new ArrayList<>();
		banker.add(card3);
		banker.add(card4);

		assertEquals("Player", test.whoWon(banker,player));

		banker.clear();
		banker.add(card1);
		banker.add(card2);

		assertEquals("Draw", test.whoWon(banker,player));

		banker.clear();

		Card card5 = new Card("",8);

		banker.add(card5);

		assertEquals("Player",test.whoWon(banker,player));

	}

	@Test
	void handTotalTest() {
		BaccaratGameLogic test = new BaccaratGameLogic();
		Card card1 = new Card("",9);
		Card card2 = new Card("",13);
		ArrayList<Card> player = new ArrayList<>();
		player.add(card1);
		player.add(card2);

		Card card3 = new Card("",1);
		Card card4 = new Card("",2);
		ArrayList<Card> banker = new ArrayList<>();
		banker.add(card3);
		banker.add(card4);

		assertEquals(9, test.handTotal(player));
		assertEquals(3,test.handTotal(banker));

		banker.clear();

		Card card5 = new Card("",12);
		Card card6 = new Card("",12);
		banker.add(card5);
		banker.add(card6);

		assertEquals(0,test.handTotal(banker));

		banker.clear();

		Card card7 = new Card("",12);
		Card card8 = new Card("",6);
		banker.add(card7);
		banker.add(card8);

		assertEquals(6,test.handTotal(banker));
	}

	@Test
	void evaluateBankerDrawTest(){
		BaccaratGameLogic test = new BaccaratGameLogic();
		Card card1 = new Card("",2);
		Card card2 = new Card("",13);
		ArrayList<Card> banker = new ArrayList<>();
		banker.add(card1);
		banker.add(card2);
		assertTrue(test.evaluateBankerDraw(banker,null));

		banker.clear();
		Card card3 = new Card("",5);
		Card card4 = new Card("",4);
		banker.add(card3);
		banker.add(card4);

		assertFalse(test.evaluateBankerDraw(banker, null));
	}

	@Test
	void evaluatePlayerDrawTest(){
		BaccaratGameLogic test = new BaccaratGameLogic();
		Card card1 = new Card("",4);
		Card card2 = new Card("",11);
		ArrayList<Card> player = new ArrayList<>();
		player.add(card1);
		player.add(card2);
		assertTrue(test.evaluateBankerDraw(player,null));

		player.clear();
		Card card3 = new Card("",5);
		Card card4 = new Card("",4);
		player.add(card3);
		player.add(card4);

		assertFalse(test.evaluateBankerDraw(player, null));
	}

	// Card class test
	@Test
	void constructorTest(){
		Card card1 = new Card("HEARTS",12);
		assertEquals("HEARTS", card1.getSuite());
		assertEquals(12,card1.getValue());

		Card card2 = new Card("Test", 99);
		assertEquals("Test",card2.getSuite());
		assertEquals(99,card2.getValue());
	}


}