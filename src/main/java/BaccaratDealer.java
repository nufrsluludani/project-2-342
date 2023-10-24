//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class BaccaratDealer {
    ArrayList<Card> deck;
    ArrayList<Integer> dealedCards = new ArrayList();
    String[] suites = new String[]{"HEART", "SPADE", "DIAMOND", "CLUB"};

    public BaccaratDealer() {
    }

    public void generateDeck() {
        this.deck = new ArrayList();
        this.dealedCards.clear();

        for(int i = 0; i < 4; ++i) {
            for(int j = 1; j < 14; ++j) {
                Card temp = new Card(this.suites[i], j);
                this.deck.add(temp);
            }
        }
        Collections.shuffle(this.deck);
    }

    public ArrayList<Card> dealHand() {
        ArrayList<Card> hand = new ArrayList();
        hand.add(this.drawOne());
        hand.add(this.drawOne());
        return hand;
    }

    public Card drawOne() {

        Card cardDrawn = this.deck.get(0); // get card
        this.deck.remove(0); // remove card that was dealt

        return cardDrawn;
    }

    public void shuffleDeck() {
        generateDeck();
        Collections.shuffle(this.deck);
    }

    public int deckSize() {
        return deck.size();
    }
}
